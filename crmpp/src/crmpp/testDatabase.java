package crmpp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class testDatabase {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://registrydb.ccg9w8x2ngjz.us-west-2.rds.amazonaws.com:3306/crmpp", "csapat", "password");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		PreparedStatement stmt = conn.prepareStatement("SELECT name FROM user");
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			System.out.println(rs.getString(1));
		}
		
	}

}
