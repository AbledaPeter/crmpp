package crmpp;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import crmpp.csvreader.PersistCSVElements;
import crmpp.csvreader.ReadCSV;
import crmpp.gui.ReadCSV_GUI;

public class Application {

	private Model model;
	
	public Application() {
		model = new Model();
		
		try 
		{
			model.createTables();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SQLException {
		
		Application app = new Application();
		
//		model.insertUser(1, "Teszt", "Elek", "M", 66, "tesztelek@ittvagyok.hu", "golf", "");
//		model.insertCorporation(1, "Teszt Rt.", "999 Valahol Valamilyen utca 3.", "tesztrt@ezegyceg.hu");
//		
//		ArrayList<Integer> list = new ArrayList<Integer>();
//		list.add(1);
//		//list.add(999);
//		
//		model.addCorporationContacts(1, list);
		
		ReadCSV_GUI gui = new ReadCSV_GUI("CSV Reader", app);
		gui.start();
	}
	
	// Persists the user data found in the three csv files the parameters are existing files
	public void persistUserData(File usersFile, File interestsFile, File availabilitiesFile) throws Exception {
		ReadCSV reader = new ReadCSV();
		PersistCSVElements persister = new PersistCSVElements();
		
		persister.persistCSVData(reader.readCRMUsersCSV(usersFile), reader.readCRMInterestsCSV(usersFile), 
				reader.readCRMAvailabilitiesCSV(usersFile), model);
		
		// run a test to see the results
		model.queryUsers();
	}
}
