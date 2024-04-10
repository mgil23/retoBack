package com.neo.app.users.mapper;

import com.neo.app.users.dto.CustomerDTO;
import com.neo.app.users.domain.Customer;
import org.mapstruct.Mapper;


import java.util.List;


@Mapper(componentModel = "spring")
public interface CustomerMapper {

    public CustomerDTO customerToCustomerDTO(Customer customer);

    public Customer customerDTOToCustomer(CustomerDTO customerDTO);

    public List<CustomerDTO> customerListToCustomerDTOList(List<Customer> customersList);

    public List<Customer> customerDTOListToCustomerList(List<CustomerDTO> customersDTOList);
}
