package io.datajek.springdata.jdbc.banking_system.controllers;

import io.datajek.springdata.jdbc.banking_system.dto.AccountInfoDTO;
import io.datajek.springdata.jdbc.banking_system.dto.DepositorWithdrawDTO;
import io.datajek.springdata.jdbc.banking_system.dto.TransferDTO;
import io.datajek.springdata.jdbc.banking_system.dto.TransferInputDTO;
import io.datajek.springdata.jdbc.banking_system.models.AccountInfoModel;
import io.datajek.springdata.jdbc.banking_system.models.TransactionModel;
import io.datajek.springdata.jdbc.banking_system.repository.AccountRepository;
import io.datajek.springdata.jdbc.banking_system.repository.TransactionRepository;
import io.datajek.springdata.jdbc.banking_system.repository.UserRepository;
import io.datajek.springdata.jdbc.banking_system.services.AccountServices;
import io.datajek.springdata.jdbc.banking_system.services.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/accounts")
public class AccountInfoController {
    @Autowired
    private final AccountRepository accountRepository;
    public AccountInfoController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Autowired
    private AccountServices accountServices;
    private TransactionModel transactionModel;
    private TransactionRepository transactionRepository;
    @Autowired private JwtUtil jwtUtil;
    @Autowired UserRepository userRepository;

    @PostMapping()
    public ResponseEntity<AccountInfoDTO> createAccount(@RequestBody AccountInfoDTO accountInfoDTO){
        accountInfoDTO.setAccountNumber(String.valueOf(UUID.randomUUID()));
        AccountInfoModel accountInfoModel = accountInfoDTO.converttoModel();
        if(accountInfoDTO.getBalance() <= 100) {
            accountInfoModel.setAccessLevel("Gold");
        }
        else if(accountInfoDTO.getBalance() > 100) {
            accountInfoModel.setAccessLevel("Platinum");
        }
        accountRepository.save(accountInfoModel);
        return ResponseEntity.ok(accountInfoDTO);
    }
    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountInfoDTO> getAccountById(@PathVariable("accountNumber") String accountNumber){
            Optional<AccountInfoModel> accountModel = accountRepository.findAccountInfoModelByAccountNumber(accountNumber);
        if(accountModel.isPresent()){
            AccountInfoDTO accountInfoDTO = accountModel.get().converttoDTO();
            return ResponseEntity.ok(accountInfoDTO);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<?> deposit(@RequestHeader("Authorization") String authHeader, @PathVariable("accountNumber") String accountNumber, @RequestBody DepositorWithdrawDTO depositorWithdrawDTO){

        return accountServices.deposit(accountNumber, depositorWithdrawDTO);
    }
    @PostMapping("/{accountNumber}/withdraw")
    public ResponseEntity<AccountInfoDTO> withdraw(@PathVariable("accountNumber") String accountNumber, @RequestBody DepositorWithdrawDTO depositorWithdrawDTO){
        return accountServices.withdraw(accountNumber, depositorWithdrawDTO);
    }
    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<String> deleteAccount(@PathVariable("accountNumber") String accountNumber){
        Optional<AccountInfoModel> accountInfoDTO = accountRepository.findAccountInfoModelByAccountNumber(accountNumber);
        if(accountInfoDTO.isPresent()){
            accountRepository.delete(accountInfoDTO.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/transfer")
    public ResponseEntity<TransferDTO> transfer(@RequestBody TransferInputDTO transferInputDTO){
        return accountServices.transfer(transferInputDTO);
    }
}
