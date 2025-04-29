package io.datajek.springdata.jdbc.banking_system.dto;

import io.datajek.springdata.jdbc.banking_system.models.AccountInfoModel;
import io.datajek.springdata.jdbc.banking_system.models.TransactionModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public class AccountInfoDTO {
    private String customerId;
    private String accountNumber;
    @NotNull
    @Pattern(regexp = "Checking|Saving", message = "accountType must be either Checking or Saving")
    private String accountType;
    private Float balance;
    private List<TransactionModel> transactions;

    public AccountInfoDTO(String customerId, String accountNumber, String accountType, Float balance, List<TransactionModel> transactions) {
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.transactions = transactions;
    }

    public AccountInfoDTO() {
    }

    public AccountInfoModel converttoModel() {
        return new AccountInfoModel(this.getCustomerId(), this.getAccountNumber(), this.getAccountType(), this.getBalance());
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

    public @NotNull @Pattern(regexp = "Checking|Saving", message = "accountType must be either ACTIVE or INACTIVE") String getAccountType() {
        return accountType;
    }

    public void setAccountType(@NotNull @Pattern(regexp = "Checking|Saving", message = "accountType must be either ACTIVE or INACTIVE") String accountType) {
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
}
