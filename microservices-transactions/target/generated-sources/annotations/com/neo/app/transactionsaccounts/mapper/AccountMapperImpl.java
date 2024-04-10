package com.neo.app.transactionsaccounts.mapper;

import com.neo.app.transactionsaccounts.domain.Account;
import com.neo.app.transactionsaccounts.dto.AccountDTO;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T10:46:30-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountDTO accountToAccountDTO(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountDTO accountDTO = new AccountDTO();

        if ( account.getCustomerId() != null ) {
            accountDTO.setCustomerId( Long.parseLong( account.getCustomerId() ) );
        }
        accountDTO.setAccountId( account.getAccountId() );
        if ( account.getAccountNumber() != null ) {
            accountDTO.setAccountNumber( Long.parseLong( account.getAccountNumber() ) );
        }
        accountDTO.setAccountType( account.getAccountType() );
        if ( account.getInitialBalance() != null ) {
            accountDTO.setInitialBalance( account.getInitialBalance().doubleValue() );
        }
        accountDTO.setState( account.getState() );

        return accountDTO;
    }

    @Override
    public Account accountDTOToAccount(AccountDTO accountDTO) {
        if ( accountDTO == null ) {
            return null;
        }

        Account account = new Account();

        account.setAccountId( accountDTO.getAccountId() );
        if ( accountDTO.getAccountNumber() != null ) {
            account.setAccountNumber( String.valueOf( accountDTO.getAccountNumber() ) );
        }
        account.setAccountType( accountDTO.getAccountType() );
        if ( accountDTO.getInitialBalance() != null ) {
            account.setInitialBalance( BigDecimal.valueOf( accountDTO.getInitialBalance() ) );
        }
        account.setState( accountDTO.getState() );
        if ( accountDTO.getCustomerId() != null ) {
            account.setCustomerId( String.valueOf( accountDTO.getCustomerId() ) );
        }

        return account;
    }
}
