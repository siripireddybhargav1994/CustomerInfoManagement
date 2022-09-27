package com.customer.info;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.customer.info.model.CustomerInfo;

@ExtendWith(MockitoExtension.class)
public class CustomerInfoTest {

	@Test
	public void testGetAge() {
		CustomerInfo customer = new CustomerInfo();
		customer.setAge(1);
		Assertions.assertTrue(customer.getAge() == 1);
	}

	@Test
	public void testGetName() {
		CustomerInfo customer = new CustomerInfo();
		customer.setName("Ram");
		Assertions.assertTrue(customer.getName().equals("Ram"));
	}

	@Test
	public void testGetLastUpdDate() {
		CustomerInfo customer = new CustomerInfo();
		customer.setRegDate(new Date("2022-08-29 18:10:37.37"));
		Assertions.assertTrue(customer.getName().equals(new Date("2022-08-29 18:10:37.37")));
	}

}
