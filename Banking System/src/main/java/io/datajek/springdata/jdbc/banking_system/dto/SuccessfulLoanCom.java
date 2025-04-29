package io.datajek.springdata.jdbc.banking_system.dto;

public class SuccessfulLoanCom {
    private LoanComDTO loanCom;
    private UpdatedAccount updatedAccount;

    public SuccessfulLoanCom(LoanComDTO loanCom, UpdatedAccount updatedAccount) {
        this.loanCom = loanCom;
        this.updatedAccount = updatedAccount;
    }
    public SuccessfulLoanCom() {}

    public LoanComDTO getLoanCom() {
        return loanCom;
    }
    public void setLoanCom(LoanComDTO loanCom) {
        this.loanCom = loanCom;
    }
    public UpdatedAccount getUpdatedAccount() {
        return updatedAccount;
    }
    public void setUpdatedAccount(UpdatedAccount updatedAccount) {
        this.updatedAccount = updatedAccount;
    }
}
