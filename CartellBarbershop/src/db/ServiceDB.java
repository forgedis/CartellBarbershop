package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.Barber;
import model.Price;
import model.Service;

public class ServiceDB implements ServiceIF {
	private static final String FIND_BY_SERVICENO_Q = "select * from Service where name = ?";
	private static final String INSERT_Q = "insert into Service (name, description, duration, price_id) values (?, ?, ?, (select id from Price where price = ?))";
	private static final String DELETE_Q = "update Reservation_Service set service_id = null where service_id = (select id from Service where name = ?)" + "delete from Service where name = ?";
	private static final String UPDATE_Q = "update Service set name = ?, description = ?, duration = ?, price_id = (select id from Price where price = ?) where name = ?";
	private static final String INSERTP_Q = "insert into Price(fromDate, price) values(?, ?)";
	private static final String FINDP_Q = "select * from Price where fromDate = ?";
	private static final String DELETEP_Q = "update Service set price_id = null where price_id = (select id from Price where fromDate = ?)" + "delete from Price where fromDate = ?";
	private static final String UPDATEP_Q = "update Price set fromDate = ?, price = ? where fromDate = ?";
	private PreparedStatement insert,find, delete, update, insert2, find2, delete2, update2;
	
		public ServiceDB() throws DataAccessException {
			try {
				insert = DBConnection.getInstance().getConnection().prepareStatement(INSERT_Q);
				find = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_SERVICENO_Q);
				delete = DBConnection.getInstance().getConnection().prepareStatement(DELETE_Q);
				update = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_Q);
				insert2 = DBConnection.getInstance().getConnection().prepareStatement(INSERTP_Q);
				find2 = DBConnection.getInstance().getConnection().prepareStatement(FINDP_Q);
				delete2 = DBConnection.getInstance().getConnection().prepareStatement(DELETEP_Q);
				update2 = DBConnection.getInstance().getConnection().prepareStatement(UPDATEP_Q);
			} catch (SQLException e) {
				throw new DataAccessException("Could not prepare statement", e);
			}
		}
	
		@Override
		public List<Service> findServiceByName(String name) throws DataAccessException {
			List<Service> services = new ArrayList<>();
			
			try {
				find.setString(1, name);
				ResultSet rs = find.executeQuery();
				Service s = null;
					if(rs.next()) {
						s = buildObject(rs);
						services.add(s);
					}
				return services;
			} catch (SQLException e) {
				throw new DataAccessException("Could not retrieve data", e);
			}
		}

		
		private Service buildObject(ResultSet rs) throws DataAccessException {
			try {
				Service s = new Service(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getTime("duration").toLocalTime(), rs.getInt("price_id"));
				return s;
			} catch (SQLException e) {
				throw new DataAccessException("Could not read result", e);
			}
		}

		@Override
		public void createService(String name, String description, LocalTime duration, double price) throws DataAccessException {
			
			try {
				insert.setString(1, name);
				insert.setString(2, description);
				insert.setTime(3, java.sql.Time.valueOf(duration));
				insert.setDouble(4, price);
				insert.executeUpdate();    // transactions needed where we have executeUpdate (changing the db)
			} catch (SQLException e) {
				throw new DataAccessException("Could not prepare statement", e);
			}
		}

		@Override
		public void deleteService(String name) throws DataAccessException {
			try {
				delete.setString(1, name);
				delete.setString(2, name);
				delete.executeUpdate();
			} catch (SQLException e) {
				throw new DataAccessException("Could not prepare statement", e);
			}
		}

		@Override
		public void updateService(String name, String newName, String newDescription, LocalTime newDuration,
				double price) throws DataAccessException {
			try {
				update.setString(1, newName);
				update.setString(2, newDescription);
				update.setTime(3, java.sql.Time.valueOf(newDuration));
				update.setDouble(4, price);
				update.setString(5, name);
				update.executeUpdate();
			} catch (SQLException e) {
				throw new DataAccessException("Could not prepare statement", e);
			}
			
			
		}

		@Override
		public List<Service> printAllServices() throws DataAccessException {
			List<Service> services = new ArrayList<>();
			Statement stmt = null;
			ResultSet rs = null;
			
			try {
				stmt = DBConnection.getInstance().getConnection().createStatement();
				rs = stmt.executeQuery("select * from Service");
				
				while(rs.next()) {
					Service s = buildObject(rs);
					services.add(s);
				} return services;
			} catch(SQLException e) {
				throw new DataAccessException("could not retrieve data", e);
			}
		}
		
		@Override
		public void createPrice(LocalDate fromDate, double price) throws DataAccessException {
			
			
			try {
				insert2.setDate(1, java.sql.Date.valueOf(fromDate));
				insert2.setDouble(2, price);
				insert2.executeUpdate();
			} catch (SQLException e) {
				throw new DataAccessException("Could not prepare statement", e);
			}
		}

		@Override
		public List<Price> findPriceByDate(LocalDate fromDate) throws DataAccessException {
			List<Price> prices = new ArrayList<>();
			try {
				find2.setDate(1, java.sql.Date.valueOf(fromDate));
				ResultSet rs = find2.executeQuery();
				Price p = null;
				if(rs.next()) {
					p = buildObject2(rs);
					prices.add(p);
				} return prices;
			} catch (SQLException e) {
				throw new DataAccessException("Could not prepare statement", e);
			}
		}
		
		private Price buildObject2(ResultSet rs) throws DataAccessException {
			try {
				Price p  = new Price(rs.getInt("id"), rs.getDate("fromDate").toLocalDate(), rs.getDouble("price"));
				return p;
			} catch (SQLException e) {
				throw new DataAccessException("Could not read result", e);
			}
		}

		@Override
		public void deletePrice(LocalDate fromDate) throws DataAccessException {
			try {
				delete2.setDate(1, java.sql.Date.valueOf(fromDate));
				delete2.setDate(2, java.sql.Date.valueOf(fromDate));
				delete2.executeUpdate();
			} catch (SQLException e) {
				throw new DataAccessException("Could not read result", e);
			}
		}

		@Override
		public void updatePrice(LocalDate fromDate, LocalDate newFromDate, double newPrice) throws DataAccessException {
			try {
				update2.setDate(1, java.sql.Date.valueOf(newFromDate));
				update2.setDouble(2, newPrice);
				update2.setDate(3, java.sql.Date.valueOf(fromDate));
				update2.executeUpdate();
			} catch (SQLException e) {
				throw new DataAccessException("Could not read result", e);
			}
		}
		
		public List<Price> printAllPrices() throws DataAccessException {
			List<Price> prices = new ArrayList<>();
			Statement stmt = null;
			ResultSet rs = null;
			
			try {
				stmt = DBConnection.getInstance().getConnection().createStatement();
				rs = stmt.executeQuery("select * from Price");
				
				while(rs.next()) {
					Price p = buildObject2(rs);
					prices.add(p);
				} return prices;
			} catch(SQLException e) {
				throw new DataAccessException("could not retrieve data", e);
			}
		}
}
