package db;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import model.Price;
import model.Service;

public interface ServiceIF {
	
	public List<Service> findServiceByName(String name) throws DataAccessException;

	public void createService(String name, String description, LocalTime duration, double price) throws DataAccessException;

	public void deleteService(String name) throws DataAccessException;
	
	public void updateService(String name, String newName, String newDescription, LocalTime newDuration,
			double price) throws DataAccessException;

	public List<Service> printAllServices() throws DataAccessException;
	
	
	
	public void createPrice(LocalDate fromDate, double price) throws DataAccessException;

	public List<Price> findPriceByDate(LocalDate fromDate) throws DataAccessException;

	public void deletePrice(LocalDate fromDate) throws DataAccessException;

	public void updatePrice(LocalDate fromDate, LocalDate newFromDate, double newPrice) throws DataAccessException;

	public List<Price> printAllPrices() throws DataAccessException;


	
}
