package com.neo.app.transactionsaccounts.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MovementDTO {

    private Long movementsId;
    private LocalDate date;
    @NotNull(message = "El type es obligatorio")
    @Pattern(regexp = "^(DEPOSITO|RETIRO)$", message = "El type solo puede ser DEPOSITO o RETIRO")
    private String type;
    @NotNull(message = "El amount es obligatorio")
    private BigDecimal amount;
    @NotNull(message = "El currentBalance es obligatorio")
    private BigDecimal currentBalance;
    @NotNull(message = "El account es obligatorio")
    @Valid
    private AccountMovementDTO account;
    private String accountNumber;
    private String customerId;


    public AccountMovementDTO getAccount() {
        return account;
    }

    public void setAccount(AccountMovementDTO account) {
        this.account = account;
    }

    public Long getMovementsId() {
        return movementsId;
    }

    public void setMovementsId(Long movementsId) {
        this.movementsId = movementsId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
