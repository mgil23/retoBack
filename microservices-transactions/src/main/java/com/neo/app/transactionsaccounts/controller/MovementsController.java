package com.neo.app.transactionsaccounts.controller;

import com.neo.app.transactionsaccounts.domain.Movement;
import com.neo.app.transactionsaccounts.dto.MovementDTO;
import com.neo.app.transactionsaccounts.dto.ReportDTO;
import com.neo.app.transactionsaccounts.mapper.MovementMapper;
import com.neo.app.transactionsaccounts.services.MovementService;
import com.neo.app.transactionsaccounts.services.ReportMovementService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/movement")
public class MovementsController {

    private static final Logger log = LoggerFactory.getLogger(MovementsController.class);

    private final MovementService movementService;
    private final MovementMapper movementMapper;

    @Autowired
    public MovementsController(MovementService movementService, MovementMapper movementMapper) {
        this.movementService = movementService;
        this.movementMapper = movementMapper;
    }

    @PostMapping("")
    public ResponseEntity<MovementDTO> createMovement(@RequestBody @Valid MovementDTO movementDTO) throws Exception {
        log.debug("Request to create Movement: {}", movementDTO);
        Movement movement = movementMapper.movementDTOToMovement(movementDTO);
        return new ResponseEntity<>(movementService.createMovement(movement), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<MovementDTO> updateMovement(@RequestBody @Valid MovementDTO movementDTO) throws Exception {
        log.debug("Request to update Movement: {}", movementDTO);
        Movement movement = movementMapper.movementDTOToMovement(movementDTO);
        return new ResponseEntity<>(movementService.updateMovement(movement), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovement(@PathVariable Long id) throws Exception {
        log.debug("Request to delete Movement: {}", id);
        movementService.deleteMovement(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovementDTO> getMovementById(@PathVariable Long id) throws Exception {
        log.debug("Request to get Movement by id: {}", id);
        return new ResponseEntity<>(movementService.getMovementById(id).orElseThrow(() -> new Exception("Movement no encontrado")), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<MovementDTO>> getAllMovements() throws Exception {
        log.debug("Request to get all Movements");
        return new ResponseEntity<>(movementService.getAllMovements(), HttpStatus.OK);
    }

}
