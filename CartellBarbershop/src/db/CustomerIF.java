package db;

import java.util.List;

import model.Customer;

public interface CustomerIF {
	
	public void createCustomer(String firstName, String lastName, String phoneNo) throws DataAccessException;
	
	public List<Customer> findCustomerByPhone(String phoneNo) throws DataAccessException;

	public void removeCustomerByPhone(String phoneNo) throws DataAccessException;
	
	public void updateCustomer(String phoneNo, String newFirstName, String newLastName, String newPhoneNo) throws DataAccessException;

	public List<Customer> printAllCustomers() throws DataAccessException;

	}