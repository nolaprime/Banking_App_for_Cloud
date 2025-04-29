package io.datajek.springdata.jdbc.banking_system.dto;

import io.datajek.springdata.jdbc.banking_system.models.LoanModel;

import java.time.LocalDateTime;

public class LoanDTO {
    private String customerId;
    private String accountNumber;
    private Float amount;
    private LocalDateTime requestDate;

    public LoanDTO(String customerId, String accountNumber, Float amount, LocalDateTime requestDate) {
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.requestDate = requestDate;
    }
    public LoanDTO() {}

    public LoanModel toModel() {
        return new LoanModel(this.getAccountNumber(), this.getAmount(), this.getRequestDate());
    }

    public String getCustomerId() {return customerId;}
    public void setCustomerId(String customerId) {this.customerId = customerId;}
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
