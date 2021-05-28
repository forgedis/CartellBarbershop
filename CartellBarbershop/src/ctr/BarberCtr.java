package ctr;

import java.sql.SQLException;
import java.util.List;

import db.BarberDB;
import db.BarberIF;
import db.DBConnection;
import db.DataAccessException;
import model.Barber;

public class BarberCtr {
	private BarberIF barberDB;
	
	public BarberCtr() throws DataAccessException {
		barberDB = new BarberDB();
	}
	
	public void createBarber(String name) throws DataAccessException, SQLException {
		try {
			DBConnection.getInstance().startTransaction();
			DBConnection.getInstance().setAutoCommit(false);
			
			barberDB.createBarber(name);
			
			DBConnection.getInstance().commitTransaction();
			DBConnection.getInstance().setAutoCommit(true);
		} catch(DataAccessException e) {
			DBConnection.getInstance().rollbackTransaction();
		}
	}
	
	public List<Barber> findBarberByName(String name) throws DataAccessException {
		return barberDB.findBarberByName(name);
		
	}
	public void removeBarber(String name) throws DataAccessException, SQLException {
		try {
			DBConnection.getInstance().startTransaction();
			DBConnection.getInstance().setAutoCommit(false);
			
			barberDB.removeBarberByName(name);
			
			DBConnection.getInstance().commitTransaction();
			DBConnection.getInstance().setAutoCommit(true);
		} catch(DataAccessException e) {
			DBConnection.getInstance().rollbackTransaction();
		}
	}
	
	public void updateBarber(String name, String newName) throws DataAccessException, SQLException {
		try {
			DBConnection.getInstance().startTransaction();
			DBConnection.getInstance().setAutoCommit(false);
			
			barberDB.updateBarber(name, newName);
			
			DBConnection.getInstance().commitTransaction();
			DBConnection.getInstance().setAutoCommit(true);
		} catch(DataAccessException e) {
			DBConnection.getInstance().rollbackTransaction();
		}
	}
	
	public List<Barber> printAllBarbers() throws DataAccessException {
		return barberDB.printAllBarbers();
	}
}
