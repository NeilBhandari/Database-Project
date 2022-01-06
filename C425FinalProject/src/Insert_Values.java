/*
 * THIS CLASS IS TO CREATE THE FIRST ADMIN FOR THE DATA! ONLY RUN ONCE!
 */
import java.sql.Connection;
import java.sql.Statement;

public class Insert_Values {

	public static void main(String[] args) {
		Connection c = null;
		Statement stat = null;
		ConnectDB c_db1 = new ConnectDB();
		
		c = c_db1.get_connection();
		
		try {
			
			String query = "INSERT INTO dependent (relation,ssn,d_name) VALUES (\r\n"
					+ "  'brother',\r\n"
					+ "  '1234567899',\r\n"
					+ "  'John Kreidi'\r\n"
					+ ");\r\n"
					+ "\r\n"
					+ "INSERT INTO Federal_Government (tax_rate, country)\r\n"
					+ "	VALUES (0.22, 'USA');\r\n"
					+ "    \r\n"
					+ "INSERT INTO states (s_name, tax_rate, country)\r\n"
					+ "	VALUES ('IL', 0.2, 'USA');\r\n"
					+ "    \r\n"
					+ "INSERT INTO insurance_plan (i_name, family_premium, individual_premium)\r\n"
					+ "	VALUES ('Insurance 1', 1152, 456);\r\n"
					+ "    \r\n"
					+ "INSERT INTO benefits (benefits_id, life_insurance, contribution_401k, attoreny_plan, health_plan)\r\n"
					+ "	VALUES (0, 'BestOne', 0.07, 'EvenBetter', 'BestHealth');\r\n"
					+ "    \r\n"
					+ "INSERT INTO employee (name, job_title, salary_type,salary, employee_ID, s_name, ssn, e_ssn ,i_name, e_username, e_Password, roll, benefits_ID, performance)\r\n"
					+ "	VALUES ('Labib Kreidi', 'Lead Backend Dev', 'yearly' ,100000, '1', 'IL', 1234567899, 1234567890 ,'Insurance 1', 'admin', '12345', 'admin',0, 'super');";
			stat = c.createStatement();
			stat.executeUpdate(query);
			System.out.println("Inserted Values.");
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	public static void Employee() {
		
	}
	

}
