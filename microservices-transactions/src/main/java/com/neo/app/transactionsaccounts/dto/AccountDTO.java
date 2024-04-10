package com.neo.app.transactionsaccounts.dto;

import jakarta.validation.constraints.NotNull;

public class AccountDTO {

    private Long accountId;
    @NotNull(message = "El accountNumber es obligatorio")
    private Long accountNumber;
    @NotNull(message = "El accountType es obligatorio")
    private String accountType;
    @NotNull(message = "El initialBalance es obligatorio")
    private Double initialBalance;
    @NotNull(message = "El state es obligatorio")
    private Character state;
    private Long customerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Character getState() {
        return state;
    }

    public void setState(Character state) {
        this.state = state;
    }
}
