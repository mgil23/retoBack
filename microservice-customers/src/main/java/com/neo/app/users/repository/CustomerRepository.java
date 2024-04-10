package com.neo.app.users.repository;

import com.neo.app.users.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
   
    Customer findByIdentification(String identification);


}
