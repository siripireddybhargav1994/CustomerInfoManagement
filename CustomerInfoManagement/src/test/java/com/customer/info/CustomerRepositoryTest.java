package com.customer.info;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.customer.info.model.Address;
import com.customer.info.model.CustomerInfo;
import com.customer.info.repository.CustomerRepository;

public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    // JUnit test for saveEmployee
    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveEmployeeTest(){

        CustomerInfo customer = new CustomerInfo();
        Address address=new Address();
        Set addressset=new HashSet();
        
        address.setAddressid(1);
        address.setAddressid(2);
        address.setZipcode("1234");
        addressset.add(address);
        customer.setName("Ramesh");
        customer.setAge(44);
        customer.setAddress(addressset);   
        customerRepository.save(customer);

        Assertions.assertThat(customer.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getEmployeeTest(){

        CustomerInfo customer = customerRepository.findById(1L).get();

        Assertions.assertThat(customer.getId()).isEqualTo(1L);

        
    }

    @Test
    @Order(3)
    public void getListOfEmployeesTest(){

        List<CustomerInfo> customers = customerRepository.findAll();

        Assertions.assertThat(customers.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateEmployeeTest(){

        CustomerInfo customer = customerRepository.findById(1L).get();

        customer.setAge(25);

        CustomerInfo customerUpdated =  customerRepository.save(customer);

        Assertions.assertThat(customerUpdated.getAge()).isEqualTo(25);

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmployeeTest(){

        CustomerInfo customer = customerRepository.findById(1L).get();

        customerRepository.delete(customer);

        customerRepository.deleteById(1L);

        CustomerInfo customerInfo = null;

        Optional<CustomerInfo> optionalCustomer = customerRepository.findById(1L);

        if(optionalCustomer.isPresent()){
            customerInfo = optionalCustomer.get();
        }

        Assertions.assertThat(customerInfo).isNull();
    }

}
