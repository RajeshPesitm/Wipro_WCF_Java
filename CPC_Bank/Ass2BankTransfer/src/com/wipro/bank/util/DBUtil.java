package com.wipro.bank.util;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBUtil {
	private static Connection connection = null;

	public static Connection getDBConnection() {
		//write code here
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "B2026132613074", "B2026132613074");
		} catch (Exception e) {
			System.out.println("[DBUtil Error] Connection failed! Details below:");
            //e.printStackTrace(); // This prints out the exact network/credential error
			connection = null;
		}
		return connection;
	}
}
