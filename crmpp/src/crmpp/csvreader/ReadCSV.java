package crmpp.csvreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import crmpp.csvreader.entities.User;

public class ReadCSV {
	
	private static final String cvsSplitBy = ",";

	/**
	 * Returns the contents of the CSV file. Converts the datas to the expected type.
	 * @param 
	 * @return
	 */
	public List<User> readCRMUsersCSV(File csvFile) {
		BufferedReader br = null;
		String line = "";
		
		List<User> users = new ArrayList<User>();

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] unparsedUser = line.split(cvsSplitBy);
				
				User user = new User(
						Integer.valueOf(unparsedUser[0].replace("\"", "").trim()),
						unparsedUser[1].replace("\"", "").trim(), 
						unparsedUser[2].replace("\"", "").trim(), 
						unparsedUser[3].replace("\"", "").trim(), 
						Integer.valueOf(unparsedUser[4].replace("\"", "").trim()));

				users.add(user);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return users;
	}
	
	/**
	 * Returns the contents of the CSV file. Converts the datas to the expected type.
	 * 
	 * @param 
	 * @return The return list contains an Object array: [0] - id, [1] - favouriteSport
	 */
	public List<Object[]> readCRMInterestsCSV(File csvFile) {
		BufferedReader br = null;
		String line = "";
		
		List<Object[]> interests = new ArrayList<Object[]>();

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] unparsedInterest = line.split(cvsSplitBy);
				
				Object[] interest = new Object[]{
						Integer.valueOf(unparsedInterest[0].replace("\"", "").trim()),
						unparsedInterest[1].replace("\"", "").trim()
						};
				interests.add(interest);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return interests;
	}
	
	/**
	 * Returns the contents of the CSV file. Converts the datas to the expected type.
	 * 
	 * @param 
	 * @return The return list contains an Object array: [0] - id, [1] - email, [2] - address
	 */
	public List<Object[]> readCRMAvailabilitiesCSV(File csvFile) {
		BufferedReader br = null;
		String line = "";
		
		List<Object[]> availabilities = new ArrayList<Object[]>();

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] unparsedAvailabilities = line.split(cvsSplitBy);
				
				Object[] availability = new Object[]{
						Integer.valueOf(unparsedAvailabilities[0].replace("\"", "").trim()),
						unparsedAvailabilities[1].replace("\"", "").trim(),
						unparsedAvailabilities[2].replace("\"", "").trim()
						};
				availabilities.add(availability);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return availabilities;
	}
}
