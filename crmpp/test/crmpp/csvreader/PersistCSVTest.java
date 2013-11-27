package crmpp.csvreader;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import crmpp.Model;
import crmpp.csvreader.entities.Availability;
import crmpp.csvreader.entities.Interest;
import crmpp.csvreader.entities.User;

public class PersistCSVTest {
	PersistCSVElements persistCSV;
	Model model;
	
	@Before
	public void init(){
		persistCSV = new PersistCSVElements();
		model = new Model();
	}

	@Test
	public void persistCSVDataTest() throws Exception {
		List<User> users = new ArrayList<User>();
		User user = new User(
				1,
				"Elek",
				"Test",
				"M",
				66
				);
		users.add(user);
		
		List<Interest> interests = new ArrayList<Interest>();
		Interest interest = new Interest(
				1,
				"Football"
				);
		interests.add(interest);
		
		List<Availability> availabilities = new ArrayList<Availability>();
		Availability availability = new Availability(
				1,
				"elekvagyok@valami.hu",
				"ittlakok"
				);
		availabilities.add(availability);
		
		persistCSV.persistCSVData(users, interests, availabilities, model);
		
		assertTrue("There are no users inserted into the database!", model.countRows("crmusers") > 0);
	}

}
