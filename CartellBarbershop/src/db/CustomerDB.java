package db;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

public class CustomerDB implements CustomerIF {
	private static final String INSERT_Q = "insert into Customer (firstname, lastname, phoneNo) values (?, ?, ?)";
	private static final String FIND_BY_PHONE_Q = "select * from Customer where phoneNo = ?";
	private static final String DELETE_BY_PHONE_Q = "update Reservation set customer_id = null where customer_id = (select id from Customer where phoneNo = ?)" + "delete from Customer where phoneNo = ?";
	private static final String UPDATE_Q = "update Customer set firstname = ?, lastname = ?, phoneNo = ? where phoneNo = ?";
	private static final String GPN_Q= "select phoneNo from Customer where id = (select customer_id from Reservation)";
	private PreparedStatement insert, find, delete, update, gpn;
	
	public CustomerDB() throws DataAccessException {
		try {
			insert = DBConnection.getInstance().getConnection().prepareStatement(INSERT_Q);
			find = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_PHONE_Q);
			delete = DBConnection.getInstance().getConnection().prepareStatement(DELETE_BY_PHONE_Q);
			update = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_Q);
			gpn = DBConnection.getInstance().getConnection().prepareStatement(GPN_Q);
		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare statement", e);
		}
	}
		
	@Override
	public void createCustomer(String firstName, String lastName, String phoneNo) throws DataAccessException {
		try {
			insert.setString(1, firstName);
			insert.setString(2, lastName);
			insert.setString(3, phoneNo);
			insert.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve data", e);
		}
	}

	
	public List<Customer> findCustomerByPhone(String phoneNo) throws DataAccessException {
		List<Customer> customers = new ArrayList<>();
		
		try {
			find.setString(1, phoneNo);
			ResultSet rs = find.executeQuery();
			Customer c = null;
			if(rs.next()) {
				c = buildObject(rs);
				customers.add(c);
			}
			return customers;
		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve data", e);
		}
	}
	
	private Customer buildObject(ResultSet rs) throws DataAccessException {
		try {
			Customer c = new Customer(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("phoneNo"));
			return c;
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result", e);
		}
	}


	@Override
	public void removeCustomerByPhone(String phoneNo) throws DataAccessException {
		try {
			delete.setString(1, phoneNo);
			delete.setString(2, phoneNo);
			delete.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve data", e);
		}
	}	
	

	@Override
	public void updateCustomer(String phoneNo, String newFirstName, String newLastName, String newPhoneNo) throws DataAccessException {

		try {
			update.setString(1, newFirstName);
			update.setString(2, newLastName);
			update.setString(3, newPhoneNo);
			update.setString(4, phoneNo);
			update.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException("could not retrieve data", e);
		}

	}

	@Override
	public List<Customer> printAllCustomers() throws DataAccessException {
		List<Customer> customers = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = DBConnection.getInstance().getConnection().createStatement();
			rs = stmt.executeQuery("select * from Customer");
			
			while(rs.next()) {
				Customer c = buildObject(rs);
				customers.add(c);
			} return customers;
		} catch(SQLException e) {
			throw new DataAccessException("could not retrieve data", e);
		}
	}
}
