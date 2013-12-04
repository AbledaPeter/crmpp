package crmpp.csvreader.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Availability {
	public Integer id;
	public String email;
	public String address;

	public Availability(Integer id, String email, String address) {
		super();
		this.id = id;
		this.email = email;
		this.address = address;
	}
	
	private boolean isValid() {
		if (email.length() > 50 || address.length() > 75)
		{
			System.err.println("Invalid argument(s) when inserting an availability!");
			return false;
		}
		return true;
	}
	
	public void insert(Connection conn) throws SQLException {
		if (isValid())
		{
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO crmavailabilities VALUES (?, ?, ?)");
			stmt.setInt(1, id);
			stmt.setString(2, email);
			stmt.setString(3, address);
			stmt.execute();
		}
	}

}
