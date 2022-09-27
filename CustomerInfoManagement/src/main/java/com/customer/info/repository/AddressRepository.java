package com.customer.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.info.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
