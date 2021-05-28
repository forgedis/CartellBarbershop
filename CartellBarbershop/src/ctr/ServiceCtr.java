package ctr;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import db.DBConnection;
import db.DataAccessException;
import db.ServiceDB;
import db.ServiceIF;
import model.Price;
import model.Service;

public class ServiceCtr {
	private ServiceIF serviceDB;
	
	public ServiceCtr() throws DataAccessException {
		serviceDB = new ServiceDB();
	}
	
	
	
	public void createService(String name, String description, LocalTime duration, double price) throws DataAccessException, SQLException {
		try {
			DBConnection.getInstance().startTransaction();
			DBConnection.getInstance().setAutoCommit(false);
			
			serviceDB.createService(name, description, duration, price);	
			
			DBConnection.getInstance().commitTransaction();
			DBConnection.getInstance().setAutoCommit(true);
		} catch(DataAccessException e) {
			DBConnection.getInstance().rollbackTransaction();
		}	
	}
	public List<Service> findServiceByName(String name) throws DataAccessException {
		return serviceDB.findServiceByName(name);
	}
	public void deleteService(String name) throws DataAccessException {
		serviceDB.deleteService(name);
	}
	public void updateService(String name, String newName, String newDescription, LocalTime newDuration, double price) throws DataAccessException, SQLException {
		try {
			DBConnection.getInstance().startTransaction();
			DBConnection.getInstance().setAutoCommit(false);
			
			serviceDB.updateService(name, newName, newDescription, newDuration, price);
			
			DBConnection.getInstance().commitTransaction();
			DBConnection.getInstance().setAutoCommit(true);
		} catch(DataAccessException e) {
			DBConnection.getInstance().rollbackTransaction();
		}	
	}
	
	public List<Service> printAllServices() throws DataAccessException {
		return serviceDB.printAllServices();
	}
	
	
	public void createPrice(LocalDate fromDate, double price) throws DataAccessException, SQLException {
		try {
			DBConnection.getInstance().startTransaction();
			DBConnection.getInstance().setAutoCommit(false);
			
			serviceDB.createPrice(fromDate, price);
			
			DBConnection.getInstance().commitTransaction();
			DBConnection.getInstance().setAutoCommit(true);
		} catch(DataAccessException e) {
			DBConnection.getInstance().rollbackTransaction();
		}	
	}
	public List<Price> findPriceByDate(LocalDate fromDate) throws DataAccessException {
		return serviceDB.findPriceByDate(fromDate);
	}
	public void deletePrice(LocalDate fromDate) throws DataAccessException, SQLException {
		try {
			DBConnection.getInstance().startTransaction();
			DBConnection.getInstance().setAutoCommit(false);
			
			serviceDB.deletePrice(fromDate);
			
			DBConnection.getInstance().commitTransaction();
			DBConnection.getInstance().setAutoCommit(true);
		} catch(DataAccessException e) {
			DBConnection.getInstance().rollbackTransaction();
		}	
	}
	public void updatePrice(LocalDate fromDate, LocalDate newFromDate, double newPrice) throws DataAccessException, SQLException {
		try {
			DBConnection.getInstance().startTransaction();
			DBConnection.getInstance().setAutoCommit(false);
			
			serviceDB.updatePrice(fromDate, newFromDate, newPrice);
			
			DBConnection.getInstance().commitTransaction();
			DBConnection.getInstance().setAutoCommit(true);
		} catch(DataAccessException e) {
			DBConnection.getInstance().rollbackTransaction();
		}	
	}
	public List<Price> printAllPrices() throws DataAccessException {
		return serviceDB.printAllPrices();
	}
}
