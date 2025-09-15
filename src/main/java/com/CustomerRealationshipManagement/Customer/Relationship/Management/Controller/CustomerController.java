package com.CustomerRealationshipManagement.Customer.Relationship.Management.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CustomerRealationshipManagement.Customer.Relationship.Management.Entity.Customer;
import com.CustomerRealationshipManagement.Customer.Relationship.Management.Service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertCustomer(@RequestBody Customer customer) {
        try {
            String message = customerService.insertCustomer(customer);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error inserting customer: " + e.getMessage());
        }
    }
    @GetMapping
public List<Customer> getCustomerList() {
		
		List<Customer> list= customerService.getCustomerList();
	return list;
    }
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable int id) {
    	return customerService.getCustomerById(id);
    	
    }
    @PutMapping
    public String customerUpdate(@RequestBody Customer customer) {
    	return customerService.customerUpdate(customer);
    	}
    @DeleteMapping("/{id}")
    public String customerDeleteById(@PathVariable int id)
    {
    	return customerService.deleteCustomerById(id);
    			
    }
    @PostMapping("/bulk-insert")
    public String insertMultipleCustomers(@RequestBody List<Customer> customers) {
    	return customerService.insertMultipleCustomers(customers);
    	
    }
	@GetMapping("/byname/{firstName}")
    public List<Customer> getCustomersByFirstName(@PathVariable String firstName){
    	return customerService.getCustomerByFirstName(firstName);
    }

	@GetMapping("/bylastname/{lastName}")
    public List<Customer> getCustomersByLastName(@PathVariable String lastName){
    	return customerService.getCustomerByLastName(lastName);
    }
	@GetMapping("/bylessthanage/{age}")
    public List<Customer> getCustomersByLessThanAge(@PathVariable int age){
    	return customerService.getCustomersByLessThanAge(age);
    }


	@GetMapping("/bymorethanage/{age}")
    public List<Customer> getCustomersByMoreThanAge(@PathVariable int age){
    	return customerService.getCustomersByMoreThanAge(age);
    }

	@GetMapping("/byage/{age}")
    public List<Customer> getCustomersByAge(@PathVariable int age){
    	return customerService.getCustomersByAge(age);
    }
	@GetMapping("/byemail/{email}")
    public List<Customer> getCustomersByEmail(@PathVariable String email){
    	return customerService.getCustomersByEmail(email);
    }
	@GetMapping("/bymobilenumber/{mobileNumber}")
    public List<Customer> getCustomersByMobileNumber(@PathVariable String mobileNumber){
    	return customerService.getCustomersByMobileNumber(mobileNumber);
    }
	@PutMapping("/update-firstname/{id}")
	public String updateCustomerFirstName(@PathVariable int id, @RequestBody Map<String, String> request) {
	    String firstName = request.get("firstName");
	    return customerService.updateCustomerFirstName(id, firstName);
	}

	@PutMapping("/update-lastname/{id}")
	public String updateCustomerLastName(@PathVariable int id, @RequestBody Map<String, String> request) {
	    String lastName = request.get("lastName");
	    return customerService.updateCustomerLastName(id, lastName);
	}
	
	@PutMapping("/update-email/{id}")
	public String updateCustomerEmail(@PathVariable int id, @RequestBody Map<String, String> request) {
	    String email = request.get("email");
	    return customerService.updateCustomerEmail(id, email);
	}

	@PutMapping("/update-mobilenumber/{id}")
	public String updateCustomerMobileNumber(@PathVariable int id, @RequestBody Map<String, String> request) {
	    String mobileNumber = request.get("mobileNumber");
	    return customerService.updateCustomerMobileNumber(id, mobileNumber);
	}
	
	@PutMapping("/update-age/{id}")
	public String updateCustomerAgee(@PathVariable int id, @RequestBody Map<String, Integer> request) {
	    int age = request.get("age");
	    return customerService.updateCustomerAge(id, age);
	}
	
	@GetMapping("/firstnames")
	public List<String> getAllFirstNames() {
	    return customerService.getCustomersFirstNames();
	}

	@GetMapping("/lastnames")
	public List<String> getAllLastNames() {
	    return customerService.getCustomersLastNames();
	}
	
	@GetMapping("/between-age/{min}/{max}")
	public List<Customer> getCustomersBetweenAgeRange(@PathVariable int min, @PathVariable int max) {
	    return customerService.getCustomersBetweenAges(min, max);
	}
	
	@GetMapping("/count")
	public long getCustomerCount() {
	    return customerService.getCustomerCount();
	}
	
	@GetMapping("/grouped-by-age")
	public List<Map<String, Object>> getCustomersGroupedByAge() {
	    List<Object[]> rawData = customerService.groupCustomersByAge();
	    List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
	    for (Object[] row : rawData) {
	        Map<String, Object> entry = new HashMap<String, Object>();
	        entry.put("age", row[0]);
	        entry.put("count", row[1]);
	        result.add(entry);
	    }
	    return result;
	}



}
