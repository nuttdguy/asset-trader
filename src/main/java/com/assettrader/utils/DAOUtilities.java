package com.assettrader.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOUtilities {
	
	private static final String CONNECTION_USERNAME = "assettrader_user";
	private static final String CONNECTION_PASSWORD = "assettrader_password";
	private static final String URL = "jdbc:mysql://localhost:3306/assettrader";
	private static Connection connection;
	

	public static synchronized Connection getConnection() throws SQLException {
		if (connection == null) {
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Could not register driver!");
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		
		if (connection.isClosed()) {
			System.out.println("Opening new connection...");
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		return connection;		
	}
	
}
