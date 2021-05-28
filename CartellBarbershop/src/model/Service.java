package model;

import java.time.LocalTime;

public class Service {
	private int id;
	private String name;
	private String description;
	private LocalTime duration;
	private int price_id;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalTime getDuration() {
		return duration;
	}
	public void setDuration(LocalTime duration) {
		this.duration = duration;
	}
	
	public Service(int id, String name, String description, LocalTime duration,int price_id) {
		super();
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.setDuration(duration);
		this.setPrice_id(price_id);

	}
	public int getPrice_id() {
		return price_id;
	}
	public void setPrice_id(int price_id) {
		this.price_id = price_id;
	}

}