package test;

import org.junit.*;

import db.DBConnection;

import static org.junit.Assert.*;

public class TestDBConnection {
	DBConnection connection = null;
	
	@Before
	public void setUp() {
		connection = DBConnection.getInstance();
	}
	
	@Test
	public void testDBConnection() {
		assertNotNull("Connected", connection);
		DBConnection.getInstance().disconnect();
		
		boolean isNull = DBConnection.getInstance().isNull();
		assertTrue("Disconnected", isNull);
		
		connection = DBConnection.getInstance();
		assertNotNull("Connected", connection);
	}
}
