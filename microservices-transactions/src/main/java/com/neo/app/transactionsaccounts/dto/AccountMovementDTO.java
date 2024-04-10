package com.neo.app.transactionsaccounts.dto;

import jakarta.validation.constraints.NotNull;

public class AccountMovementDTO {

    @NotNull(message = "El accountNumber es obligatorio")
    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
