package crmpp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Model {

	private Connection conn = null;
	
	public Model() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://registrydb.ccg9w8x2ngjz.us-west-2.rds.amazonaws.com:3306/crmpp", "admin", "invarionadmin");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void test() throws SQLException {
		
		if (conn != null)
		{
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user");
			ResultSet rs = stmt.executeQuery();
			
			ResultSetMetaData rm = rs.getMetaData();

			while (rs.next()) {
				for (int i=1; i<4; i++)
				{
					System.out.println(rm.getColumnName(i) + "\t" + rs.getString(i));
				}
			}
		}
	}
	
	public void createUserTables() throws SQLException {
		if (conn != null)
		{
			java.sql.Statement stmt = conn.createStatement();
			
			String dropString = "";
			
			dropString = "DELETE FROM crmavailabilities";
			stmt.execute(dropString);
			dropString = "DROP TABLE crmavailabilities";
			stmt.execute(dropString);
			dropString = "DELETE FROM crminterests";
			stmt.execute(dropString);
			dropString = "DROP TABLE crminterests";
			stmt.execute(dropString);
			dropString = "DELETE FROM crmusers";
			stmt.execute(dropString);
			dropString = "DROP TABLE crmusers";
			stmt.execute(dropString);

			
			String createString = "CREATE TABLE crmusers (USER_ID INTEGER PRIMARY KEY, FIRST_NAME CHAR(50)," +
									"LAST_NAME CHAR(75), GENDER CHAR(1), AGE INTEGER)";
			stmt.execute(createString);
			
			
			createString = "CREATE TABLE crmavailabilities (USER_ID INTEGER not null, FOREIGN KEY (USER_ID) REFERENCES crmusers (USER_ID)"
					+ ", EMAIL CHAR(50))";
			stmt.execute(createString);
			

			createString = "CREATE TABLE crminterests (USER_ID INTEGER not null, FOREIGN KEY (USER_ID) REFERENCES crmusers (USER_ID)"
					+ ", FAVORITE_SPORT CHAR(50))";
			stmt.execute(createString);
		}
	}
	
	public void createAvailabilities() throws SQLException {
		if (conn != null)
		{
			java.sql.Statement stmt = conn.createStatement();
			

		}
	}
	
	public void createInterests() throws SQLException {
		if (conn != null)
		{
			java.sql.Statement stmt = conn.createStatement();
			

		}
	}
	
	public void insertUserTest() throws SQLException {
		if (conn != null)
		{
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO crmusers " + 
						"VALUES (1, 'Teszt', 'Elek', 'M', 66)");
			pstmt.execute();
			
			pstmt = conn.prepareStatement("INSERT INTO crmavailabilities " + 
					"VALUES (1, 'tesztelek@ittvagyok.hu')");
			pstmt.execute();
			
			pstmt = conn.prepareStatement("INSERT INTO crminterests " + 
					"VALUES (1, 'football')");
			pstmt.execute();
			
			pstmt = conn.prepareStatement("SELECT * FROM crmusers");
			
		    ResultSet rs = pstmt.executeQuery();
		      
		    ResultSetMetaData rm = rs.getMetaData();

			while (rs.next()) {
				for (int i=1; i<6; i++)
				{
					System.out.println(rm.getColumnName(i) + "\t" + rs.getString(i));
				}
			}
			
			pstmt = conn.prepareStatement("SELECT * FROM crmavailabilities");
			
		    rs = pstmt.executeQuery();
		      
		    rm = rs.getMetaData();

			while (rs.next()) {
				for (int i=1; i<3; i++)
				{
					System.out.println(rm.getColumnName(i) + "\t" + rs.getString(i));
				}
			}
			
			pstmt = conn.prepareStatement("SELECT * FROM crminterests");
			
		    rs = pstmt.executeQuery();
		      
		    rm = rs.getMetaData();

			while (rs.next()) {
				for (int i=1; i<3; i++)
				{
					System.out.println(rm.getColumnName(i) + "\t" + rs.getString(i));
				}
			}
		}
	}
}
