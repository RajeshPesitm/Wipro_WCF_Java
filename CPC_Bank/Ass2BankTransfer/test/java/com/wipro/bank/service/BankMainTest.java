package com.wipro.bank.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.wipro.bank.bean.TransferBean;
import com.wipro.bank.util.DBUtil;

class BankMainTest {


	private static Connection connection;
	private static BankMain bankMain;

	@BeforeAll
	public static void setUp() throws SQLException {

		// Create objects/resources required for testing
		connection = DBUtil.getDBConnection();
		bankMain = new BankMain();
	}

	@Test
	public void testReddyToSam() {

		// Reddy -> 100 -> Sam
		TransferBean transferBean = new TransferBean();

		transferBean.setFromAccountNumber("1234567890");
		transferBean.setToAccountNumber("1234567893");
		transferBean.setAmount(100);

		String result = bankMain.transfer(transferBean);

		assertEquals("SUCCESS", result);
	}

	@Test
	public void testSamToReddy() {

		// Sam -> 100 -> Reddy
		TransferBean transferBean = new TransferBean();

		transferBean.setFromAccountNumber("1234567893");
		transferBean.setToAccountNumber("1234567890");
		transferBean.setAmount(100);

		String result = bankMain.transfer(transferBean);

		assertEquals("SUCCESS", result);
	}

	@AfterAll
	public static void tearDown() throws SQLException {

		/*
		 * Restore original balances so that the database
		 * remains in its original state after testing.
		  		String sql =
				"UPDATE ACCOUNT_TBL " +
				"SET BALANCE = ? " +
				"WHERE ACCOUNT_NUMBER = ?";

		try (PreparedStatement ps =
				connection.prepareStatement(sql)) {

			// Restore Reddy = 80000
			ps.setFloat(1, 80000);
			ps.setString(2, "1234567890");
			ps.executeUpdate();

			// Restore Sam = 500
			ps.setFloat(1, 500);
			ps.setString(2, "1234567893");
			ps.executeUpdate();
		}
		 */


		// Close database connection
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}
}
