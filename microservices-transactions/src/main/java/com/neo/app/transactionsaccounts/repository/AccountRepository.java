package com.neo.app.transactionsaccounts.repository;

import com.neo.app.transactionsaccounts.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByCustomerId(String customerId);
    Optional<Account> findByAccountNumber(String accountNumber);

}
