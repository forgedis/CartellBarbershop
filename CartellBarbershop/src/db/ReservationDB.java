package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.Reservation;

public class ReservationDB implements ReservationIF {
	
	private static final String INSERT_Q = "insert into Reservation(reservationNo, date, time, customer_id, barber_id) values(?, ?, ?, (select id from Customer where phoneNo = ?), (select id from Barber where name = ?))";
	private static final String SERVICE_Q = "insert into Reservation_Service (reservation_id, service_id) values ((select id from Reservation where reservationNo = ?), (select id from Service where name = ?))";
	private static final String FIND_Q = "select * from Reservation where reservationNo = ?";
	private static final String DELETE_Q = "delete from Reservation_Service where reservation_id = (select id from Reservation where reservationNo = ?)" + "delete from Reservation where reservationNo = ?";
	private static final String UPDATE_Q = "update Reservation set date = ?, time = ?, barber_id = (select id from Barber where name = ?), customer_id = (select id from Customer where phoneNo = ?) where reservationNo = ?";
	private PreparedStatement insert, service, find, delete, update;
	
	public ReservationDB() throws DataAccessException {
		try {
			insert = DBConnection.getInstance().getConnection().prepareStatement(INSERT_Q);
			service = DBConnection.getInstance().getConnection().prepareStatement(SERVICE_Q);
			find = DBConnection.getInstance().getConnection().prepareStatement(FIND_Q);
			delete =  DBConnection.getInstance().getConnection().prepareStatement(DELETE_Q);
			update = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_Q);
		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare statement", e);
		}
	}

	@Override
	public void createReservation(String reservationNo, LocalDate date, LocalTime time, String CustomerPhoneNo, String barberName, String serviceName) throws DataAccessException {
		try {
			insert.setString(1, reservationNo);
			insert.setDate(2, java.sql.Date.valueOf(date));
			insert.setTime(3, java.sql.Time.valueOf(time));
			insert.setString(4, CustomerPhoneNo);
			insert.setString(5, barberName);
			insert.executeUpdate();
			service.setString(1, reservationNo);
			service.setString(2, serviceName);
			service.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare statement", e);
		}
	}
	public void assignNewServiceToReservation(int Reservation_id, String serviceName) throws DataAccessException { // assign new service
		
		try {
			service.setInt(1, Reservation_id);
			service.setString(2, serviceName);
			service.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare statement", e);
		}
	}

	public List<Reservation> findReservation(String reservationNo) throws DataAccessException {
		List<Reservation> reservations = new ArrayList<>();
		
		try {
			find.setString(1, reservationNo);
			ResultSet rs = find.executeQuery();
			Reservation r = null;
			if(rs.next()) {
				r = buildObject(rs);
				reservations.add(r);
			}
			return reservations;
		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve data", e);
		}
	}
	
	private Reservation buildObject(ResultSet rs) throws DataAccessException {
		try {
			Reservation r = new Reservation(rs.getString("reservationNo"), rs.getDate("date").toLocalDate(), rs.getTime("time").toLocalTime(), rs.getInt("customer_id"), rs.getInt("barber_id"));
			return r;
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result", e);
		}
	}

	@Override
	public void deleteReservation(String reservationNo) throws DataAccessException {
		try {
			delete.setString(1, reservationNo);
			delete.setString(2, reservationNo);
			delete.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result", e);
		}
	}

	@Override
	public void updateReservation(LocalDate newDate, LocalTime newTime, String barberName,
			String phoneNo, String reservationNo) throws DataAccessException {
		try {
			update.setDate(1, java.sql.Date.valueOf(newDate));
			update.setTime(2, java.sql.Time.valueOf(newTime));
			update.setString(3, barberName);
			update.setString(4, phoneNo);
			update.setString(5, reservationNo);
			update.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result", e);
		}
	}
	
	
	public List<Reservation> printAllReservations() throws DataAccessException {
		List<Reservation> reservations = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = DBConnection.getInstance().getConnection().createStatement();
			rs = stmt.executeQuery("select * from Reservation");
			
			while(rs.next()) {
				Reservation r = buildObject(rs);
				reservations.add(r);
			} return reservations;
		} catch(SQLException e) {
			throw new DataAccessException("could not retrieve data", e);
		}
	}

}

