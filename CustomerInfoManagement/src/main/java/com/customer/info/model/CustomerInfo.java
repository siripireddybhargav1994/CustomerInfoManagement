package com.customer.info.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name ="CustomerInfo")
public class CustomerInfo {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name ="Name",nullable = false)
	private String name;
	@Column(name ="Age",nullable = false)
	private int age;	
	@CreationTimestamp
	@Column(name ="RegDate",nullable = false,updatable = false)
	private Date regDate;
	@UpdateTimestamp
	@Column(name ="LastUpdInfo",nullable = false,updatable = false)
	private Date lastUpdInfo;
	//cascade = {CascadeType.PERSIST,CascadeType.MERGE}
	@OneToMany(targetEntity = Address.class,cascade = CascadeType.ALL)
	@JoinColumn(name ="CustomerId",referencedColumnName = "id")
	Set<Address> address;
}
