package com.customer.info.customerexception;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Error{

	
	private String errorcode;

	private String errordescription;

	private String statuscode;
	
	private Map<String, Object> addinfo;


}

