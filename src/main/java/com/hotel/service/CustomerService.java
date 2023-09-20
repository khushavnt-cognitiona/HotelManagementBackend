package com.hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.allException.FormatNotMatchException;
import com.hotel.allException.NotFondException;
import com.hotel.entity.Customer;
import com.hotel.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	public List<Customer> getAllCustomers() {

		return customerRepository.findAll();
	}

	public Customer createCustomer(Customer customer) {
		//store only validate Customer 
		validateCustomer(customer);

		return customerRepository.save(customer);
	}

	private void validateCustomer(Customer customer) {
	    if (customer.getPhoneNumber() == null || customer.getPhoneNumber().isBlank() ||
	        customer.getPhoneNumber().length() != 10) {
	        throw new FormatNotMatchException("Phone number should be exactly 10 digits long.");
	    }

	    if (customer.getEmail() == null || customer.getEmail().isBlank() ||
	        !customer.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
	        throw new FormatNotMatchException("Email should be in a valid format.");
	    }

	    if (customer.getFirstName() == null || customer.getFirstName().isBlank() ||
	        customer.getLastName() == null || customer.getLastName().isBlank() ||
	        customer.getAddress() == null || customer.getAddress().isBlank()) {
	        throw new FormatNotMatchException("First Name, Last Name, and Address are required.");
	    }
	}


	public Customer findCustomerById(Long id) throws Exception {
		Customer customer = customerRepository.findById(id).orElse(null);
		if (customer == null) {
			throw new NotFondException("Customer Not Found With this Id :" +id);
		}

		return customer;
	}

	public void deleteCustomerById(Long id) {
		Customer customer=customerRepository.findById(id).orElse(null);
		
		if(customer==null) {
			throw new NotFondException("Customer Not Found With this Id :" +id);
		}
		customerRepository.deleteById(id);
		
	}

	 public Customer updateCustomer(Long id, Customer customer) {
	        Customer existingCustomer = customerRepository.findById(id).orElse(null);
	        if (existingCustomer != null) {
	            existingCustomer.setFirstName(customer.getFirstName());
	            existingCustomer.setLastName(customer.getLastName());
	            existingCustomer.setEmail(customer.getEmail());
	            existingCustomer.setPhoneNumber(customer.getPhoneNumber());
	            existingCustomer.setAddress(customer.getAddress());
	            
	            return customerRepository.save(existingCustomer);
	        }
			throw new NotFondException("Customer Not Found With this Id  :" +id);
	        
	    }

}
