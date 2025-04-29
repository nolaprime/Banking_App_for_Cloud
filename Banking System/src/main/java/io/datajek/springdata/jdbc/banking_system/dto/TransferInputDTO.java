package io.datajek.springdata.jdbc.banking_system.dto;

public class TransferInputDTO {
    private String fromAccountNumber;
    private String toAccountNumber;
    private Float amount;

    public TransferInputDTO() {}
    public TransferInputDTO(String fromAccountNumber, String toAccountNumber, Float amount) {
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
    }
    public String getFromAccountNumber() {
        return fromAccountNumber;
    }
    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }
    public String getToAccountNumber() {
        return toAccountNumber;
    }
    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }
    public Float getAmount() {
        return amount;
    }
    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
