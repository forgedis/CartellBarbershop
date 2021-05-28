package db;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import model.Reservation;

public interface ReservationIF {


	void createReservation(String reservationNo, LocalDate date, LocalTime time, String CustomerPhoneNo, String barberName, String serviceName) throws DataAccessException;
	
	void assignNewServiceToReservation(int customer_id, String serviceName) throws DataAccessException;

	List<Reservation> findReservation(String reservationNo) throws DataAccessException;

	void deleteReservation(String reservationNo) throws DataAccessException;

	void updateReservation(LocalDate newDate, LocalTime newTime, String phoneNo, String barberName, String reservationNo) throws DataAccessException;

	List<Reservation> printAllReservations() throws DataAccessException;
}
