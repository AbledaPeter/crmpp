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
	
	// Creates the tables that store user data; drops existing ones
	public void createUserTables() throws SQLException {
		if (conn != null)
		{
			java.sql.Statement stmt = conn.createStatement();
			
			String dropString = "";
			
			dropString = "DROP TABLE IF EXISTS crmavailabilities";
			stmt.execute(dropString);
			dropString = "DROP TABLE IF EXISTS crminterests";
			stmt.execute(dropString);
			dropString = "DROP TABLE IF EXISTS crmusers";
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
	
	// Inserts a user into the database with the given parameters
	public void insertUser(int userID, String firstName, String lastName, String gender, int age,
					String email, String sport) throws SQLException {
		if (conn != null)
		{
			// TODO: egzaktabb, pontosabb hibaüzenet
			if (firstName.length() > 50 || lastName.length() > 75 || (!gender.equals("M") && !gender.equals("F"))
					|| email.length() > 50 || sport.length() > 50)
			{
				System.out.println("Invalid argument(s)!");
				return;
			}
			
			String query = "";
			PreparedStatement stmt = null;
			
			query = "INSERT INTO crmusers VALUES (?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, userID);
			stmt.setString(2, firstName);
			stmt.setString(3, lastName);
			stmt.setString(4, gender);
			stmt.setInt(5, age);
			
			stmt.execute();
			
			query = "INSERT INTO crmavailabilities VALUES (?, ?)";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, userID);
			stmt.setString(2, email);
			stmt.execute();
			
			query = "INSERT INTO crminterests VALUES (?, ?)";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, userID);
			stmt.setString(2, sport);
			stmt.execute();
			
			//****************************************//
			// Code from now on is used for testing only
			//****************************************//
			
			stmt = conn.prepareStatement("SELECT * FROM crmusers");
			
		    ResultSet rs = stmt.executeQuery();
		      
		    ResultSetMetaData rm = rs.getMetaData();

			while (rs.next()) {
				for (int i=1; i<6; i++)
				{
					System.out.println(rm.getColumnName(i) + "\t" + rs.getString(i));
				}
			}
			
			stmt = conn.prepareStatement("SELECT * FROM crmavailabilities");
			
		    rs = stmt.executeQuery();
		      
		    rm = rs.getMetaData();

			while (rs.next()) {
				for (int i=1; i<3; i++)
				{
					System.out.println(rm.getColumnName(i) + "\t" + rs.getString(i));
				}
			}
			
			stmt = conn.prepareStatement("SELECT * FROM crminterests");
			
		    rs = stmt.executeQuery();
		      
		    rm = rs.getMetaData();

			while (rs.next()) {
				for (int i=1; i<3; i++)
				{
					System.out.println(rm.getColumnName(i) + "\t" + rs.getString(i));
				}
			}
			
			//****************************************//
			//************End of testing***************//
			//****************************************//
		}
	}
}
