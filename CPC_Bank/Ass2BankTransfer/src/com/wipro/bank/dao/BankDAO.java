package com.wipro.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wipro.bank.bean.TransferBean;
import com.wipro.bank.util.DBUtil;

public class BankDAO {

	public boolean validateAccount(String accountNumber) {
		boolean validAccountStatus = false;

		String sql = "SELECT ACCOUNT_NUMBER FROM ACCOUNT_TBL "
				   + "WHERE ACCOUNT_NUMBER = ?";

		try (Connection connection = DBUtil.getDBConnection();
			 PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, accountNumber);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					validAccountStatus = true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return validAccountStatus;
	}

	public float findBalance(String accountNumber) {
		float balance = -1f;

		String sql = "SELECT BALANCE FROM ACCOUNT_TBL "
				   + "WHERE ACCOUNT_NUMBER = ?";

		try (Connection connection = DBUtil.getDBConnection();
			 PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, accountNumber);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					balance = rs.getFloat("BALANCE");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return balance;
	}

	public boolean updateBalance(String accountNumber, float newBalance) {
		boolean status = false;

		String sql = "UPDATE ACCOUNT_TBL "
				   + "SET BALANCE = ? "
				   + "WHERE ACCOUNT_NUMBER = ?";

		try (Connection connection = DBUtil.getDBConnection();
			 PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setFloat(1, newBalance);
			ps.setString(2, accountNumber);

			int rowsUpdated = ps.executeUpdate();

			if (rowsUpdated > 0) {
				status = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	public boolean transferMoney(TransferBean transferBean) {
		boolean transferStatus = false;

		String sql = "INSERT INTO TRANSFER_TBL "
				   + "(TRANSACTION_ID, ACCOUNT_NUMBER, "
				   + "BENEFICIARY_ACCOUNT_NUMBER, TRANSACTION_DATE, "
				   + "TRANSACTION_AMOUNT) "
				   + "VALUES (?, ?, ?, ?, ?)";

		try (Connection connection = DBUtil.getDBConnection();
			 PreparedStatement ps = connection.prepareStatement(sql)) {

			int transactionID = generateSequenceNumber();

			ps.setInt(1, transactionID);
			ps.setString(2, transferBean.getFromAccountNumber());
			ps.setString(3, transferBean.getToAccountNumber());

			// Use today's date
			ps.setDate(4, new Date(System.currentTimeMillis()));

			ps.setFloat(5, transferBean.getAmount());

			int rowsInserted = ps.executeUpdate();

			if (rowsInserted > 0) {
				transferStatus = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return transferStatus;
	}

	public int generateSequenceNumber() {

		int sequenceNumber = -1;

		String sql = "SELECT transactionId_seq.NEXTVAL FROM DUAL";

		try (Connection connection = DBUtil.getDBConnection();
			 PreparedStatement ps = connection.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {

			if (rs.next()) {
				sequenceNumber = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sequenceNumber;
	}
}
