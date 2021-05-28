package ctr;

import java.sql.SQLException;
import java.util.List;

import db.CustomerDB;
import db.CustomerIF;
import db.DBConnection;
import db.DataAccessException;
import model.Customer;

public class CustomerCtr {
	private CustomerIF customerDB;
	
	
		public CustomerCtr() throws DataAccessException {
			customerDB = new CustomerDB();
		
		}
	
		public void createCustomer(String firstName, String lastName, String phoneNo) throws DataAccessException, SQLException {
			try {
				DBConnection.getInstance().startTransaction();
				DBConnection.getInstance().setAutoCommit(false);
				
				customerDB.createCustomer(firstName, lastName, phoneNo);
				
				DBConnection.getInstance().commitTransaction();
				DBConnection.getInstance().setAutoCommit(true);
			} catch(DataAccessException e) {
				DBConnection.getInstance().rollbackTransaction();
			}
		}
		

		public List<Customer> findCustomer(String phoneNo) throws DataAccessException {
			return customerDB.findCustomerByPhone(phoneNo);
		
		}
		
		public void deleteCustomer(String phoneNo) throws DataAccessException, SQLException {
			try {
				DBConnection.getInstance().startTransaction();
				DBConnection.getInstance().setAutoCommit(false);
				
				customerDB.removeCustomerByPhone(phoneNo);
				
				DBConnection.getInstance().commitTransaction();
				DBConnection.getInstance().setAutoCommit(true);
			} catch(DataAccessException e) {
				DBConnection.getInstance().rollbackTransaction();
			}
		}
		
		public void updateCustomer(String phoneNo, String newFirstName, String newLastName, String newPhoneNo) throws DataAccessException, SQLException {
			
			try {
				DBConnection.getInstance().startTransaction();
				DBConnection.getInstance().setAutoCommit(false);
				
				customerDB.updateCustomer(phoneNo, newFirstName, newLastName, newPhoneNo);
				
				DBConnection.getInstance().commitTransaction();
				DBConnection.getInstance().setAutoCommit(true);
			} catch(DataAccessException e) {
				DBConnection.getInstance().rollbackTransaction();
			}
		}
		
		
		public List<Customer> printAllCustomers() throws DataAccessException {
			return customerDB.printAllCustomers();
		}
}
