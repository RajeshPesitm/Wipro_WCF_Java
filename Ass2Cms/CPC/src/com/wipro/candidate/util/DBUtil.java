package com.wipro.candidate.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	public static Connection getDBConn() {
		Connection con = null;
		// write code here
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "B2026132613074", "B2026132613074");
		} catch (Exception e) {
			System.out.println("[DBUtil Error] Connection failed! Details below:");
            e.printStackTrace(); // This prints out the exact network/credential error
			con = null;
		}
		return con;
	}
}
