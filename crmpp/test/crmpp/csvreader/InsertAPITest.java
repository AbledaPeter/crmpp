package crmpp.csvreader;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import crmpp.Model;
import crmpp.UserData;

public class InsertAPITest {
	Model model;
	
	@Before
	public void init() throws SQLException{
		model = new Model();
	}

	@Test
	public void insertUserTest() throws SQLException {
		model.insertUser(new UserData(1, "Teszt", "Elek", "M", 66, "tesztelek@ittvagyok.hu", "ittlakok", "sakk"));
		
		assertTrue("There are no users inserted into the database!", DatabaseTestHelper.getInstance().countRows("crmusers") > 0);
	}
	
	@Test
	public void insertCorporationTest() throws SQLException {
		model.insertCorporation(1, "TesztCeg", "tesztceg@cegvagyok.hu", "ittvanaceg");
		
		assertTrue("There are no corporations inserted into the database!", DatabaseTestHelper.getInstance().countRows("crmcorporations") > 0);
	}
	
	@Test
	public void addCorporationContactsTest() throws SQLException {
		ArrayList<Integer> users = new ArrayList<Integer>();
		users.add(1);
		users.add(2);
		users.add(3);
		
		model.insertUser(new UserData(1, "Teszt", "Elek", "M", 66, "tesztelek@ittvagyok.hu", "ittlakok", "sakk"));
		model.insertUser(new UserData(2, "Teszt", "Elek2", "M", 66, "tesztelek@ittvagyok.hu", "ittlakok", "sakk"));
		model.insertUser(new UserData(3, "Teszt", "Elek3", "M", 66, "tesztelek@ittvagyok.hu", "ittlakok", "sakk"));
		
		model.insertCorporation(1, "TesztCeg", "tesztceg@cegvagyok.hu", "ittvanaceg");
		
		model.addCorporationContacts(1, users);
		
		assertTrue("There are no corporation contacts inserted into the database!", DatabaseTestHelper.getInstance().countRows("crmcorporationuserconnection") > 0);
	}
}
