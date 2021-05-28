package test;

import org.junit.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import ctr.BarberCtr;
import ctr.CustomerCtr;
import ctr.ReservationCtr;
import ctr.ServiceCtr;
import db.DataAccessException;
import model.Barber;
import model.Customer;
import model.Reservation;
import model.Service;

public class TestReservation {
	private static ReservationCtr reservationCtr;
	private static CustomerCtr customerCtr;
	private static BarberCtr barberCtr;
	private static ServiceCtr serviceCtr;


	@Before
	public void setUp() throws DataAccessException{
		reservationCtr = new ReservationCtr();
		customerCtr = new CustomerCtr();
		barberCtr = new BarberCtr();
		serviceCtr = new ServiceCtr();
	}

	
	//Entering reservationNo 1 from second line in the Reservations table should get reservationNo 1
	@Test
	public void testReservationExistsInDB() throws DataAccessException {
		// Arrange
		List<Reservation> reservations;
		String expectedResNo = "1";
		
		// Act
		reservations = reservationCtr.printAllReservations();
			
		// Assert
		assertEquals("Expected reservationNo should be: 1", expectedResNo, reservations.get(1).getReservationNo());
	}
	
	//Testing creating reservation with a specific date
	@Test
	public void testReservationIsCreatedInDB() throws DataAccessException, SQLException {
		// Arrange
		List<Reservation> reservations;
		LocalDate expectedDate = LocalDate.parse("2020-05-20");
		
		// Act
		customerCtr.createCustomer("Test", "User", "123456");
		barberCtr.createBarber("testBarber");
		serviceCtr.createService("testService", "testDescription", LocalTime.parse("00:30"), 7);
		reservationCtr.createReservation("999", LocalDate.parse("2020-05-20"), LocalTime.parse("14:45"), "123456", "testBarber", "Haircut");
		reservations = reservationCtr.printAllReservations();
		
		// Assert
		assertEquals("Expected reservation Date should be: 2020-05-20", expectedDate, reservations.get(reservations.size() - 1).getDate());
	}
	
	//Testing updating reservation
	@Test
	public void testReservationIsUpdatedinDB() throws DataAccessException, SQLException {
		// Arrange
		List<Reservation> reservations;
		
		LocalDate expectedDate = LocalDate.parse("2020-01-01");
		
		// Act
		reservations = reservationCtr.printAllReservations();
		reservationCtr.updateReservation(LocalDate.parse("2020-01-01"), LocalTime.parse("14:45"), "69", "Kristian", "123");
		reservations = reservationCtr.printAllReservations();
		
		// Assert
		assertEquals("Expected reservation Date should be: 2020-01-01", expectedDate, reservations.get(reservations.size() - 1).getDate());
	}
	
	
	//Deleting tested stuff
	@AfterClass
	public static void clear() throws DataAccessException, SQLException {
		List<Customer> customers;
		List<Barber> barbers;
		//List<Service> services;
		List<Reservation> reservations;
		customers = customerCtr.printAllCustomers();
		barbers = barberCtr.printAllBarbers();
		//services = serviceCtr.printAllServices();
		reservations = reservationCtr.printAllReservations();
		Customer customer = customers.get(customers.size() - 1);
		Barber barber = barbers.get(barbers.size() - 1);
		//Service service = services.get(services.size() - 1);
		Reservation reservation = reservations.get(reservations.size() - 1);
		String phoneNo = customer.getPhoneNo();
		String barberName = barber.getName();
		//String serviceName = service.getName();
		String resNo = reservation.getReservationNo();
		customerCtr.deleteCustomer(phoneNo);
		barberCtr.removeBarber(barberName);
		//serviceCtr.deleteService(serviceName);
		reservationCtr.deleteReservation(resNo);
	}
}
