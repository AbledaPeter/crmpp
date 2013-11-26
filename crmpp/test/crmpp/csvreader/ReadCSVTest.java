package crmpp.csvreader;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import crmpp.csvreader.entities.Availability;
import crmpp.csvreader.entities.Interest;
import crmpp.csvreader.entities.User;

public class ReadCSVTest {
	ReadCSV readCSV;
	
	@Before
	public void init(){
		readCSV = new ReadCSV();
	}

	@Test
	public void readCRMUsersCSVTest() {
		List<User> readCRMUsersCSV = readCSV.readCRMUsersCSV(new File(".//testfiles//crmusers.csv"));
		
		assertTrue("There is not 6 elements in the csv!", readCRMUsersCSV.size() == 6);
	}
	
	@Test
	public void readCRMInterestsCSVTest() {
		List<Interest> readCRMInterestsCSV = readCSV.readCRMInterestsCSV(new File(".//testfiles//crminterests.csv"));

		assertTrue("There is not 6 elements in the csv!", readCRMInterestsCSV.size() == 6);
	}
	
	@Test
	public void readCRMAvailabilitiesCSVTest() {
		List<Availability> readCRMAvailabilitiesCSV = readCSV.readCRMAvailabilitiesCSV(new File(".//testfiles//crmavailabilities.csv"));

		assertTrue("There is not 6 elements in the csv!", readCRMAvailabilitiesCSV.size() == 6);
	}

}
