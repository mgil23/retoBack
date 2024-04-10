package com.neo.app.transactionsaccounts.controller;

import com.neo.app.transactionsaccounts.domain.Account;
import com.neo.app.transactionsaccounts.domain.Movement;
import com.neo.app.transactionsaccounts.dto.AccountMovementDTO;
import com.neo.app.transactionsaccounts.dto.MovementDTO;
import com.neo.app.transactionsaccounts.dto.ReportDTO;
import com.neo.app.transactionsaccounts.mapper.MovementMapper;
import com.neo.app.transactionsaccounts.repository.AccountRepository;
import com.neo.app.transactionsaccounts.services.MovementService;
import com.neo.app.transactionsaccounts.services.ReportMovementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class MovementsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovementService movementService;

    @Autowired
    private MovementMapper movementMapper;

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private ReportMovementService reportMovementService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Create movement successfully")
    @Test
    public void shouldCreateMovementSuccessfully() throws Exception {

        MovementDTO movementDTO = new MovementDTO();
        //movementDTO.setAccountNumber("1234567890");
        movementDTO.setType("DEPOSIT");
        movementDTO.setAmount(BigDecimal.valueOf(300.0));
        movementDTO.setCurrentBalance(BigDecimal.valueOf(100.0));
        movementDTO.setDate(LocalDate.parse("2021-09-01"));
        AccountMovementDTO account = new AccountMovementDTO();
        account.setAccountNumber("123456789");
        movementDTO.setAccount(account);

        Movement movement = movementMapper.movementDTOToMovement(movementDTO);

        when(movementService.createMovement(movement)).thenReturn(movementDTO);

        ResponseEntity<MovementDTO> response = new ResponseEntity<>(movementService.createMovement(movement), HttpStatus.CREATED);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        MovementDTO responseBody = response.getBody();
        assertEquals(movementDTO, responseBody);
        verify(movementService, times(1)).createMovement(movement);
    }

    @DisplayName("Create movement throws exception")
    @Test
    public void shouldThrowExceptionWhenCreateMovement() throws Exception {

        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setAccountNumber("1234567890");
        movementDTO.setType("DEPOSIT");
        movementDTO.setAmount(BigDecimal.valueOf(300.0));
        movementDTO.setCurrentBalance(BigDecimal.valueOf(100.0));
        movementDTO.setDate(LocalDate.parse("2021-09-01"));

        Movement movement = movementMapper.movementDTOToMovement(movementDTO);
        when(movementService.createMovement(movement)).thenThrow(new RuntimeException());

        try {
            movementService.createMovement(movement);
        } catch (Exception e) {
            assertEquals(RuntimeException.class, e.getClass());
            verify(movementService, times(1)).createMovement(movement);
        }
    }

    @Test
    public void testGenerateAccountMovementsReport() throws Exception {

        Account account = new Account();
        account.setAccountNumber("1234567890");
        account.setState('A');
        account.setCustomerId("John Doe");
        account.setAccountType("SAVINGS");
        account.setInitialBalance(BigDecimal.valueOf(400.0));
        account.setAccountId(1L);

        when(accountRepository.findByAccountNumber("1234567890")).thenReturn(Optional.of(account));

        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setAccountNumber("1234567890");
        reportDTO.setCustomer("John Doe");
        reportDTO.setDate(LocalDate.parse("2021-09-01"));
        reportDTO.setType("DEPOSIT");
        reportDTO.setMovement(BigDecimal.valueOf(300.0));
        reportDTO.setAvailableBalance(BigDecimal.valueOf(100.0));

        List<ReportDTO> reportDTOList = new ArrayList<>();
        reportDTOList.add(reportDTO);

        when(reportMovementService.generateAccountMovementsReport(anyString(), anyString(), anyString())).thenReturn(reportDTOList);

        mockMvc.perform(get("/movement/report")
                        .param("accountNumber", "1234567890")
                        .param("initialDate", "2021-09-01")
                        .param("finalDate", "2021-09-30"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].accountNumber", is("1234567890")))
                .andExpect(jsonPath("$[0].customer", is("John Doe")))
                .andExpect(jsonPath("$[0].date", is("2021-09-01")))
                .andExpect(jsonPath("$[0].type", is("DEPOSIT")))
                .andExpect(jsonPath("$[0].movement", is(300.0)))
                .andExpect(jsonPath("$[0].availableBalance", is(100.0)));

        verify(reportMovementService, times(1)).generateAccountMovementsReport("1234567890", "2021-09-01", "2021-09-30");
    }


    private MovementDTO requestToMovementDTO() {
        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setAccountNumber("1234567890");
        movementDTO.setType("DEPOSIT");
        movementDTO.setAmount(BigDecimal.valueOf(300.0));
        movementDTO.setCurrentBalance(BigDecimal.valueOf(100.0));
        movementDTO.setDate(LocalDate.parse("2021-09-01"));
        return movementDTO;
    }

    private Movement createTestMovement() {
        Movement movement = new Movement();
        movement.setType("DEPOSIT");
        movement.setAmount(BigDecimal.valueOf(300.0));
        movement.setCurrentBalance(BigDecimal.valueOf(100.0));
        movement.setDate(LocalDate.parse("2021-09-01"));
        Account account = new Account();
        account.setAccountId(1L);
        account.setAccountNumber("1234567890");
        account.setAccountType("SAVINGS");
        account.setInitialBalance(BigDecimal.valueOf(400.0));
        account.setCustomerId("1");
        movement.setAccount(account);
        return movement;
    }
}