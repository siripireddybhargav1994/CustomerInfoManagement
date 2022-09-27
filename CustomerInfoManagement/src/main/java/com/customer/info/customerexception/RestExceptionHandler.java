package com.customer.info.customerexception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class RestExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class) // exception handled
	public ResponseEntity<ErrorResponse> handleNullPointerExceptions(Exception e) {
		ErrorResponse errresp = new ErrorResponse();
		List<Error> errlist = new ArrayList();
		Error error = new Error();
		error.setErrorcode("CUST_NOT_FOUND_101");
		error.setErrordescription("Customer deatails not found");
		error.setStatuscode(String.valueOf(HttpStatus.NOT_FOUND));
		Map<String, Object> addinfo = new HashMap();
		addinfo.put("SubCode", "Data not found");
		error.setAddinfo(addinfo);
		errlist.add(error);
		errresp.setErrors(errlist);
		return ResponseEntity.internalServerError().body(errresp);
	}

	@ExceptionHandler(CustomerAlreadyExistException.class) // exception handled
	public ResponseEntity<ErrorResponse> UserAlreadyExistException(Exception e) {
		ErrorResponse errresp = new ErrorResponse();
		List<Error> errlist = new ArrayList();
		Error error = new Error();
		error.setErrorcode("CUST_CONFLICT_102");
		error.setErrordescription("Duplicate customer deatails found");
		error.setStatuscode(String.valueOf(HttpStatus.CONFLICT));
		Map<String, Object> addinfo = new HashMap();
		addinfo.put("SubCode", "Duplicate data found");
		error.setAddinfo(addinfo);
		errlist.add(error);
		errresp.setErrors(errlist);
		return ResponseEntity.internalServerError().body(errresp);
	}

	@ExceptionHandler(Exception.class) // exception handled
	public ResponseEntity<ErrorResponse> handleExceptions(Exception e) {
		ErrorResponse errresp = new ErrorResponse();
		List<Error> errlist = new ArrayList();
		Error error = new Error();
		error.setErrorcode("CUST_BAD_REQUEST_103");
		error.setErrordescription("Customer deatails not found");
		error.setStatuscode(String.valueOf(HttpStatus.BAD_REQUEST));
		Map<String, Object> addinfo = new HashMap();
		addinfo.put("SubCode", "Bad Request");
		error.setAddinfo(addinfo);
		errlist.add(error);
		errresp.setErrors(errlist);
		return ResponseEntity.internalServerError().body(errresp);
	}
}
