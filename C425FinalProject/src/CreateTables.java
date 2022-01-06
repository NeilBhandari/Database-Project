/*
 * THIS CLASS IS TO CREATE THE TABLES FOR POSTGREQL, ONLY NEED TO RUN THIS CLASS ONCE!
 */

import java.sql.Connection;
import java.sql.Statement;

public class CreateTables {
	
	private static String tables = "CREATE TABLE Federal_Government(\r\n"
			+ "        tax_rate FLOAT NOT NULL,\r\n"
			+ "        country TEXT NOT NULL,\r\n"
			+ "        PRIMARY KEY (country)\r\n"
			+ "      );\r\n"
			+ "      CREATE TABLE States(\r\n"
			+ "        s_name TEXT NOT NULL,\r\n"
			+ "        tax_rate FLOAT NOT NULL,\r\n"
			+ "        country TEXT NOT NULL,\r\n"
			+ "        PRIMARY KEY (s_name),\r\n"
			+ "        FOREIGN KEY (country) REFERENCES Federal_Government(country)\r\n"
			+ "      );\r\n"
			+ "      CREATE TABLE Dependent(\r\n"
			+ "        relation TEXT NOT NULL,\r\n"
			+ "        ssn INT NOT NULL,\r\n"
			+ "        d_name TEXT NOT NULL,\r\n"
			+ "        PRIMARY KEY (ssn)\r\n"
			+ "      );\r\n"
			+ "      \r\n"
			+ "      CREATE TABLE Insurance_Plan(\r\n"
			+ "        i_name TEXT NOT NULL,\r\n"
			+ "        family_premium FLOAT NOT NULL,\r\n"
			+ "        individual_premium FLOAT NOT NULL,\r\n"
			+ "        PRIMARY KEY (i_name)\r\n"
			+ "      );\r\n"
			+ "      CREATE TABLE Benefits(\r\n"
			+ "        benefits_ID INT UNIQUE NOT NULL,\r\n"
			+ "        life_insurance TEXT NOT NULL,\r\n"
			+ "        contribution_401k FLOAT NOT NULL,\r\n"
			+ "        attoreny_plan TEXT NOT NULL,\r\n"
			+ "        health_plan TEXT NOT NULL\r\n"
			+ "      );\r\n"
			+ "      CREATE TABLE Employee(\r\n"
			+ "        name TEXT NOT NULL,\r\n"
			+ "        job_title TEXT NOT NULL,\r\n"
			+ "        salary_type TEXT NOT NULL,\r\n"
			+ "        salary FLOAT NOT NULL,\r\n"
			+ "        employee_ID INT NOT NULL,\r\n"
			+ "        s_name TEXT NOT NULL,\r\n"
			+ "        ssn INT NOT NULL,\r\n"
			+ "	e_ssn INT UNIQUE NOT NULL,\r\n"
			+ "        i_name TEXT NOT NULL,\r\n"
			+ "        e_username TEXT NOT NULL,\r\n"
			+ "        e_Password Text not NULL,\r\n"
			+ "	roll Text NOT NULL,\r\n"
			+ "        benefits_ID INT NOT Null,\r\n"
			+ "        performance TEXT NOT NULL,\r\n"
			+ "        PRIMARY KEY (employee_ID),\r\n"
			+ "        FOREIGN KEY (s_name) REFERENCES States(s_name),\r\n"
			+ "        FOREIGN KEY (ssn) REFERENCES Dependent(ssn),\r\n"
			+ "        FOREIGN KEY (i_name) REFERENCES Insurance_Plan(i_name),\r\n"
			+ "        Foreign Key (benefits_ID) REFERENCES Benefits(benefits_ID)\r\n"
			+ "      );";
	
	public static void main(String[] args) {
		
		Connection c = null;
		Statement stat = null;
		ConnectDB c_db1 = new ConnectDB();
		
		c = c_db1.get_connection();
		
		try {
			
			String query = tables;
			stat = c.createStatement();
			stat.executeUpdate(query);
			System.out.println("Created Tables.");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
