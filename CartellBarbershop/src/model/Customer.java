package model;

public class Customer {
	private int id;
	private String firstName;
	private String lastName;
	private String phoneNo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public Customer(int id, String firstName, String lastName, String phoneNo) { 
		super();
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setPhoneNo(phoneNo);
	}
	
	public Customer(String firstName, String lastName, String phoneNo) {
		super();
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setPhoneNo(phoneNo);
	}
}
