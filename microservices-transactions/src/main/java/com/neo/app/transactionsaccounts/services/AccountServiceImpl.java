package com.neo.app.transactionsaccounts.services;

import com.neo.app.transactionsaccounts.dto.AccountDTO;
import com.neo.app.transactionsaccounts.mapper.AccountMapper;
import com.neo.app.transactionsaccounts.domain.Account;
import com.neo.app.transactionsaccounts.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;


    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public AccountDTO createAccount(Account account) throws Exception {
        if (account == null) {
            throw new Exception("La cuenta no puede ser nula");
        } else if (accountRepository.findById(account.getAccountId()).isPresent()) {
            throw new Exception("La cuenta con id " + account.getAccountId() + " ya existe");
        }
        return accountMapper.accountToAccountDTO(accountRepository.save(account));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountDTO> getClientAccounts(String customerId) {
        return accountRepository.findAllByCustomerId(customerId).stream().map(accountMapper::accountToAccountDTO).toList();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public AccountDTO updateAccount(Account account) throws Exception {
        Optional<Account> existingAccount = accountRepository.findById(account.getAccountId());
        if (existingAccount.isPresent()) {
            return accountMapper.accountToAccountDTO(accountRepository.save(account));
        } else {
            throw new Exception("La cuenta con el id " + account.getAccountId() + " no existe");
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteAccount(Long id) throws Exception {
        Optional<Account> existingAccount = accountRepository.findById(id);
        if (existingAccount.isPresent()) {
            accountRepository.delete(existingAccount.get());
        } else {
            throw new Exception("La cuenta con el id " + id + " no existe");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AccountDTO> getAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.map(accountMapper::accountToAccountDTO);
    }


}
