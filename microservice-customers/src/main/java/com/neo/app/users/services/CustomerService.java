package com.neo.app.users.services;

import com.neo.app.users.dto.CustomerDTO;
import com.neo.app.users.domain.Customer;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> findAllCustomers() throws Exception;

    CustomerDTO findCustomerByIdentification(String identification) throws Exception;

    CustomerDTO findCustomerById(Long id) throws Exception;

    CustomerDTO createCustomer(Customer customer) throws Exception;

    CustomerDTO updateCustomer(Customer customer) throws Exception;

    void deleteCustomerById(Long id) throws Exception;


    @Transactional(readOnly = true)
    String findCustomerByName(Long id) throws Exception;
}
