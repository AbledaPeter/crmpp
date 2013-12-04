package crmpp.csvreader.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Corporation {
	public Integer id;
	public String name;
	public String address;
	public String email;
	
	public Corporation(Integer id, String name, String address, String email) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
	}
	
	private boolean isValid() {
		if (name.length() > 50 || address.length() > 75 || email.length() > 50)
		{
			System.err.println("Invalid argument(s) when inserting a corporation!");
			return false;
		}
		return true;
	}
	
	public void insert(Connection conn) throws SQLException {
		if (isValid())
		{
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO crmcorporations VALUES (?, ?, ?, ?)");
			stmt.setInt(1, id);
			stmt.setString(2, name);
			stmt.setString(3, address);
			stmt.setString(4, email);

			stmt.execute();
		}
	}
}
