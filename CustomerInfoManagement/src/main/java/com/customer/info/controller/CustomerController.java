package com.customer.info.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.info.customerexception.CustomerNotFoundException;
import com.customer.info.customerexception.ErrorResponse;
import com.customer.info.model.CustomerInfo;
import com.customer.info.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/createCustomer")
	public Object saveCustomer(@RequestBody CustomerInfo customerInfo) {

		try {
			return new ResponseEntity<>(customerService.saveCustomer(customerInfo), HttpStatus.CREATED);
			
		}catch (CustomerNotFoundException e) {
			return handleCustomerNotFoundException();
		} catch (Exception e) {
			return handleGenericException(e);
		}
	}

	@PutMapping("/updateCustomer/{id}")
	public Object updateCustomer(@RequestBody CustomerInfo customer,@PathVariable Long id) {
		try {
			return new ResponseEntity<>(customerService.updateCustomer(customer,id), HttpStatus.OK);
		} catch (Exception e) {
			return handleGenericException(e);
		}
	}

	@GetMapping("/getCustomerByZipCode/{zipcode}")
	public Object getCustomerByZipCode(@PathVariable String zipcode) {
		try {
		Object customer = customerService.getCustomerByZipcode(zipcode);	
		
		return new ResponseEntity<>(customer, HttpStatus.OK);
		}catch(CustomerNotFoundException e) {
			return handleCustomerNotFoundException();
		}catch(Exception e) {
			return handleGenericException(e);
			}
	}

	@DeleteMapping("/customers/{id}")
	public Object deleteCustomerById(@PathVariable Long id) {
		try {
			Optional<Object> customer = Optional.ofNullable(customerService.deleteCustomerById(id));
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return handleGenericException(e);
		}
	}
	public ResponseEntity<Object> handleCustomerNotFoundException() {
		ErrorResponse errresp=new ErrorResponse();
		List<com.customer.info.customerexception.Error> errlist=new ArrayList();
		com.customer.info.customerexception.Error err= new com.customer.info.customerexception.Error();
		err.setErrorcode("CUST_NOT_FOUND_101");
		err.setErrordescription("Customer deatails not found");
		err.setStatuscode(String.valueOf(HttpStatus.NOT_FOUND));
		Map<String,Object> addinfo=new HashMap();
		addinfo.put("SubCode", "Data not found");
		err.setAddinfo(addinfo);
		errlist.add(err);
		errresp.setErrors(errlist);
		return ResponseEntity.internalServerError().body(errresp);
	}
	public ResponseEntity<Object> handleGenericException(Exception e) {
		ErrorResponse errresp=new ErrorResponse();
		List<com.customer.info.customerexception.Error> errlist=new ArrayList();
		com.customer.info.customerexception.Error err= new com.customer.info.customerexception.Error();
		err.setErrorcode("UNKNOW_101");
		err.setErrordescription(e.getMessage());
		err.setStatuscode(String.valueOf(HttpStatus.BAD_REQUEST));
		Map<String,Object> addinfo=new HashMap();
		addinfo.put("SubCode", e.getStackTrace());
		err.setAddinfo(addinfo);
		errlist.add(err);
		errresp.setErrors(errlist);
		return ResponseEntity.internalServerError().body(errresp);
	}
	
}
