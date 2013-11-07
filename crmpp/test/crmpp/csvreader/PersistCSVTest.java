package crmpp.csvreader;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import crmpp.Model;

public class PersistCSVTest {
	PersistCSVElements persistCSV;
	Model model;
	
	@Before
	public void init(){
		persistCSV = new PersistCSVElements();
		model = new Model();
		
		try {
			model.createTables();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void persistCSVDataTest() throws Exception {
		List<Object[]> users = new ArrayList<Object[]>();
		Object[] user = new Object[]{
				1,
				"Elek",
				"Test",
				"M",
				66
				};
		users.add(user);
		
		List<Object[]> interests = new ArrayList<Object[]>();
		Object[] interest = new Object[]{
				1,
				"Football",
				};
		interests.add(interest);
		
		List<Object[]> availabilities = new ArrayList<Object[]>();
		Object[] availability = new Object[]{
				1,
				"elekvagyok@valami.hu",
				"ittlakok"
				};
		availabilities.add(availability);
		
		persistCSV.persistCSVData(users, interests, availabilities, model);
		
		assertTrue("There are no users inserted into the database!", model.getUserCount() > 0);
	}

}
