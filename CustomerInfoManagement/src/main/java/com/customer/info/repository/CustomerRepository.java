package com.customer.info.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.customer.info.model.CustomerInfo;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerInfo, Long>{


	@Query("select new com.customer.info.repository.CustomerResponse(c.name,c.age,c.regDate,c.lastUpdInfo,a.zipcode) from CustomerInfo c JOIN c.address a where a.zipcode like %?1%")
	public List<CustomerResponse> getInfoByZipcode(String name);
	
		
}
