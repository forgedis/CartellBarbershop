package ctr;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import db.DBConnection;
import db.DataAccessException;
import db.ReservationDB;
import db.ReservationIF;
import model.Reservation;


public class ReservationCtr {

	private ReservationIF reservationDB;
	
	public ReservationCtr() throws DataAccessException {
		reservationDB = new ReservationDB();

	}
	
	public void createReservation(String reservationNo, LocalDate date, LocalTime time, String CustomerPhoneNo, String barberName, String serviceName) throws DataAccessException, SQLException {
		try {
			DBConnection.getInstance().startTransaction();
			DBConnection.getInstance().setAutoCommit(false);
			
			reservationDB.createReservation(reservationNo, date, time, CustomerPhoneNo, barberName, serviceName); 			
			
			DBConnection.getInstance().commitTransaction();
			DBConnection.getInstance().setAutoCommit(true);
		} catch(DataAccessException e) {
			DBConnection.getInstance().rollbackTransaction();
		}		
	}
	
	public List<Reservation> findReservation(String reservationNo) throws DataAccessException {
		return reservationDB.findReservation(reservationNo);
	}
	
	public void deleteReservation(String reservationNo) throws DataAccessException, SQLException {
		try {
			DBConnection.getInstance().startTransaction();
			DBConnection.getInstance().setAutoCommit(false);
			
			reservationDB.deleteReservation(reservationNo);
			
			DBConnection.getInstance().commitTransaction();
			DBConnection.getInstance().setAutoCommit(true);
		} catch(DataAccessException e) {
			DBConnection.getInstance().rollbackTransaction();
		}		
	}
	
	public void updateReservation(LocalDate newDate, LocalTime newTime, String phoneNo , String barberName, String reservationNo) throws DataAccessException, SQLException {
		
		try {
			DBConnection.getInstance().startTransaction();
			DBConnection.getInstance().setAutoCommit(false);
			
			reservationDB.updateReservation(newDate, newTime, barberName, phoneNo, reservationNo);		
			
			DBConnection.getInstance().commitTransaction();
			DBConnection.getInstance().setAutoCommit(true);
		} catch(DataAccessException e) {
			DBConnection.getInstance().rollbackTransaction();
		}		
	}
	
	public List<Reservation> printAllReservations() throws DataAccessException {
		return reservationDB.printAllReservations();
	}
}
