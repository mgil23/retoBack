package com.neo.app.transactionsaccounts.mapper;



import com.neo.app.transactionsaccounts.dto.MovementDTO;
import com.neo.app.transactionsaccounts.domain.Movement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovementMapper {

    public MovementDTO movementToMovementDTO(Movement movement);
    public Movement movementDTOToMovement(MovementDTO movementDTO);

}
