package com.neo.app.users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo.app.users.domain.Customer;
import com.neo.app.users.dto.CustomerDTO;
import com.neo.app.users.mapper.CustomerMapper;
import com.neo.app.users.services.CustomerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private CustomerMapper customerMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @DisplayName("Add customer successfully")
    @Test
    public void shouldAddCustomerSuccessfully() throws Exception {
        CustomerDTO customerDTO = getCustomerInDTO();
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        when(customerService.createCustomer(customer)).thenReturn(customerDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(customerDTO.getName()))
                .andExpect(jsonPath("$.age").value(customerDTO.getAge()))
                .andExpect(jsonPath("$.gender").value(customerDTO.getGender()))
                .andExpect(jsonPath("$.identification").value(customerDTO.getIdentification()));
    }


    @DisplayName("Test add customer with invalid request")
    @Test
    public void testAddCustomerWithInvalidRequest() throws Exception {
        // Prepare test data
        CustomerDTO customerIntDTO = new CustomerDTO();
        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerIntDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Test update customer")
    @Test
    public void testUpdateCustomer() throws Exception {
        CustomerDTO customerDTO = getCustomerInDTO();
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        when(customerService.updateCustomer(customer)).thenReturn(customerDTO);
        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.put("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @DisplayName("Test find customer by id")
    @Test
    public void testFindCustomerByIdentification() throws Exception {
        // Prepare test data
        CustomerDTO customerOutDTO = getCustomerOutDto();
        when(customerService.findCustomerByIdentification("123")).thenReturn(customerOutDTO);
        // Perform the GET request
        mockMvc.perform(get("/customer/identification/123"))
                .andExpect(status().isOk())
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerId").value(1))
                .andExpect(jsonPath("$.name").value("Juan"))
                .andExpect(jsonPath("$.age").value(26));

    }

    private static CustomerDTO getCustomerOutDto() {
        CustomerDTO customerOutDTO = new CustomerDTO();
        customerOutDTO.setCustomerId(1L);
        customerOutDTO.setAddress("Calle 123");
        customerOutDTO.setAge(26);
        customerOutDTO.setGender("M");
        customerOutDTO.setIdentification("123");
        customerOutDTO.setName("Juan");
        customerOutDTO.setPassword("123");
        customerOutDTO.setPhoneNumber("3187533");
        customerOutDTO.setState('A');
        return customerOutDTO;
    }

    private static CustomerDTO getCustomerInDTO() {
        CustomerDTO customerIntDTO = new CustomerDTO();
        customerIntDTO.setAddress("Calle 123");
        customerIntDTO.setAge(25);
        customerIntDTO.setGender("M");
        customerIntDTO.setIdentification("00001");
        customerIntDTO.setName("Juan");
        customerIntDTO.setPassword("00001");
        customerIntDTO.setPhoneNumber("3187533");
        customerIntDTO.setState('A');
        return customerIntDTO;
    }

}