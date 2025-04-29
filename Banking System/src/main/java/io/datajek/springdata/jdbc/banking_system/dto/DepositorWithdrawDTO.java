package io.datajek.springdata.jdbc.banking_system.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class DepositorWithdrawDTO {
    @NotEmpty
    private Float amount;

    public DepositorWithdrawDTO() {}
    public DepositorWithdrawDTO(Float amount) {
        this.amount = amount;
    }

    public @NotEmpty Float getAmount() {
        return amount;
    }

    public void setAmount(@NotEmpty Float amount) {
        this.amount = amount;
    }
}
