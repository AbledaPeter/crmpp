package crmpp.csvreader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseTestHelper {
	private static DatabaseTestHelper instance;
	
	public static DatabaseTestHelper getInstance(){
		if(instance == null){
			instance = new DatabaseTestHelper();
		}
		return instance;
	}
	
	private Connection conn = null;

	private DatabaseTestHelper() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://registrydb.ccg9w8x2ngjz.us-west-2.rds.amazonaws.com:3306/crmpp", "admin", "invarionadmin");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int countRows(String tableName) throws SQLException {
		if (conn != null) {
			PreparedStatement stmt = conn.prepareStatement(String.format("SELECT COUNT(*) AS rowcount FROM %s", tableName));

			ResultSet rs = stmt.executeQuery();
			rs.next();

			return rs.getInt("rowcount");
		}
		return 0;
	}
}
