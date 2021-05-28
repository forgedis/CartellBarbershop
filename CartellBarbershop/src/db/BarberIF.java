package db;

import java.util.List;

import model.Barber;

public interface BarberIF {
	
	public List<Barber> findBarberByName(String name) throws DataAccessException;

	void removeBarberByName(String name) throws DataAccessException;

	void createBarber(String name) throws DataAccessException;

	public void updateBarber(String name, String newName) throws DataAccessException;

	public List<Barber> printAllBarbers() throws DataAccessException;
	
}
