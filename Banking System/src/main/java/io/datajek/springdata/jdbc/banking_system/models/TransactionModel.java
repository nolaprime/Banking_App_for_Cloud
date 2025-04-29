package io.datajek.springdata.jdbc.banking_system.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
@Entity
public class    TransactionModel {
    @Id
    private String transactionId;
    //private String accountNumber;
    private String transactionType;
    private Float amount;
    private LocalDateTime transactionDate;
    private Float oldBalance;
    private Float newBalance;

    public TransactionModel(String transactionId, String accountNumber, String transactionType, Float amount, LocalDateTime transactionDate, Float oldBalance, Float newBalance) {
        this.transactionId = transactionId;
        //this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
    }
    public TransactionModel() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

   /* public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }*/

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Float getOldBalance() {
        return oldBalance;
    }

    public void setOldBalance(Float oldBalance) {
        this.oldBalance = oldBalance;
    }

    public Float getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(Float newBalance) {
        this.newBalance = newBalance;
    }
}
