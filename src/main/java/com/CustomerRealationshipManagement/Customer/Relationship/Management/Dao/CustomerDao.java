package com.CustomerRealationshipManagement.Customer.Relationship.Management.Dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.CustomerRealationshipManagement.Customer.Relationship.Management.Entity.Customer;

import jakarta.transaction.Transactional;

@Repository
public class CustomerDao {

    private final SessionFactory sessionFactory;

    public CustomerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public String insertCustomer(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.persist(customer);
            transaction.commit();
            return "Customer inserted successfully";
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return "Error while inserting customer: " + e.getMessage();
        } finally {
            session.close();
        }
    }

    public List<Customer> getCustomersList() {
        Session session = sessionFactory.openSession();
        List<Customer> customers = session.createQuery("from Customer", Customer.class).getResultList();
        session.close();
        return customers;
    }
    @Transactional
    public Customer getCustomerById(int id) {
    	Session session = sessionFactory.openSession();
        return session.get(Customer.class, id);
    }
    
    public String customerUpdate(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction tr = null;

        try {
            tr = session.beginTransaction();

            Customer existing = session.get(Customer.class, customer.getId());
            if (existing == null) {
                return "Customer with ID " + customer.getId() + " does not exist.";
            }

            session.merge(customer); 
            tr.commit();
            return "Customer updated successfully";
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            e.printStackTrace();
            return "Error during update: " + e.getMessage();
        } finally {
            session.close();
        }
    }
    
    public String deleteCustomerById(int id)
    {
    	Session session= sessionFactory.openSession();
    	Transaction tr=session.beginTransaction();
    	Customer customer=session.get(Customer.class, id);

        if (customer == null) {
            session.close();
            return "Customer not found";
        }
    	session.remove(customer);
    	tr.commit();
    	session.close();
    	
    	return "Customer deleted Successfully";
    	
    }
    
    public String insertMultipleCustomers(List<Customer> customers) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();

        for (Customer customer : customers) {
            session.merge(customer); 
        }

        tr.commit();
        session.close();

        return "All customers inserted successfully";
    }

    public List<Customer> getCustomersByFirstName(String firstName) {
        Session session = sessionFactory.openSession();

        List<Customer> customers = session
            .createQuery("FROM Customer c WHERE c.firstName = :firstName", Customer.class)
            .setParameter("firstName", firstName)
            .getResultList();

        session.close();
        return customers;
    }
    
    public List<Customer> getCustomersByLastName(String lastName) {
        Session session = sessionFactory.openSession();

        List<Customer> customers = session
            .createQuery("FROM Customer c WHERE c.lastName = :lastName", Customer.class)
            .setParameter("lastName", lastName)
            .getResultList();

        session.close();
        return customers;
    }

    
    public List<Customer> getCustomersByLessThanAge(int age) {
        Session session = sessionFactory.openSession();
        List<Customer> customers = null;

        try {
            String hql = "FROM Customer WHERE age < :age";
            Query<Customer> query = session.createQuery(hql, Customer.class);
            query.setParameter("age", age);
            customers = query.getResultList();
        } 
        finally {
            session.close();
        }

        return customers;
    }
    
    public List<Customer> getCustomersByMoreThanAge(int age) {
        Session session = sessionFactory.openSession();
        List<Customer> customers = null;
        
        try {
            String hql = "FROM Customer WHERE age > :age";
            Query<Customer> query = session.createQuery(hql, Customer.class);
            query.setParameter("age", age);
            customers = query.getResultList();
            
        } finally {
            session.close();
        }            
        return customers;
    }

    public List<Customer> getCustomersByAge(int age) {
        Session session = sessionFactory.openSession();
        List<Customer> customers;

        try {
            String hql = "FROM Customer WHERE age = :age";
            Query<Customer> query = session.createQuery(hql, Customer.class);
            query.setParameter("age", age);
            customers = query.getResultList();

            if (customers == null || customers.isEmpty()) {
                System.out.println("No customers found with age " + age);
                customers = Collections.emptyList();
            }

        } finally {
            session.close();
        }

        return customers;
    }
    
    public List<Customer> getCustomersByEmail(String email) {
        Session session = sessionFactory.openSession();

        List<Customer> customers = session
            .createQuery("FROM Customer c WHERE c.email = :email", Customer.class)
            .setParameter("email", email)
            .getResultList();

        session.close();
        return customers;
    }

    public List<Customer> getCustomersByMobileNumber(String mobileNumber) {
        Session session = sessionFactory.openSession();

        List<Customer> customers = session
            .createQuery("FROM Customer c WHERE c.mobileNumber = :mobileNumber", Customer.class)
            .setParameter("mobileNumber", mobileNumber)
            .getResultList();

        session.close();
        return customers;
    }
    
    public String updateCustomerFirstName(int id, String firstName) { 
    	Session session = sessionFactory.openSession();
    Transaction transaction = null;

    try {
        transaction = session.beginTransaction();

        Customer customer = session.get(Customer.class, id);
        if (customer == null) {
            return "Customer with ID " + id + " not found.";
        }

        customer.setFirstName(firstName); 
        transaction.commit();
        return "First name updated successfully for customer ID " + id;

    } catch (Exception e) {
        if (transaction != null) transaction.rollback();
        e.printStackTrace();
        return "Error occurred while updating first name.";

    } finally {
        session.close();
    }
}

    public String updateCustomerLastName(int id, String lastName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Customer customer = session.get(Customer.class, id);
            if (customer == null) {
                return "Customer with ID " + id + " not found.";
            }

            customer.setLastName(lastName); // ✅ Update last name here

            transaction.commit();
            return "Last name updated successfully for customer ID " + id;

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return "Error occurred while updating last name.";

        } finally {
            session.close();
        }
    }
    
    public String updateCustomerEmail(int id, String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Customer customer = session.get(Customer.class, id);
            if (customer == null) {
                return "Customer with ID " + id + " not found.";
            }

            customer.setEmail(email);

            transaction.commit();
            return "Email updated successfully for customer ID " + id;

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return "Error occurred while updating Email Id.";

        } finally {
            session.close();
        }
    }
    
    public String updateCustomerMobileNumber(int id, String mobileNumber) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Customer customer = session.get(Customer.class, id);
            if (customer == null) {
                return "Customer with ID " + id + " not found.";
            }

            customer.setMobileNumber(mobileNumber); 
            transaction.commit();
            return "Mobile Number updated successfully for customer ID " + id;

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return "Error occurred while updating mobile number";

        } finally {
            session.close();
        }
    }
    
    public String updateCustomerAge(int id, int age) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Customer customer = session.get(Customer.class, id);
            if (customer == null) {
                return "Customer with ID " + id + " not found.";
            }

            customer.setAge(age); 
            transaction.commit();
            return "Age updated successfully for customer ID " + id;

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return "Error occurred while updating age";

        } finally {
            session.close();
        }
    }
    
    public List<String> getCustomersFirstNames() {
        Session session = sessionFactory.openSession();
        List<String> firstNames = session.createQuery("SELECT c.firstName FROM Customer c", String.class).getResultList();
        session.close();
        return firstNames;
    }

    public List<String> getCustomersLastNames() {
        Session session = sessionFactory.openSession();
        List<String> lastNames = session.createQuery("SELECT c.lastName FROM Customer c", String.class).getResultList();
        session.close();
        return lastNames;
    }

    public long getCustomerCount() {
        Session session = sessionFactory.openSession();
        Long count = session.createQuery("SELECT COUNT(c.id) FROM Customer c", Long.class).getSingleResult();
        session.close();
        return count;
    }
    
    public List<Customer> getCustomersBetweenAges(int minAge, int maxAge) {
        Session session = sessionFactory.openSession();
        List<Customer> list = session.createQuery(
            "FROM Customer c WHERE c.age BETWEEN :min AND :max", Customer.class)
            .setParameter("min", minAge)
            .setParameter("max", maxAge)
            .getResultList();
        session.close();
        return list;
    }
    
    public List<Object[]> groupCustomersByAge() {
        Session session = sessionFactory.openSession();
        List<Object[]> result = session.createQuery(
            "SELECT c.age, COUNT(c.id) FROM Customer c GROUP BY c.age", Object[].class)
            .getResultList();
        session.close();
        return result;
    }




}
