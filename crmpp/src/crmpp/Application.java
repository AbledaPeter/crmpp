package crmpp;

import java.sql.SQLException;

public class Application {

	public static void main(String[] args) throws SQLException {
		
		Model model = new Model();
		
		model.createUserTables();
		
		model.insertUser(1, "Teszt", "Elek", "M", 66, "tesztelek@ittvagyok.hu", "golf");	
	}
	
	

}
