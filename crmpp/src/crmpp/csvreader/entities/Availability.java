package crmpp.csvreader.entities;

public class Availability {
	public Integer id;
	public String email;
	public String address;

	public Availability() {
	}

	public Availability(Integer id, String email, String address) {
		super();
		this.id = id;
		this.email = email;
		this.address = address;
	}
}
