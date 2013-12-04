package crmpp.csvreader.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {
	public Integer id;
	public String firstName;
	public String lastName;
	public String gender;
	public Integer age;
	
	public User(Integer id, String firstName, String lastName, String gender, Integer age) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
	}
	
	private boolean isValid() {
		if (firstName.length() > 50 || lastName.length() > 75 || (!gender.equals("M") && !gender.equals("F")))
		{
			System.err.println("Invalid argument(s) when inserting a user!");
			return false;
		}
		return true;
	}
	
	public void insert(Connection conn) throws SQLException {
		if (isValid())
		{
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO crmusers VALUES (?, ?, ?, ?, ?)");
			stmt.setInt(1, id);
			stmt.setString(2, firstName);
			stmt.setString(3, lastName);
			stmt.setString(4, gender);
			stmt.setInt(5, age);
			stmt.execute();
		}
	}
}
