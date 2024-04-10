package com.neo.app.transactionsaccounts.mapper;

import com.neo.app.transactionsaccounts.domain.Account;
import com.neo.app.transactionsaccounts.domain.Movement;
import com.neo.app.transactionsaccounts.dto.AccountMovementDTO;
import com.neo.app.transactionsaccounts.dto.MovementDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T10:46:30-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class MovementMapperImpl implements MovementMapper {

    @Override
    public MovementDTO movementToMovementDTO(Movement movement) {
        if ( movement == null ) {
            return null;
        }

        MovementDTO movementDTO = new MovementDTO();

        movementDTO.setAccount( accountToAccountMovementDTO( movement.getAccount() ) );
        movementDTO.setMovementsId( movement.getMovementsId() );
        movementDTO.setDate( movement.getDate() );
        movementDTO.setType( movement.getType() );
        movementDTO.setAmount( movement.getAmount() );
        movementDTO.setCurrentBalance( movement.getCurrentBalance() );

        return movementDTO;
    }

    @Override
    public Movement movementDTOToMovement(MovementDTO movementDTO) {
        if ( movementDTO == null ) {
            return null;
        }

        Movement movement = new Movement();

        movement.setMovementsId( movementDTO.getMovementsId() );
        movement.setDate( movementDTO.getDate() );
        movement.setType( movementDTO.getType() );
        movement.setAmount( movementDTO.getAmount() );
        movement.setCurrentBalance( movementDTO.getCurrentBalance() );
        movement.setAccount( accountMovementDTOToAccount( movementDTO.getAccount() ) );

        return movement;
    }

    protected AccountMovementDTO accountToAccountMovementDTO(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountMovementDTO accountMovementDTO = new AccountMovementDTO();

        accountMovementDTO.setAccountNumber( account.getAccountNumber() );

        return accountMovementDTO;
    }

    protected Account accountMovementDTOToAccount(AccountMovementDTO accountMovementDTO) {
        if ( accountMovementDTO == null ) {
            return null;
        }

        Account account = new Account();

        account.setAccountNumber( accountMovementDTO.getAccountNumber() );

        return account;
    }
}
