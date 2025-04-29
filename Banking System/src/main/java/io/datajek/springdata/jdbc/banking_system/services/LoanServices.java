package io.datajek.springdata.jdbc.banking_system.services;

import com.google.protobuf.StringValue;
import io.datajek.springdata.jdbc.banking_system.dto.LoanComDTO;
import io.datajek.springdata.jdbc.banking_system.dto.LoanDTO;
import io.datajek.springdata.jdbc.banking_system.dto.SuccessfulLoanCom;
import io.datajek.springdata.jdbc.banking_system.dto.UpdatedAccount;
import io.datajek.springdata.jdbc.banking_system.models.AccountInfoModel;
import io.datajek.springdata.jdbc.banking_system.models.CustomerInfoModel;
import io.datajek.springdata.jdbc.banking_system.models.LoanModel;
import io.datajek.springdata.jdbc.banking_system.models.TransactionModel;
import io.datajek.springdata.jdbc.banking_system.repository.AccountRepository;
import io.datajek.springdata.jdbc.banking_system.repository.CustomerRepository;
import io.datajek.springdata.jdbc.banking_system.repository.LoanRepository;
import io.datajek.springdata.jdbc.banking_system.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.UUID;

@Service
public class LoanServices {
    @Autowired
            private final TransactionRepository transactionRepository;
    @Autowired
            private final AccountRepository accountRepository;
    @Autowired
            private final LoanRepository loanRepository;
    @Autowired
    private CustomerRepository customerRepository;


    public LoanServices(TransactionRepository transactionRepository, AccountRepository accountRepository, LoanRepository loanRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.loanRepository = loanRepository;
    }

    public ResponseEntity<LoanComDTO> createLoans(LoanDTO loanDTO) {

        CustomerInfoModel customerInfoModel = customerRepository.findById(loanDTO.getCustomerId()).orElse(null);
        if (customerInfoModel != null) {
            loanDTO.setRequestDate(LocalDateTime.now());
            LoanModel loanModel = loanDTO.toModel();
            loanModel.setLoanId(UUID.randomUUID().toString());
            customerInfoModel.getLoanRequests().add(loanModel);
            customerRepository.save(customerInfoModel);
            LoanComDTO loanComDTO = new LoanComDTO("Loan application received.", loanModel);
            return ResponseEntity.ok(loanComDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
    public ResponseEntity<?> processLoans(String customerId) {
        CustomerInfoModel potentialLoanCustomerInfo = customerRepository.findById(customerId).orElse(null);
        List<LoanModel> loanRequests = potentialLoanCustomerInfo.getLoanRequests();
        LoanModel lastLoanRequest = loanRequests.get(loanRequests.size() - 1);

            List<TransactionModel> totalTransactions = accountRepository.findAccountInfoModelByAccountNumber(lastLoanRequest.getAccountNumber()).get().getTransactions();
        if(totalTransactions != null && !totalTransactions.isEmpty()) {
            Float totalAmount = 0.0F;
            for(TransactionModel totalTransaction : totalTransactions){
                totalAmount += totalTransaction.getAmount();
            }
            if((4*totalAmount) < lastLoanRequest.getAmount()) {
                String response = "Requested amount exceeds allowable amount.";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            SuccessfulLoanCom successfulLoanCom = new SuccessfulLoanCom();
            LoanComDTO loanComDTO = new LoanComDTO("Loan application processed successfully.", lastLoanRequest);
            AccountInfoModel accountInfoModel = accountRepository.findAccountInfoModelByAccountNumber(lastLoanRequest.getAccountNumber()).get();
            accountInfoModel.setBalance(accountInfoModel.getBalance() + lastLoanRequest.getAmount());
            UpdatedAccount updatedAccount = new UpdatedAccount(lastLoanRequest.getAccountNumber(), accountInfoModel.getBalance()+lastLoanRequest.getAmount());
            successfulLoanCom.setLoanCom(loanComDTO);
            successfulLoanCom.setUpdatedAccount(updatedAccount);

            TransactionModel transactionModel = new TransactionModel();
            transactionModel.setTransactionId(String.valueOf(UUID.randomUUID()));
           // transactionModel.setAccountNumber(potentialLoan.getAccountNumber());
            transactionModel.setAmount(lastLoanRequest.getAmount());
            transactionModel.setTransactionType("Loan");
            transactionModel.setTransactionDate(LocalDateTime.now());
            transactionModel.setOldBalance(accountInfoModel.getBalance() - lastLoanRequest.getAmount());
            transactionModel.setNewBalance(accountInfoModel.getBalance());
            transactionRepository.save(transactionModel);

            return ResponseEntity.ok(successfulLoanCom);
        }

        return ResponseEntity.badRequest().build();
    }
}
