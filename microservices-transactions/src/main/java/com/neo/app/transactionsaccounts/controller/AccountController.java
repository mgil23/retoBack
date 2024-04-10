package com.neo.app.transactionsaccounts.controller;


import com.neo.app.transactionsaccounts.dto.AccountDTO;
import com.neo.app.transactionsaccounts.mapper.AccountMapper;
import com.neo.app.transactionsaccounts.domain.Account;
import com.neo.app.transactionsaccounts.services.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountController(AccountService accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> getAccountById(@NotNull @PathVariable Long accountId) {
        Optional<AccountDTO> accountDTO = accountService.getAccountById(accountId);
        return accountDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody AccountDTO account) {
        try {
            AccountDTO createdAccount = accountService.createAccount(accountMapper.accountDTOToAccount(account));
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<AccountDTO> updateAccount(@Valid @RequestBody AccountDTO accountDTO) {
        try {
            Account account = accountMapper.accountDTOToAccount(accountDTO);
            AccountDTO updatedAccount = accountService.updateAccount(account);
            return ResponseEntity.ok(updatedAccount);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteAccount(@NotNull @PathVariable Long accountId) {
        try {
            accountService.deleteAccount(accountId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

