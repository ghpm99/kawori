package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

	private static DataBaseConnection myInstance;	
	private static Connection connection = null;
	

	private static final String USERNAME = "Kawori";
	private static final String PASSWORD = "5316";
	private static final String URL = "jdbc:mysql://localhost:3306/?useTimezone=true&serverTimezone=UTC";

	
	private static void createConnection() {
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static Connection getConnection() {
		if (connection == null) {
			createConnection();
		}
		return connection;
	}

}
