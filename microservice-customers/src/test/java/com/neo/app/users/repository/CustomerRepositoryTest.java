package com.neo.app.users.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.neo.app.users.domain.Customer;



@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void testFindCustomerByIdentification() {
        Customer customer = new Customer();
        customer.setAddress("Calle 123");
        customer.setAge(25);
        customer.setGender("M");
        customer.setIdentification("123456");
        customer.setName("Juan");
        customer.setPassword("123");
        customer.setPhoneNumber("3187533");
        customer.setState('A');
        entityManager.persist(customer);

        Customer customerFound = customerRepository.findByIdentification("123456");
        assertNotNull(customerFound);
        assertEquals(customer.getIdentification(), customerFound.getIdentification());
    }

    @Test
    public void testUpdateCustomer() {
        Customer customer = new Customer();
        customer.setAddress("Calle 789");
        customer.setAge(40);
        customer.setGender("M");
        customer.setIdentification("9012");
        customer.setName("Pedro");
        customer.setPassword("789");
        customer.setPhoneNumber("6543210");
        customer.setState('A');
        entityManager.persist(customer);

        Customer customerToUpdate = customerRepository.findByIdentification("9012");
        assertNotNull(customerToUpdate);
        assertEquals(customer.getIdentification(), customerToUpdate.getIdentification());

        customerToUpdate.setName("Updated Name");
        customerRepository.save(customerToUpdate);

        Customer updatedCustomer = customerRepository.findByIdentification("9012");
        assertNotNull(updatedCustomer);
        assertEquals("Updated Name", updatedCustomer.getName());
    }

    


}
