package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Exceptions.FailedToRegisterException;
import Exceptions.InvalidCredentialsException;
import Models.User;

public class DatabaseHelper {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/wednesdayTaskThing";

	private Connection connection;

	public DatabaseHelper() {
		connection = getDatabaseConnection();
	}

	private Connection getDatabaseConnection() {
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

	public User login(String username, String password) throws InvalidCredentialsException {
		try {
			PreparedStatement login = connection
					.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
			login.setString(1, username);
			login.setString(2, password);
			ResultSet result = login.executeQuery();
			User user = new User();
			while (result.next()) {
				user.setUsername(result.getString("username"));
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InvalidCredentialsException();
		}
	}

	public User register(String fName, String lName, String username, String password) throws FailedToRegisterException {
		try {
			PreparedStatement register = connection.prepareStatement("INSERT INTO users(fName, lName, username, password) VALUES(?, ?, ?, ?)");
			register.setString(1, fName);
			register.setString(2, lName);
			register.setString(3, username);
			register.setString(4, password);
			register.execute();
			return login(username, password);
		} catch (SQLException | InvalidCredentialsException e) {
			e.printStackTrace();
			throw new FailedToRegisterException();
		}
	}
}
