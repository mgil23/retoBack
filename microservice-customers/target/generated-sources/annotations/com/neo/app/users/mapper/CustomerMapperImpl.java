package com.neo.app.users.mapper;

import com.neo.app.users.domain.Customer;
import com.neo.app.users.dto.CustomerDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T10:45:59-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO customerToCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setCustomerId( customer.getCustomerId() );
        customerDTO.setPassword( customer.getPassword() );
        customerDTO.setState( customer.getState() );
        customerDTO.setName( customer.getName() );
        customerDTO.setGender( customer.getGender() );
        customerDTO.setAge( customer.getAge() );
        customerDTO.setIdentification( customer.getIdentification() );
        customerDTO.setAddress( customer.getAddress() );
        customerDTO.setPhoneNumber( customer.getPhoneNumber() );

        return customerDTO;
    }

    @Override
    public Customer customerDTOToCustomer(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setName( customerDTO.getName() );
        customer.setGender( customerDTO.getGender() );
        customer.setAge( customerDTO.getAge() );
        customer.setIdentification( customerDTO.getIdentification() );
        customer.setAddress( customerDTO.getAddress() );
        customer.setPhoneNumber( customerDTO.getPhoneNumber() );
        customer.setCustomerId( customerDTO.getCustomerId() );
        customer.setPassword( customerDTO.getPassword() );
        customer.setState( customerDTO.getState() );

        return customer;
    }

    @Override
    public List<CustomerDTO> customerListToCustomerDTOList(List<Customer> customersList) {
        if ( customersList == null ) {
            return null;
        }

        List<CustomerDTO> list = new ArrayList<CustomerDTO>( customersList.size() );
        for ( Customer customer : customersList ) {
            list.add( customerToCustomerDTO( customer ) );
        }

        return list;
    }

    @Override
    public List<Customer> customerDTOListToCustomerList(List<CustomerDTO> customersDTOList) {
        if ( customersDTOList == null ) {
            return null;
        }

        List<Customer> list = new ArrayList<Customer>( customersDTOList.size() );
        for ( CustomerDTO customerDTO : customersDTOList ) {
            list.add( customerDTOToCustomer( customerDTO ) );
        }

        return list;
    }
}
