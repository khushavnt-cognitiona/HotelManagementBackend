package com.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
}