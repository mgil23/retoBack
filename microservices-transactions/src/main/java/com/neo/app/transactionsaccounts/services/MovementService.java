package com.neo.app.transactionsaccounts.services;


import com.neo.app.transactionsaccounts.dto.MovementDTO;
import com.neo.app.transactionsaccounts.domain.Movement;

import java.util.List;
import java.util.Optional;

public interface MovementService {

    MovementDTO createMovement(Movement movement) throws Exception;

    MovementDTO updateMovement(Movement movement) throws Exception;

    void deleteMovement(Long id) throws Exception;

    Optional<MovementDTO> getMovementById(Long id) throws Exception;

    List<MovementDTO> getAllMovements() throws Exception;


}
