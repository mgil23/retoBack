package com.neo.app.transactionsaccounts.repository;

import com.neo.app.transactionsaccounts.domain.Account;
import com.neo.app.transactionsaccounts.domain.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {


    @Query("SELECT m FROM Movement m WHERE m.account = :account AND m.date BETWEEN :startDate AND :endDate")
    List<Movement> findMovementsByAccountAndDateRange(
            @Param("account") Account account,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}