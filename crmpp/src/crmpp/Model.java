package crmpp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Model {

	private Connection conn = null;
	
	public Model() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://registrydb.ccg9w8x2ngjz.us-west-2.rds.amazonaws.com:3306/crmpp", "admin", "invarionadmin");
			initialize();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void dropTable(String tableName) throws SQLException {
		if (conn != null)
		{
			PreparedStatement stmt = conn.prepareStatement(String.format("DROP TABLE IF EXISTS %s", tableName));
			stmt.execute();
		}
	}
	
	public void initialize() throws SQLException {
		if (conn != null)
		{
			dropTable("crmcorporationuserconnection");
			dropTable("crmcorporations");
			dropTable("crmavailabilities");
			dropTable("crminterests");
			dropTable("crmusers");
			
			Statement stmt = conn.createStatement();
			
			stmt.execute("CREATE TABLE crmusers (USER_ID INTEGER PRIMARY KEY, FIRST_NAME CHAR(50)," +
							"LAST_NAME CHAR(75), GENDER CHAR(1), AGE INTEGER)");
			
			stmt.execute("CREATE TABLE crmavailabilities (USER_ID INTEGER not null, FOREIGN KEY (USER_ID) REFERENCES crmusers (USER_ID)"
					+ ", EMAIL CHAR(50), ADDRESS CHAR(75))");

			stmt.execute("CREATE TABLE crminterests (USER_ID INTEGER not null, FOREIGN KEY (USER_ID) REFERENCES crmusers (USER_ID)"
					+ ", FAVORITE_SPORT CHAR(50))");
			
			stmt.execute("CREATE TABLE crmcorporations (ID INTEGER PRIMARY KEY, NAME CHAR(50)," +
					"ADDRESS CHAR(75), EMAIL CHAR(50))");

			stmt.execute("CREATE TABLE crmcorporationuserconnection (USER_ID INTEGER not null, " +
					"FOREIGN KEY (USER_ID) REFERENCES crmusers (USER_ID)"
					+ ", CORPORATION_ID INTEGER not null, FOREIGN KEY (CORPORATION_ID) REFERENCES crmcorporations (ID))");
		}
	}
	
	public void insertUser(int userID, String firstName, String lastName, String gender, int age,
					String email, String address, String sport) throws SQLException {
		if (conn != null)
		{
			if (firstName.length() > 50 || lastName.length() > 75 || (!gender.equals("M") && !gender.equals("F"))
					|| email.length() > 50 || sport.length() > 50)
			{
				System.out.println("Invalid argument(s) when inserting a user!");
				return;
			}
			
			PreparedStatement stmt;
			
			stmt = conn.prepareStatement("INSERT INTO crmusers VALUES (?, ?, ?, ?, ?)");
			stmt.setInt(1, userID);
			stmt.setString(2, firstName);
			stmt.setString(3, lastName);
			stmt.setString(4, gender);
			stmt.setInt(5, age);
			stmt.execute();
			
			stmt = conn.prepareStatement("INSERT INTO crmavailabilities VALUES (?, ?, ?)");
			stmt.setInt(1, userID);
			stmt.setString(2, email);
			stmt.setString(3, address);
			stmt.execute();
			
			stmt = conn.prepareStatement("INSERT INTO crminterests VALUES (?, ?)");
			stmt.setInt(1, userID);
			stmt.setString(2, sport);
			stmt.execute();
		}
	}
	
	public void insertCorporation(int ID, String name, String address, String email) throws SQLException {
		if (conn != null)
		{
			if (name.length() > 50 || address.length() > 75 || email.length() > 50)
			{
				System.out.println("Invalid argument(s) when inserting a corporation!");
				return;
			}
			
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO crmcorporations VALUES (?, ?, ?, ?)");
			stmt.setInt(1, ID);
			stmt.setString(2, name);
			stmt.setString(3, address);
			stmt.setString(4, email);
			
			stmt.execute();
		}
	}
	
	public void addCorporationContacts(int corporationID, ArrayList<Integer> users) throws SQLException {
		if (conn != null)
		{
			for(int userID : users)
			{
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO crmcorporationuserconnection VALUES (?, ?)");
				stmt.setInt(1, userID);
				stmt.setInt(2, corporationID);
				stmt.execute();
			}
		}
	}
	
	public int countRows(String tableName) throws SQLException {
		if (conn != null)
		{
			PreparedStatement stmt = conn.prepareStatement(String.format("SELECT COUNT(*) AS rowcount FROM %s", tableName));
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("rowcount");
		}
		return 0;
	}
	
	private void queryTable(String tableName) throws SQLException {
		if (conn != null)
		{
			PreparedStatement stmt = conn.prepareStatement(String.format("SELECT * FROM %s", tableName));
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) 
			{
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) 
				{
					System.out.println(rs.getMetaData().getColumnName(i) + "\t" + rs.getString(i));
				}
			}
		}
	}
	
	public void queryUsers() throws SQLException {
		if (conn != null)
		{
			System.out.println("**********USERS**********");
			queryTable("crmusers");
			System.out.println("**********AVAILABILITIES**********");
			queryTable("crmavailabilities");
			System.out.println("**********INTERESTS**********");
			queryTable("crminterests");
		}
	}
		
	public void queryCorporations() throws SQLException {
		if (conn != null)
		{
		    System.out.println("**********CORPORATIONS**********");
		    queryTable("crmcorporations");
		    System.out.println("**********CORPORATION-USER CONNECTIONS**********");
		    queryTable("crmcorporationuserconnection");
		}
	}
}
