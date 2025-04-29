package io.datajek.springdata.jdbc.banking_system.models;

import io.datajek.springdata.jdbc.banking_system.dto.AccountInfoDTO;
import jakarta.persistence.*;
import org.apache.catalina.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AccountInfoModel  {
    private String customerId;
    @Id
    private String accountNumber;
    private String accountType;
    private Float balance;
    private String accessLevel;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransactionModel> transactions = new ArrayList<>();

    public AccountInfoModel() {

    }
    public AccountInfoModel(String customerId, String accountNumber, String accountType, Float balance) {
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
    }

    public AccountInfoDTO converttoDTO() {
        return new AccountInfoDTO(this.getCustomerId(), this.getAccountNumber(), this.getAccountType(), this.getBalance(), this.getTransactions());
    }
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }
    public List<TransactionModel> getTransactions() {
        return transactions;
    }
    public void setTransactions(List<TransactionModel> transactions) {
        this.transactions = transactions;
    }
    public String getAccessLevel() {
        return accessLevel;
    }
    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
}
