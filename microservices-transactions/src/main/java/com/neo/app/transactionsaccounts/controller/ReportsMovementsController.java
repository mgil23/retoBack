package com.neo.app.transactionsaccounts.controller;


import com.neo.app.transactionsaccounts.dto.ReportDTO;
import com.neo.app.transactionsaccounts.services.ReportMovementService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportsMovementsController {

    private final ReportMovementService reportMovementService;

    public ReportsMovementsController(ReportMovementService reportMovementService) {
        this.reportMovementService = reportMovementService;
    }

    @GetMapping("")
    public ResponseEntity<List<ReportDTO>> generateAccountMovementsReport(@RequestParam String accountNumber,
                                                                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate initialDate,
                                                                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate finalDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String initialDateStr = initialDate.format(formatter);
        String finalDateStr = finalDate.format(formatter);
        List<ReportDTO> report = reportMovementService.generateAccountMovementsReport(accountNumber, initialDateStr, finalDateStr);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

}
