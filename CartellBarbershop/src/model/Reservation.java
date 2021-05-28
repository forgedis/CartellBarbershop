package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
	private int id;
	private String reservationNo;
	private LocalDate date;
	private LocalTime time;
	private int customer_id;
	private int barber_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReservationNo() {
		return reservationNo;
	}
	public void setReservationNo(String reservationNo) {
		this.reservationNo = reservationNo;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	public Reservation(String reservationNo, LocalDate date, LocalTime time, int customer_id, int barber_id) {
		super();
		this.setReservationNo(reservationNo);
		this.setDate(date);
		this.setTime(time);
		this.setCustomer_id(customer_id);
		this.setBarber_id(barber_id);
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getBarber_id() {
		return barber_id;
	}
	public void setBarber_id(int barber_id) {
		this.barber_id = barber_id;
	}
}

