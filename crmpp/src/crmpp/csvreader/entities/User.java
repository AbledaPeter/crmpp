package crmpp.csvreader.entities;

public class User {
	public Integer id;
	public String firstName;
	public String lastName;
	public String gender;
	public Integer age;
	
	public User() {
	}
	
	public User(Integer id, String firstName, String lastName, String gender, Integer age) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
	}
}
