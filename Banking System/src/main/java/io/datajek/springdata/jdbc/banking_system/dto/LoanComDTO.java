package io.datajek.springdata.jdbc.banking_system.dto;

import io.datajek.springdata.jdbc.banking_system.models.LoanModel;

public class LoanComDTO {
    private String message;
    private LoanModel loanModel;

    public LoanComDTO(String message, LoanModel loanModel) {
        this.message = message;
        this.loanModel = loanModel;
    }
    public LoanComDTO(){
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public LoanModel getLoanModel() {
        return loanModel;
    }
    public void setLoanDTO(LoanModel loanModel) {
        this.loanModel = loanModel;
    }

}
