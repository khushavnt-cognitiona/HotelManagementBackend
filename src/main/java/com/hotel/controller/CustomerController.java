package com.hotel.controller;

import java.util.List;

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

import com.hotel.entity.Customer;
import com.hotel.service.CustomerService;

@RestController
@RequestMapping("/customer/api")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	@GetMapping
	public ResponseEntity<List<Customer>>getAllcustomer(){
		List<Customer>customers=customerService.getAllCustomers();
		
		return new ResponseEntity<>(customers,HttpStatus.OK);
		
	}
	@PostMapping
	public ResponseEntity<Customer>createCustomer(@RequestBody Customer customer){
		Customer customer2=customerService.createCustomer(customer);
		return new ResponseEntity<Customer>(customer2,HttpStatus.CREATED);
		
	}
	@GetMapping("/{Id}")
	public ResponseEntity<Customer>findCustomerById(@PathVariable Long Id) throws Exception{
		Customer customer=customerService.findCustomerById(Id);
		return new ResponseEntity<>(customer,HttpStatus.OK);
		
	}
	
	//delete customer delete bye id
	@DeleteMapping("delete/{Id}")
	public void deleteCustomerById  (@PathVariable Long Id) {
		customerService.deleteCustomerById(Id);
		
	}
	
	@PutMapping("/update/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

	

}
