package io.datajek.springdata.jdbc.banking_system.dto;

public class TransferDTO {
    private String message;
    private AccountInfoDTO fromAccount;
    private AccountInfoDTO toAccount;

    public TransferDTO() {
    }
    public TransferDTO(String message, AccountInfoDTO fromAccount, AccountInfoDTO toAccount) {
        this.message = message;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AccountInfoDTO getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(AccountInfoDTO fromAccount) {
        this.fromAccount = fromAccount;
    }

    public AccountInfoDTO getToAccount() {
        return toAccount;
    }

    public void setToAccount(AccountInfoDTO toAccount) {
        this.toAccount = toAccount;
    }
}
