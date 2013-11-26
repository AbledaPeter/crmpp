package crmpp.csvreader.entities;

public class Availability {
	Integer id;
	String email;
	String address;

	public Availability() {
	}

	public Availability(Integer id, String email, String address) {
		super();
		this.id = id;
		this.email = email;
		this.address = address;
	}
}
