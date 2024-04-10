package com.neo.app.transactionsaccounts.services;

import com.neo.app.transactionsaccounts.dto.ReportDTO;

import java.util.List;

public interface ReportMovementService {

    List<ReportDTO> generateAccountMovementsReport(String accountNumber, String initialDateStr, String finalDateStr);


}
