package ui;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import ctr.CustomerCtr;
import db.BarberDB;
import db.CustomerDB;
import db.DataAccessException;
import db.ReservationDB;
import db.ServiceDB;
import model.Barber;
import model.Customer;

public class TryMe {
	private static CustomerDB customerDB;
	private static BarberDB barberDB;
	private static ServiceDB serviceDB;
	private static CustomerCtr customerCtr;
	private static ReservationDB reservationDB;
	
	public static void main(String[] args) throws DataAccessException {
		/*
		 // Test function for finding customers in the database
		int phoneNo = 4040404;
		customerDB = new CustomerDB();
		Customer c = customerDB.findCustomerByPhone(phoneNo);
		System.out.println(c.getFirstName() + c.getLastName() + c.getPhoneNo() + c.getId());
		
		
		/* test function for deleting customers in the database
		int phoneNo = 503598850;
		customerDB = new CustomerDB();
		customerDB.removeCustomerByPhone(phoneNo);
		System.out.println("good.");
		

		String name = "Annita";
		barberDB = new BarberDB();
		barberDB.createBarber(name);
		*/

		
		/* finding barber in database
		int phoneNo = 33333;
		barberDB = new BarberDB();
		Barber b = barberDB.findBarberByNo(phoneNo);
		System.out.println(b.getName() + " " + b.getPhoneNo());
		*/ 
		

		/* update customer in database
		customerDB = new CustomerDB();
		int phoneNo = 5050505;
		String newFirstName = "Martina";
		String newLastName = "Bujnakova";
		int newPhoneNo = 696969;
		customerDB.updateCustomer(phoneNo, newFirstName, newLastName, newPhoneNo);
		System.out.println("Updated.");
		
		
		reservationDB = new ReservationDB();
		int reservationNo = 50;
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		int customerPhoneNo = 4040404;
		String barberName = "Kristian";
		int customer_id = 2;
		String serviceName = "Haircut";
		reservationDB.createReservation(reservationNo, date, time, customerPhoneNo, barberName, customer_id, serviceName);
		/* = new ReservationDB();
		int customer_id = 7;
		String serviceName = "Haircut";
		reservationDB.createRS(customer_id, serviceName);
		404040
		
		reservationDB = new ReservationDB();
		int customer_id = 7;
		String serviceName = "Beard Trim";
		reservationDB.createRS(customer_id, serviceName);
		
		
		customerDB = new CustomerDB();
		int phoneNo = 53890;
		customerDB.removeCustomerByPhone(phoneNo);
		
		
		customerDB = new CustomerDB();
		System.out.println(customerDB.printAllCustomers()); */
		/*
		customerDB = new CustomerDB();
		
		customerDB.createCustomer("Mrko", "Frgo", 696969);
		*/
		
		String name = "testBarber";
		serviceDB = new ServiceDB();
		serviceDB.deletePrice(LocalDate.parse("2020-05-19"));
		System.out.println("good.");
	}
}
