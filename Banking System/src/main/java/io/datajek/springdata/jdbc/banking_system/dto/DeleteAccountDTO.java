package io.datajek.springdata.jdbc.banking_system.dto;

public class DeleteAccountDTO {
    private String accountNumber;

    public DeleteAccountDTO() {}

    public DeleteAccountDTO(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
