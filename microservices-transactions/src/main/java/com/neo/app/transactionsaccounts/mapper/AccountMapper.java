package com.neo.app.transactionsaccounts.mapper;


import com.neo.app.transactionsaccounts.dto.AccountDTO;
import com.neo.app.transactionsaccounts.domain.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

   public  AccountDTO accountToAccountDTO(Account account);
   public Account accountDTOToAccount(AccountDTO accountDTO);



}
