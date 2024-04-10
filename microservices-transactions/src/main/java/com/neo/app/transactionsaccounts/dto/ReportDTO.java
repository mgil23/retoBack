package com.neo.app.transactionsaccounts.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class ReportDTO {

    private LocalDate date;
    private String customer;
    private String accountNumber;
    private String type;
    private BigDecimal initialBalance;
    private boolean status;
    private BigDecimal movement;
    private BigDecimal availableBalance;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public BigDecimal getMovement() {
        return movement;
    }

    public void setMovement(BigDecimal movement) {
        this.movement = movement;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }
}
