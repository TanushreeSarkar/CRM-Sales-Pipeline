package com.CustomerRealationshipManagement.Customer.Relationship.Management.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.CustomerRealationshipManagement.Customer.Relationship.Management.Entity.Customer;
@Service

public interface CustomerService {

	String insertCustomer(Customer customer);
	
	List<Customer> getCustomerList();
	Customer getCustomerById(int id);
	String customerUpdate(Customer customer);
	String deleteCustomerById(int id);
	String insertMultipleCustomers(List<Customer> customers);
	String updateCustomerFirstName(int id, String firstName);
	String updateCustomerLastName(int id, String lastName);
	String updateCustomerMobileNumber(int id, String mobileNumber);
	String updateCustomerEmail(int id, String email);
	String updateCustomerAge(int id, int age);
	 long getCustomerCount();
	 List<Object[]> groupCustomersByAge();


	
	List <Customer> getCustomerByFirstName(String firstName);
	List <Customer> getCustomerByLastName(String lastName);
	List<Customer> getCustomersByLessThanAge(int age);
	List<Customer> getCustomersByMoreThanAge(int age);
	List<Customer> getCustomersByAge(int age);
	List <Customer> getCustomersByEmail(String email);
	List<Customer> getCustomersByMobileNumber(String mobileNumber);
	List <String> getCustomersFirstNames();
	List <String> getCustomersLastNames();
	List<Customer> getCustomersBetweenAges(int minAge, int maxAge);

}
