import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
	/*
	public static void main(String[] args) {
		ConnectDB c_db1 = new ConnectDB();
		
		System.out.println(c_db1.get_connection());
	}*/
	
	public Connection get_connection() {
		
		Connection c = null;
		String host = "localhost";
		String port = "5432";
		String db_name = "postgres";
		String username = "postgres";
		String password = "12345";
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://"+host+":"+port+"/"+db_name,
	            username, password);
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      //System.out.println("Opened database successfully!!");
	      return c;
	}

}
