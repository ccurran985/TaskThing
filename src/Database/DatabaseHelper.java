package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost/wednesdayTaskThing";
	
	private Connection connection;
	
	public DatabaseHelper(){
		connection = getDatabaseConnection();
	}
	
	private Connection getDatabaseConnection(){
		try {
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, "root", "");
			return conn;
		} catch (ClassNotFoundException e) {
			System.out.println("Things went wrong");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Things went wrong");
			e.printStackTrace();
		}
		return null;
	}
}
