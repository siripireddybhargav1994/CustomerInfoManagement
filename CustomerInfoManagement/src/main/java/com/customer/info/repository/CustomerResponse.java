package com.customer.info.repository;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerResponse {

	private String name;
	private int age;	
	private Date regDate;
	private Date lastUpdInfo;
	private String zipcode;
}
