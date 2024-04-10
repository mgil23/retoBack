package com.neo.app.transactionsaccounts.services;

import com.neo.app.transactionsaccounts.domain.Account;
import com.neo.app.transactionsaccounts.domain.Movement;
import com.neo.app.transactionsaccounts.dto.MovementDTO;
import com.neo.app.transactionsaccounts.mapper.MovementMapper;
import com.neo.app.transactionsaccounts.repository.AccountRepository;
import com.neo.app.transactionsaccounts.repository.MovementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MovementServiceImpl implements MovementService {

    private static final Logger log = LoggerFactory.getLogger(MovementServiceImpl.class);
    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final MovementMapper movementMapper;


    public MovementServiceImpl(MovementRepository movementRepository, AccountRepository accountRepository, AccountService accountService, MovementMapper movementMapper) {
        this.movementRepository = movementRepository;
        this.accountRepository = accountRepository;
        this.accountService = accountService;
        this.movementMapper = movementMapper;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public MovementDTO createMovement(Movement movement) throws Exception {
        if (movement == null) {
            throw new Exception("El movimiento no puede ser nulo");
        }

        Optional<Account> accountOptional = accountRepository.findByAccountNumber(movement.getAccount().getAccountNumber());
        if (accountOptional.isEmpty()) {
            throw new Exception("La cuenta con accountNumber" + movement.getAccount().getAccountId() + " no existe");
        }

        Account account = accountOptional.get();
        BigDecimal currentBalance = account.getInitialBalance();
        BigDecimal movementAmount = movement.getAmount();

        if (currentBalance.compareTo(movementAmount) < 0) {
            throw new Exception("Saldo no disponible para realizar la operación");
        }

        switch (movement.getType()) {
            case "DEPOSITO":
                account.setInitialBalance(currentBalance.add(movementAmount));
                break;
            case "RETIRO":
                account.setInitialBalance(currentBalance.subtract(movementAmount));
                break;
            default:
                throw new Exception("Tipo de movimiento no válido");
        }

        accountService.updateAccount(account);

        movement.setDate(LocalDate.now());
        movement.setAccount(account);
        Movement savedMovement = movementRepository.save(movement);
        return movementMapper.movementToMovementDTO(savedMovement);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public MovementDTO updateMovement(Movement movement) throws Exception {
        if (movement == null) {
            throw new Exception("El movimiento no puede ser nulo");
        }
        Optional<Movement> existingMovement = movementRepository.findById(movement.getMovementsId());
        if (existingMovement.isPresent()) {
            Movement updatedMovement = movementRepository.save(movement);
            return movementMapper.movementToMovementDTO(updatedMovement);
        } else {
            throw new Exception("El movimiento con id " + movement.getMovementsId() + " no existe");
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteMovement(Long id) throws Exception {
        Movement movementExist = movementRepository.findById(id).orElseThrow(() -> new Exception("El movimiento con id " + id + " no existe"));
        movementRepository.delete(movementExist);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MovementDTO> getMovementById(Long id) throws Exception {
        Optional<Movement> movement = movementRepository.findById(id);
        if (movement.isEmpty()) {
            throw new Exception("El movimiento con id " + id + " no existe");
        }
        return movement.map(movementMapper::movementToMovementDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovementDTO> getAllMovements() throws Exception {
        List<Movement> movements = movementRepository.findAll();
        if (movements.isEmpty()) {
            throw new Exception("No hay movimientos registrados");
        }
        return movements.stream().map(movementMapper::movementToMovementDTO).toList();
    }
}










