package io.datajek.springdata.jdbc.banking_system.services;

import io.datajek.springdata.jdbc.banking_system.dto.AccountInfoDTO;
import io.datajek.springdata.jdbc.banking_system.dto.DepositorWithdrawDTO;
import io.datajek.springdata.jdbc.banking_system.dto.TransferDTO;
import io.datajek.springdata.jdbc.banking_system.dto.TransferInputDTO;
import io.datajek.springdata.jdbc.banking_system.models.AccountInfoModel;
import io.datajek.springdata.jdbc.banking_system.models.TransactionModel;
import io.datajek.springdata.jdbc.banking_system.repository.AccountRepository;
import io.datajek.springdata.jdbc.banking_system.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServices {
    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public AccountServices(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public ResponseEntity<AccountInfoDTO> deposit(String accountNumber, DepositorWithdrawDTO depositorWithdrawDTO) {
        Optional<AccountInfoModel> optionalAccountInfoModel = accountRepository.findAccountInfoModelByAccountNumber(accountNumber);
        if (optionalAccountInfoModel.isPresent()) {
            AccountInfoModel accountInfoModel = optionalAccountInfoModel.get();
            Float balance = accountInfoModel.getBalance();
            if (depositorWithdrawDTO.getAmount() > 0) {

                // Update account balance
                Float newBalance = balance + depositorWithdrawDTO.getAmount();
                accountInfoModel.setBalance(newBalance);

                // Set transaction model
                TransactionModel transactionModel = new TransactionModel();
                transactionModel.setTransactionId(String.valueOf(UUID.randomUUID()));
               // transactionModel.setAccountNumber(accountInfoModel.getAccountNumber());
                transactionModel.setTransactionType("Deposit");
                transactionModel.setAmount(depositorWithdrawDTO.getAmount());
                transactionModel.setTransactionDate(LocalDateTime.now());
                transactionModel.setOldBalance(accountInfoModel.getBalance()-depositorWithdrawDTO.getAmount());
                transactionModel.setNewBalance(newBalance);
//                transactionRepository.save(transactionModel);

                // Add transaction to account's list of transactions.
                accountInfoModel.getTransactions().add(transactionModel);
                accountRepository.save(accountInfoModel);

                AccountInfoDTO accountInfoDTO1 = accountInfoModel.converttoDTO();
                return ResponseEntity.ok(accountInfoDTO1);
            }
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<AccountInfoDTO> withdraw(String accountNumber, DepositorWithdrawDTO depositorWithdrawDTO) {
        Optional<AccountInfoModel> accountInfoModel = accountRepository.findAccountInfoModelByAccountNumber(accountNumber);
        if (accountInfoModel.isPresent()) {
            AccountInfoModel accountInfoModel1 = accountInfoModel.get();
            Float balance = accountInfoModel1.getBalance();
            if ((balance - depositorWithdrawDTO.getAmount()) < 0) {
                return ResponseEntity.badRequest().build();
            }

            // Update the account's balance
            Float newBalance = balance - depositorWithdrawDTO.getAmount();
            accountInfoModel1.setBalance(newBalance);


            TransactionModel transactionModel = new TransactionModel();
            transactionModel.setTransactionId(String.valueOf(UUID.randomUUID()));
            //transactionModel.setAccountNumber(accountInfoModel1.getAccountNumber());
            transactionModel.setTransactionType("Withdrawal");
            transactionModel.setAmount(depositorWithdrawDTO.getAmount());
            transactionModel.setTransactionDate(LocalDateTime.now());
            transactionModel.setOldBalance(accountInfoModel1.getBalance()+depositorWithdrawDTO.getAmount());
            transactionModel.setNewBalance(newBalance);
            transactionRepository.save(transactionModel);

            // Add the transaction to the list of transactions in the account if model
            List<TransactionModel> accountTransactions = accountInfoModel1.getTransactions();
            accountTransactions.add(transactionModel);
            accountInfoModel1.setTransactions(accountTransactions);

            accountRepository.save(accountInfoModel1);
            AccountInfoDTO accountInfoDTO = accountInfoModel1.converttoDTO();
            return ResponseEntity.ok(accountInfoDTO);
        }
        return ResponseEntity.notFound().build();
    }
    @Transactional
    public ResponseEntity<TransferDTO> transfer(TransferInputDTO transferInputDTO) {
        Optional<AccountInfoModel> accountInfoModel1 = accountRepository.findAccountInfoModelByAccountNumber(transferInputDTO.getFromAccountNumber());
        Optional<AccountInfoModel> accountInfoModel2 = accountRepository.findAccountInfoModelByAccountNumber(transferInputDTO.getToAccountNumber());
        if(accountInfoModel1.get().getAccessLevel() == "Gold"){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (!accountInfoModel1.isPresent() || !accountInfoModel2.isPresent() || accountInfoModel1==accountInfoModel2) {
            return ResponseEntity.badRequest().build();
        }
        AccountInfoModel user1 = accountInfoModel1.get();
        AccountInfoModel user2 = accountInfoModel2.get();
        if (transferInputDTO.getAmount() > user1.getBalance()) {
            return ResponseEntity.badRequest().build();
        }
        user1.setBalance(user1.getBalance() - transferInputDTO.getAmount());
        user2.setBalance(user2.getBalance() + transferInputDTO.getAmount());



        TransactionModel transactionModelFrom = new TransactionModel();
        transactionModelFrom.setTransactionId(String.valueOf(UUID.randomUUID()));
       // transactionModelFrom.setAccountNumber(user1.getAccountNumber());
        transactionModelFrom.setTransactionType("Transfer Out");
        transactionModelFrom.setAmount(transferInputDTO.getAmount());
        transactionModelFrom.setTransactionDate(LocalDateTime.now());
        transactionModelFrom.setNewBalance(user1.getBalance());
        transactionModelFrom.setOldBalance(user1.getBalance()+transferInputDTO.getAmount());
        transactionRepository.save(transactionModelFrom);

        List<TransactionModel> accountTransactions = user1.getTransactions();
        accountTransactions.add(transactionModelFrom);
        user1.setTransactions(accountTransactions);

        accountRepository.save(user1);


        TransactionModel transactionModelTo = new TransactionModel();
        transactionModelTo.setTransactionId(String.valueOf(UUID.randomUUID()));
        //transactionModelTo.setAccountNumber(user2.getAccountNumber());
        transactionModelTo.setTransactionType("Transfer In");
        transactionModelTo.setAmount(transferInputDTO.getAmount());
        transactionModelTo.setTransactionDate(LocalDateTime.now());
        transactionModelTo.setNewBalance(user2.getBalance());
        transactionModelTo.setOldBalance(user2.getBalance()-transferInputDTO.getAmount());
        transactionRepository.save(transactionModelTo);

        List<TransactionModel> transactions = user2.getTransactions();
        transactions.add(transactionModelTo);
        user2.setTransactions(transactions);

        accountRepository.save(user2);

        AccountInfoDTO user1DTO = user1.converttoDTO();
        AccountInfoDTO user2DTO = user2.converttoDTO();
        TransferDTO transferDTO = new TransferDTO("Transfer Successful", user1DTO, user2DTO);

        return ResponseEntity.ok(transferDTO);
    }
}

