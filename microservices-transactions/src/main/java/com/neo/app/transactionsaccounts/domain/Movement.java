package com.neo.app.transactionsaccounts.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "movements")
public class Movement{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movements_id")
    private Long movementsId;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "type")
    private String type;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "current_balance")
    private BigDecimal currentBalance;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "account_id")
    private Account account;

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
