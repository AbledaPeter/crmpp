package crmpp.csvreader.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Interest {
	public Integer id;
	public String favouriteSport;

	public Interest(Integer id, String favouriteSport) {
		super();
		this.id = id;
		this.favouriteSport = favouriteSport;
	}

	private boolean isValid() {
		if (favouriteSport.length() > 50)
		{
			System.err.println("Invalid argument(s) when inserting an interest!");
			return false;
		}
		return true;
	}
	
	public void insert(Connection conn) throws SQLException {
		if (isValid())
		{
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO crminterests VALUES (?, ?)");
			stmt.setInt(1, id);
			stmt.setString(2, favouriteSport);

			stmt.execute();
		}
	}
}
