package model;

import java.time.LocalDate;

public class Price {
	private int id;
	private LocalDate fromDate;
	private double price;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Price(int id, LocalDate fromDate, double price) {
		super();
		this.setId(id);
		this.setFromDate(fromDate);
		this.setPrice(price);
	}
	
}
