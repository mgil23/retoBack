package com.neo.app.users.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import com.neo.app.users.dto.CustomerDTO;
import com.neo.app.users.domain.Customer;
import com.neo.app.users.mapper.CustomerMapper;
import com.neo.app.users.repository.CustomerRepository;
import com.neo.app.users.services.CustomerServiceImpl;


public class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    private CustomerDTO customerDTO;
    private Customer customer;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        customerDTO = new CustomerDTO();
        customerDTO.setAddress("Calle 123");
        customerDTO.setAge(25);
        customerDTO.setGender("M");
        customerDTO.setIdentification("1234");
        customerDTO.setName("Juan");
        customerDTO.setPassword("123");
        customerDTO.setPhoneNumber("3187533");
        customerDTO.setState('A');

        customer = new Customer();
        customer.setAddress("Calle 123");
        customer.setAge(25);
        customer.setGender("M");
        customer.setIdentification("1234");
        customer.setName("Juan");
        customer.setPassword("123");
        customer.setPhoneNumber("3187533");
        customer.setState('A');
     }


    @Test
    public void testCreateCustomer() throws Exception {
        // Arrange
        when(customerRepository.findByIdentification(customer.getIdentification())).thenReturn(null);
        when(customerRepository.save(customer)).thenReturn(customer);
        customerDTO.setCustomerId(1L);
        when(customerMapper.customerToCustomerDTO(customer)).thenReturn(customerDTO);
        // Act
        CustomerDTO response = customerServiceImpl.createCustomer(customer);
        // Assert
        assertEquals(response.getCustomerId(), customerDTO.getCustomerId());
        assertEquals(response.getAddress(), customerDTO.getAddress());

        verify(customerRepository, times(1)).findByIdentification(customer.getIdentification());
        verify(customerRepository, times(1)).save(customer);
        verify(customerMapper, times(1)).customerToCustomerDTO(customer);

    }

    @Test
    public void testCreateCustomer_ThrowsException_WhenCustomerIsNull() {
        // Arrange
        Customer customer = null;

        // Act & Assert
        assertThrows(Exception.class, () -> customerServiceImpl.createCustomer(customer));
    }

    @Test
    public void testCreateCustomer_ThrowsException_WhenCustomerExists() {
        // Arrange
        when(customerRepository.findByIdentification(customer.getIdentification())).thenReturn(customer);
        // Act & Assert
        assertThrows(Exception.class, () -> customerServiceImpl.createCustomer(customer));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        // Arrange
        when(customerRepository.save(customer)).thenReturn(customer);
        customerDTO.setCustomerId(1L);
        when(customerMapper.customerToCustomerDTO(customer)).thenReturn(customerDTO);
        // Act
        CustomerDTO response = customerServiceImpl.updateCustomer(customer);
        // Assert
        assertEquals(response.getCustomerId(), customerDTO.getCustomerId());
        assertEquals(response.getAddress(), customerDTO.getAddress());

        verify(customerRepository, times(1)).save(customer);
        verify(customerMapper, times(1)).customerToCustomerDTO(customer);
    }
    
    @DisplayName("Test updateCustomer_ThrowsException_WhenCustomerIsNull")
    @Test
    public void testUpdateCustomer_ThrowsException_WhenCustomerIsNull() {
        // Arrange
        Customer customer = null;
        // Act & Assert
        assertThrows(Exception.class, () -> customerServiceImpl.updateCustomer(customer));
    }
    
    @DisplayName("Test deleteCustomerById")
    @Test
    public void testDeleteCustomerById() throws Exception {
        // Arrange
        Long id = 1L;
        when(customerRepository.existsById(id)).thenReturn(true);
        // Act
        customerServiceImpl.deleteCustomerById(id);
        // Assert
        verify(customerRepository, times(1)).existsById(id);
        verify(customerRepository, times(1)).deleteById(id);
    }

}