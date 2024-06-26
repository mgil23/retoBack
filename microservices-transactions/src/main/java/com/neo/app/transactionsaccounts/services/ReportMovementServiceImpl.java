package com.neo.app.transactionsaccounts.services;

import com.neo.app.transactionsaccounts.domain.Account;
import com.neo.app.transactionsaccounts.domain.Movement;
import com.neo.app.transactionsaccounts.dto.ReportDTO;
import com.neo.app.transactionsaccounts.repository.AccountRepository;
import com.neo.app.transactionsaccounts.repository.MovementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportMovementServiceImpl implements ReportMovementService {

    private static final Logger log = LoggerFactory.getLogger(ReportMovementServiceImpl.class);
    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final WebClient.Builder webClientBulder;

    public ReportMovementServiceImpl(MovementRepository movementRepository, AccountRepository accountRepository, WebClient.Builder webClientBulder) {
        this.movementRepository = movementRepository;
        this.webClientBulder = webClientBulder;
        this.accountRepository = accountRepository;

    }


    @Override
    public List<ReportDTO> generateAccountMovementsReport(String accountNumber, String initialDateStr, String finalDateStr) {

        LocalDate initialDate = LocalDate.parse(initialDateStr, formatter);
        LocalDate finalDate = LocalDate.parse(finalDateStr, formatter);

        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new RuntimeException("La cuenta no existe"));
        List<Movement> movements = movementRepository.findMovementsByAccountAndDateRange(account, initialDate, finalDate);

        return movements.stream().map(movement -> {
            ReportDTO mapper = new ReportDTO();
            mapper.setDate(movement.getDate());
            String customerId = movement.getAccount().getCustomerId();
            String customerName = getCustomerName(customerId);
            mapper.setCustomer(customerName);
            mapper.setAccountNumber(account.getAccountNumber());
            mapper.setType(movement.getType());
            mapper.setStatus(true);
            mapper.setMovement(movement.getAmount());
            mapper.setInitialBalance(movement.getCurrentBalance());
            return mapper;
        }).collect(Collectors.toList());

    }

    private String getCustomerName(String customerId) {
        return webClientBulder.build().get()
                .uri("http://MICROSERVICE-CUSTOMERS/customer/name/{customerId}", customerId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}










