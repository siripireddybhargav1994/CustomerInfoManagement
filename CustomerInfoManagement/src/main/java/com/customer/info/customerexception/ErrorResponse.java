package com.customer.info.customerexception;

import java.util.List;

import lombok.Data;

@Data
public class ErrorResponse {
	private List<Error> errors;
	private Object meta;

}
