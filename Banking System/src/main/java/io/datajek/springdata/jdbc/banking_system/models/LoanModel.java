package io.datajek.springdata.jdbc.banking_system.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class LoanModel {
    @Id
    private String loanId;
    private String accountNumber;
    private Float amount;
    private LocalDateTime requestDate;

    public LoanModel() {}

    public LoanModel(String loanId, String accountNumber, Float amount, LocalDateTime requestDate) {
        this.loanId = loanId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.requestDate = requestDate;
    }

    public LoanModel(String accountNumber, Float amount, LocalDateTime requestDate) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.requestDate = requestDate;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }
}
