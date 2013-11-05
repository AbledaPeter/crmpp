package crmpp.csvreader;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ReadCSVTest {
	ReadCSV readCSV;
	
	@Before
	public void init(){
		readCSV = new ReadCSV();
	}

	@Test
	public void readCRMUsersCSVTest() {
		List<Object[]> readCRMUsersCSV = readCSV.readCRMUsersCSV(new File(".//testfiles//crmusers.csv"));

		assertTrue("There is not 6 elements in the csv!", readCRMUsersCSV.size() == 6);
		assertTrue("The 1st element is not an Integer", readCRMUsersCSV.get(0)[0] instanceof Integer);
		assertTrue("The 2nd element is not an String", readCRMUsersCSV.get(0)[1] instanceof String);
		assertTrue("The 3rd element is not an String", readCRMUsersCSV.get(0)[2] instanceof String);
		assertTrue("The 4th element is not an String", readCRMUsersCSV.get(0)[3] instanceof String);
		assertTrue("The 5th element is not an Integer", readCRMUsersCSV.get(0)[4] instanceof Integer);
	}
	
	@Test
	public void readCRMInterestsCSVTest() {
		List<Object[]> readCRMInterestsCSV = readCSV.readCRMInterestsCSV(new File(".//testfiles//crminterests.csv"));

		assertTrue("There is not 6 elements in the csv!", readCRMInterestsCSV.size() == 6);
		assertTrue("The 1st element is not an Integer", readCRMInterestsCSV.get(0)[0] instanceof Integer);
		assertTrue("The 2nd element is not an String", readCRMInterestsCSV.get(0)[1] instanceof String);
	}
	
	@Test
	public void readCRMAvailabilitiesCSVTest() {
		List<Object[]> readCRMAvailabilitiesCSV = readCSV.readCRMAvailabilitiesCSV(new File(".//testfiles//crmavailabilities.csv"));

		assertTrue("There is not 6 elements in the csv!", readCRMAvailabilitiesCSV.size() == 6);
		assertTrue("The 1st element is not an Integer", readCRMAvailabilitiesCSV.get(0)[0] instanceof Integer);
		assertTrue("The 2nd element is not an String", readCRMAvailabilitiesCSV.get(0)[1] instanceof String);
		assertTrue("The 3rd element is not an String", readCRMAvailabilitiesCSV.get(0)[2] instanceof String);
	}

}
