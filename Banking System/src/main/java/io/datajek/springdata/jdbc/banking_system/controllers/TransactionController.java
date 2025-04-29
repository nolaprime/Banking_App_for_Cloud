package io.datajek.springdata.jdbc.banking_system.controllers;

import io.datajek.springdata.jdbc.banking_system.models.AccountInfoModel;
import io.datajek.springdata.jdbc.banking_system.models.TransactionModel;
import io.datajek.springdata.jdbc.banking_system.repository.AccountRepository;
import io.datajek.springdata.jdbc.banking_system.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;
    TransactionModel transactionModel;
    AccountInfoModel accountInfoModel;
    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/accounts/{accountNumber}/transactions")
    public ResponseEntity<Iterable<TransactionModel>> getTransactions(@PathVariable String accountNumber) {

//        Optional<AccountInfoModel> accountInfoModel = accountRepository.findAccountInfoModelByAccountNumber(accountNumber);
//        if(accountInfoModel.isPresent()) {
//            Iterable<TransactionModel> transactionModels = new ArrayList<>();
//            transactionModels = transactionRepository.findAll();
//            return ResponseEntity.ok(transactionModels);
//        }
        return ResponseEntity.notFound().build();
    }
}
