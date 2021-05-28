package db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private static Connection connection;
	private static DBConnection dbConnection;
	
	private static final String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DBNAME = "dmaj0920_1086313";
	private static final String SERVERNAME = "hildur.ucn.dk";
	private static final String PORTNUMBER = "1433";
	private static final String USERNAME = "dmaj0920_1086313";
	private static final String PASSWORD = "Password1!";  

	private DBConnection() {
		String connectionString = String.format("jdbc:sqlserver://%s:%s;databaseName=%s;user=%s;password=%s", 
				SERVERNAME, PORTNUMBER, DBNAME, USERNAME, PASSWORD);

		try {
			Class.forName(driverClass);
			connection = DriverManager.getConnection(connectionString);
		} catch (ClassNotFoundException e) {
			System.err.println("Could not load JDBC driver");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Could not connect to database " + DBNAME + "@" + SERVERNAME + ":" + PORTNUMBER + " as user " + USERNAME + " using password ******");
			System.out.println("Connection string was: " + connectionString.substring(0, connectionString.length() - PASSWORD.length()) + "....");
			e.printStackTrace();
		}
	}
	
	public static DBConnection getInstance() {
		if(dbConnection == null) {
			dbConnection = new DBConnection();
		}
		return dbConnection;
	}
	
	public void startTransaction() throws SQLException {
		connection.setAutoCommit(false);
	}
	
	public void commitTransaction() throws SQLException {
		connection.commit();
		connection.setAutoCommit(true);
	}
	
	public void rollbackTransaction() throws SQLException {
		connection.rollback();
		connection.setAutoCommit(true);
	}
	
	public int executeInsertWithIdentity(PreparedStatement ps) throws SQLException  {
		int res = -1;
		try {
			res = ps.executeUpdate();
			if(res > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return res;
	}
	
	public int executeInsertWithIdentity(String sql) throws SQLException  {
		System.out.println("DBConnection, Inserting: " + sql);
		int res = -1;
		try (Statement s = connection.createStatement()) {
			res = s.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			if(res > 0) {
				ResultSet rs = s.getGeneratedKeys();
				rs.next();
				res = rs.getInt(1);
			}
			//s.close(); -- the try block does this for us now

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return res;
	}
	
	public int executeUpdate(String sql) throws SQLException {
		System.out.println("DBConnection, Updating: " + sql);
		int res = -1;
		try (Statement s = connection.createStatement()){
			res = s.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return res;
	}
	
	
	public Connection getConnection() {
		return connection;
	}
	
	public void disconnect() {
		try {
			connection.close();
			dbConnection = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setAutoCommit(boolean b) throws SQLException {
		connection.setAutoCommit(b);
	}
	
	public boolean isNull() {
		connection = null;
		return true;
	}
	
	public void reOpenConnection() {
		String connectionString = String.format("jdbc:sqlserver://%s:%s;databaseName=%s;user=%s;password=%s", 
				SERVERNAME, PORTNUMBER, DBNAME, USERNAME, PASSWORD);

		try {
			Class.forName(driverClass);
			connection = DriverManager.getConnection(connectionString);
		} catch (ClassNotFoundException e) {
			System.err.println("Could not load JDBC driver");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Could not connect to database " + DBNAME + "@" + SERVERNAME + ":" + PORTNUMBER + " as user " + USERNAME + " using password ******");
			System.out.println("Connection string was: " + connectionString.substring(0, connectionString.length() - PASSWORD.length()) + "....");
			e.printStackTrace();
		}
			}
}