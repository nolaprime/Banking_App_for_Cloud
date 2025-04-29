package io.datajek.springdata.jdbc.banking_system.dto;

import java.math.BigDecimal;

public class UpdatedAccount {
    private String accountNumber;
    private Float balance;

    public UpdatedAccount(String accountNumber, Float balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    public UpdatedAccount() {}

    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public Float getBalance() {
        return balance;
    }
    public void setBalance(Float balance) {
        this.balance = balance;
    }
}
