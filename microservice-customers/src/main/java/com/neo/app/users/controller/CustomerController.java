package com.neo.app.users.controller;

import com.neo.app.users.dto.CustomerDTO;
import com.neo.app.users.domain.Customer;
import com.neo.app.users.mapper.CustomerMapper;
import com.neo.app.users.services.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    //@Autowired
    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

   @PostMapping("")
   public ResponseEntity<CustomerDTO> addCustomer(@RequestBody @Valid CustomerDTO customerDTO) throws Exception {
       log.debug("Request to create Customer: {}", customerDTO);
       Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
       return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
   }

   @PutMapping("")
   public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody @Valid CustomerDTO customerDTO) throws Exception {
       log.debug("Request to update Customer: {}", customerDTO);
       Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
       return new ResponseEntity<>(customerService.updateCustomer(customer), HttpStatus.OK);
   }

   @GetMapping("/identification/{identification}")
   public ResponseEntity<CustomerDTO> findCustomerByIdentification(@PathVariable @NotNull String identification) throws Exception {
       log.debug("Request to get Customer by id: {}", identification);
       return new ResponseEntity<>(customerService.findCustomerByIdentification(identification), HttpStatus.OK);
   }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> finCustomerById(@NotNull @PathVariable Long customerId) throws Exception {
        return new ResponseEntity<>(customerService.findCustomerById(customerId), HttpStatus.OK);
    }

   @GetMapping("")
   public ResponseEntity<List<CustomerDTO>> findAllCustomers() throws Exception {
       log.debug("Request to get all Customers");
       return new ResponseEntity<>(customerService.findAllCustomers(), HttpStatus.OK);
   }

    @GetMapping("/name/{customerId}")
    public ResponseEntity<?> findCustomerByName(@NotNull @PathVariable Long customerId) throws Exception {
        return new ResponseEntity<>(customerService.findCustomerByName(customerId), HttpStatus.OK);
    }



}
