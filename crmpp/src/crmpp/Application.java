package crmpp;

import java.sql.SQLException;
import java.util.ArrayList;

public class Application {

	public static void main(String[] args) throws SQLException {
		
		Model model = new Model();
		
		model.createTables();
		
		model.insertUser(1, "Teszt", "Elek", "M", 66, "tesztelek@ittvagyok.hu", "golf", "");
		model.insertCorporation(1, "Teszt Rt.", "999 Valahol Valamilyen utca 3.", "tesztrt@ezegyceg.hu");
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		//list.add(999);
		
		model.addCorporationContacts(1, list);
	}
}
