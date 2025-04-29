package io.datajek.springdata.jdbc.banking_system.controllers;

import io.datajek.springdata.jdbc.banking_system.dto.LoanComDTO;
import io.datajek.springdata.jdbc.banking_system.dto.LoanDTO;
import io.datajek.springdata.jdbc.banking_system.repository.AccountRepository;
import io.datajek.springdata.jdbc.banking_system.repository.TransactionRepository;
import io.datajek.springdata.jdbc.banking_system.services.LoanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Stack;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    @Autowired
    private final TransactionRepository transactionRepository;
    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private LoanServices loanServices;

    public LoanController(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @PostMapping("/apply")
    public ResponseEntity<LoanComDTO> createLoan(@RequestBody LoanDTO loanDTO) {
        if(accountRepository.findById(loanDTO.getAccountNumber()).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else if(loanDTO.getAmount() <= 0){
            return ResponseEntity.badRequest().build();
        }
//        LoanDTO loanDTO2 = new LoanDTO(loanDTO.getAccountNumber(), loanDTO.getAmount(), LocalDateTime.now());
        LoanComDTO newLoan = loanServices.createLoans(loanDTO).getBody();
        return ResponseEntity.ok(newLoan);
    }
    @GetMapping("/process/{customerId}")
    public ResponseEntity<?> processLoan(@PathVariable String customerId) {
        return ResponseEntity.ok(loanServices.processLoans(customerId));
    }
}
