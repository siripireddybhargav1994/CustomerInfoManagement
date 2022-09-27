package com.customer.info.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.info.customerexception.CustomerNotFoundException;
import com.customer.info.model.CustomerInfo;
import com.customer.info.repository.CustomerRepository;
import com.customer.info.repository.CustomerResponse;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;


	public CustomerInfo saveCustomer(CustomerInfo customerInfo) throws Exception {
	
		CustomerInfo customer = customerRepository.save(customerInfo);
		return customer;
	}

	public Object updateCustomer(CustomerInfo customerInfo,Long id) {
		
	CustomerInfo customer = customerRepository.save(customerInfo);
		
		return customer;
	}

	public List<CustomerResponse> getCustomerByZipcode(String zipcode) throws CustomerNotFoundException {

		List<CustomerResponse> customer = customerRepository.getInfoByZipcode(zipcode);
		
		for(CustomerResponse cust:customer) {
		cust.setZipcode(maskZipcode(cust.getZipcode()));
		}
		if (customer.size()==0||customer.isEmpty()) {
			throw new CustomerNotFoundException("Customer Not Found");
		}
		return customer;
	}

	public Optional<CustomerInfo> deleteCustomerById(Long id) throws Exception {
		Optional<CustomerInfo> customer;

		customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			customerRepository.delete(customer.get());
		}
		return customer;
	}
	
	public String maskZipcode(String zipcode){
		 long starttime = System.currentTimeMillis();
	        int total = zipcode.length();
	        int startlen=2,endlen = 2;
	        int masklen = total-(startlen+endlen) ;
	        StringBuffer maskedbuf = new StringBuffer(zipcode.substring(0,startlen));
	        for(int i=0;i<masklen;i++) {
	            maskedbuf.append('X');
	        }
	        maskedbuf.append(zipcode.substring(startlen+masklen, total));
	        String masked = maskedbuf.toString();

	        return masked;
	}
	
}
