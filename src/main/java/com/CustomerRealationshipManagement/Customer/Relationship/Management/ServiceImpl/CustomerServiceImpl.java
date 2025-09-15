package com.CustomerRealationshipManagement.Customer.Relationship.Management.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.CustomerRealationshipManagement.Customer.Relationship.Management.Dao.CustomerDao;
import com.CustomerRealationshipManagement.Customer.Relationship.Management.Entity.Customer;
import com.CustomerRealationshipManagement.Customer.Relationship.Management.Service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {

	
	CustomerDao customerDao;
	
	public CustomerServiceImpl(CustomerDao customerDao) {
		super();
		this.customerDao = customerDao;
	}
	@Override
	public String insertCustomer(Customer customer) {
		
		return customerDao.insertCustomer(customer);
	}
	@Override
	public List<Customer> getCustomerList() {
		return customerDao.getCustomersList();
	}
	@Override
	public Customer getCustomerById(int id) {
     Customer customer=customerDao.getCustomerById(id);
		return customer;
	}
	@Override
	public String customerUpdate(Customer customer) {
		return customerDao.customerUpdate(customer);
	}
	@Override
	public String deleteCustomerById(int id) {
		
		return customerDao.deleteCustomerById(id);
	}
	@Override
	public String insertMultipleCustomers(List<Customer> customers) {
		return customerDao.insertMultipleCustomers(customers);
				
	}
	@Override
	public List<Customer> getCustomerByFirstName(String firstName) {
		
		return customerDao.getCustomersByFirstName(firstName);
	}
	@Override
	public List<Customer> getCustomerByLastName(String lastName) {

		return customerDao.getCustomersByLastName(lastName);
	}	
	@Override
	public List<Customer> getCustomersByLessThanAge(int age) {
		return customerDao.getCustomersByLessThanAge(age);
		}
	@Override
	public List<Customer> getCustomersByMoreThanAge(int age) {
		return customerDao.getCustomersByMoreThanAge(age);
	}
	@Override
	public List<Customer> getCustomersByAge(int age) {
		return customerDao.getCustomersByAge(age);

	}
	@Override
	public List<Customer> getCustomersByEmail(String email) {
		return customerDao.getCustomersByEmail(email);	
		}
	@Override
	public List<Customer> getCustomersByMobileNumber(String mobileNumber)
	{		
		return customerDao.getCustomersByMobileNumber(mobileNumber);
	}
	@Override
	public String updateCustomerFirstName(int id, String firstName) {
		return customerDao.updateCustomerFirstName(id, firstName);
	}
	@Override
	public String updateCustomerLastName(int id, String lastName) {
		return customerDao.updateCustomerLastName(id, lastName);
			
	}
	@Override
	public String updateCustomerMobileNumber(int id, String mobileNumber) {
		return customerDao.updateCustomerMobileNumber(id, mobileNumber);
	}
	@Override
	public String updateCustomerEmail(int id, String email) {
		return customerDao.updateCustomerEmail(id, email);
	}
	@Override
	public String updateCustomerAge(int id, int age) {
		return customerDao.updateCustomerAge(id, age);
	}
	@Override
	public List<String> getCustomersFirstNames() {
		return customerDao.getCustomersFirstNames();
	}
	@Override
	public List<String> getCustomersLastNames() {
		return customerDao.getCustomersLastNames();
	}
	@Override
	public long getCustomerCount() {
		return customerDao.getCustomerCount();
	}
	@Override
	public List<Object[]> groupCustomersByAge() {
		// TODO Auto-generated method stub
		return customerDao.groupCustomersByAge();
	}
	@Override
	public List<Customer> getCustomersBetweenAges(int minAge, int maxAge) {
		// TODO Auto-generated method stub
		return customerDao.getCustomersBetweenAges(minAge, maxAge);
	}
	
	
}
