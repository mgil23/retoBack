package com.neo.app.transactionsaccounts.services;

import com.neo.app.transactionsaccounts.dto.AccountDTO;
import com.neo.app.transactionsaccounts.domain.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService{

  AccountDTO createAccount(Account account) throws Exception;
  List<AccountDTO> getClientAccounts(String clientId);
  AccountDTO updateAccount(Account account) throws Exception;
  void deleteAccount(Long id) throws Exception;
  Optional<AccountDTO> getAccountById(Long id);

}
