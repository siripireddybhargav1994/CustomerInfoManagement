package com.customer.info;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.customer.info.model.CustomerInfo;

@ExtendWith(MockitoExtension.class)
public class AddressTest {	
		
		@Test
		public void testGetzipcode() {
	CustomerInfo customer = new CustomerInfo();
	customer.getAddress().iterator().next().setZipcode("11111111");
	Assertions.assertTrue(customer.getAddress().iterator().next().getZipcode().equals("11111111"));
		}
	
	}
