package model;

public class User {
	private int id;
	private String name, email, location;
	public User(int id, String name, String email, String location) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.location = location;
	}
	@Override
	public String toString() {
		return "User [ID=" + id + ", name=" + name + ", email=" + email + ", location=" + location + "]";
	}
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
