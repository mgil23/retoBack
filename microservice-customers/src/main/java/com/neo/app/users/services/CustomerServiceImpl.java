package com.neo.app.users.services;


import com.neo.app.users.dto.CustomerDTO;
import com.neo.app.users.domain.Customer;
import com.neo.app.users.mapper.CustomerMapper;
import com.neo.app.users.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    //@Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public CustomerDTO createCustomer(Customer customer) throws Exception {
        if (customer == null) {
            throw new Exception("El cliente no puede ser nulo");
        }
        Customer existCustomer = customerRepository.findByIdentification(customer.getIdentification());
        if (existCustomer != null) {
            throw new Exception("El cliente con identificación " + customer.getIdentification() + "ya existe");
        }

        return customerMapper.customerToCustomerDTO(customerRepository.save(customer));
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public CustomerDTO updateCustomer(Customer customer) throws Exception {
        if (customer == null) {
            throw new Exception("El cliente no puede ser nulo");
        }
        return customerMapper.customerToCustomerDTO(customerRepository.save(customer));
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteCustomerById(Long id) throws Exception {
        if (id == null) {
            throw new Exception("El id: "+id+" del cliente no puede ser nulo");
        }
        if (!customerRepository.existsById(id)) {
            throw new Exception("El cliente no existe");
        }
        customerRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> findAllCustomers() throws Exception {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
           throw new Exception("No se encontraron clientes en la base de datos");
        } else {
            return customerMapper.customerListToCustomerDTOList(customers);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDTO findCustomerByIdentification(String identification) throws Exception {
        if (identification == null) {
            throw new Exception("La identificación: "+identification+" del cliente es nula");
        }
        Customer customer = customerRepository.findByIdentification(identification);

        if (customer == null) {
            throw new Exception("El cliente con identificación " + identification + "no existe");
        }
        return customerMapper.customerToCustomerDTO(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDTO findCustomerById(Long id) throws Exception {
        Customer customer = customerRepository.findById(id).orElseThrow( () -> new Exception("El cliente con id " + id + "no existe"));
        return customerMapper.customerToCustomerDTO(customer);
    }
}
