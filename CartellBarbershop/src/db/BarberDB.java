package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Barber;
import model.Customer;
import model.Barber;

public class BarberDB implements BarberIF {
	private static final String INSERT_Q = "insert into Barber (name) values (?)";
	private static final String FIND_BY_NUMBER_Q = "select * from Barber where name = ?";
	private static final String DELETE_BY_NUMBER_Q = "update Reservation set barber_id = null where barber_id = (select id from Barber where name = ?)" + "delete from Barber where name = ?"; 
	private static final String UPDATE_Q =	"update Barber set name = ? where name = ?";
	private PreparedStatement insert, find, delete, update;
	
	public BarberDB() throws DataAccessException {
		try {
			insert = DBConnection.getInstance().getConnection().prepareStatement(INSERT_Q);
			find = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_NUMBER_Q);
			delete = DBConnection.getInstance().getConnection().prepareStatement(DELETE_BY_NUMBER_Q);
			update = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_Q);
		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare statement", e);
		}
	}
	
	@Override
	public List<Barber> findBarberByName(String name) throws DataAccessException {
		List<Barber> barbers = new ArrayList<>();
		
		try {
			find.setString(1, name);
			ResultSet rs = find.executeQuery();
			Barber b = null;
			if(rs.next()) {
				b = buildObject(rs);
				barbers.add(b);
			}
			return barbers;
		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve data", e);
		}
	}

	private Barber buildObject(ResultSet rs) throws DataAccessException {
		try {
			Barber b = new Barber(rs.getInt("id"), rs.getString("name"));
			return b;
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result", e);
		}
	}


	@Override
	public void createBarber(String name) throws DataAccessException {
		try {
			insert.setString(1, name);
			insert.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve data", e);
		}
	}
		

	@Override
	public void removeBarberByName(String name) throws DataAccessException {
		try {
			delete.setString(1, name);
			delete.setString(2, name);
			delete.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve data", e);
		}
	}

	@Override
	public void updateBarber(String name, String newName) throws DataAccessException {
		try {
			update.setString(1, newName);
			update.setString(2, name);
			update.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve data", e);
		}
		
	}	
	
	public List<Barber> printAllBarbers() throws DataAccessException {
		List<Barber> Barbers = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = DBConnection.getInstance().getConnection().createStatement();
			rs = stmt.executeQuery("select * from Barber");
			
			while(rs.next()) {
				Barber c = buildObject(rs);
				Barbers.add(c);
			} return Barbers;
		} catch(SQLException e) {
			throw new DataAccessException("could not retrieve data", e);
		}
	}
}


