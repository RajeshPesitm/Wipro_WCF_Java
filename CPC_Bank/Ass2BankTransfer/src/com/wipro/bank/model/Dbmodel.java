package com.wipro.bank.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.wipro.bank.util.DBUtil;

public class Dbmodel {

    public static void main(String[] args) {

        Connection connection = null;

        try {
            connection = DBUtil.getDBConnection();

            createAccountTable(connection);
            createTransferTable(connection);
            createSequence(connection);
            insertSampleRecords(connection);

            System.out.println("Database model created successfully.");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // ------------------------------------------------------------
    // Create ACCOUNT_TBL
    // ------------------------------------------------------------
    public static void createAccountTable(Connection connection)
            throws SQLException {

        String checkSql =
                "SELECT COUNT(*) FROM USER_TABLES WHERE TABLE_NAME = 'ACCOUNT_TBL'";

        try (PreparedStatement ps = connection.prepareStatement(checkSql);
             ResultSet rs = ps.executeQuery()) {

            rs.next();

            if (rs.getInt(1) > 0) {
                throw new SQLException("ACCOUNT_TBL already exists.");
            }
        }

        String sql =
                "CREATE TABLE ACCOUNT_TBL (" +
                "ACCOUNT_NUMBER VARCHAR2(10) PRIMARY KEY, " +
                "CUSTOMER_NAME VARCHAR2(15), " +
                "BALANCE NUMBER(10,2)" +
                ")";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    // ------------------------------------------------------------
    // Create TRANSFER_TBL
    // ------------------------------------------------------------
    public static void createTransferTable(Connection connection)
            throws SQLException {

        String checkSql =
                "SELECT COUNT(*) FROM USER_TABLES WHERE TABLE_NAME = 'TRANSFER_TBL'";

        try (PreparedStatement ps = connection.prepareStatement(checkSql);
             ResultSet rs = ps.executeQuery()) {

            rs.next();

            if (rs.getInt(1) > 0) {
                throw new SQLException("TRANSFER_TBL already exists.");
            }
        }

        String sql =
                "CREATE TABLE TRANSFER_TBL (" +
                "TRANSACTION_ID NUMBER(4) PRIMARY KEY, " +
                "ACCOUNT_NUMBER VARCHAR2(10), " +
                "BENEFICIARY_ACCOUNT_NUMBER VARCHAR2(10), " +
                "TRANSACTION_DATE DATE, " +
                "TRANSACTION_AMOUNT NUMBER(10,2), " +
                "CONSTRAINT FK_TRANSFER_ACCOUNT " +
                "FOREIGN KEY (ACCOUNT_NUMBER) " +
                "REFERENCES ACCOUNT_TBL(ACCOUNT_NUMBER), " +
                "CONSTRAINT FK_TRANSFER_BENEFICIARY " +
                "FOREIGN KEY (BENEFICIARY_ACCOUNT_NUMBER) " +
                "REFERENCES ACCOUNT_TBL(ACCOUNT_NUMBER)" +
                ")";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    // ------------------------------------------------------------
    // Create transactionId_seq
    // ------------------------------------------------------------
    public static void createSequence(Connection connection)
            throws SQLException {

        String checkSql =
                "SELECT COUNT(*) FROM USER_SEQUENCES " +
                "WHERE SEQUENCE_NAME = 'TRANSACTIONID_SEQ'";

        try (PreparedStatement ps = connection.prepareStatement(checkSql);
             ResultSet rs = ps.executeQuery()) {

            rs.next();

            if (rs.getInt(1) > 0) {
                throw new SQLException("transactionId_seq already exists.");
            }
        }

        String sql =
                "CREATE SEQUENCE transactionId_seq " +
                "MINVALUE 1000 " +
                "MAXVALUE 9999 " +
                "START WITH 1000 " +
                "INCREMENT BY 1";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    // ------------------------------------------------------------
    // Insert sample records
    // ------------------------------------------------------------
    public static void insertSampleRecords(Connection connection)
            throws SQLException {

        String checkSql =
                "SELECT COUNT(*) FROM ACCOUNT_TBL";

        try (PreparedStatement ps = connection.prepareStatement(checkSql);
             ResultSet rs = ps.executeQuery()) {

            rs.next();

            if (rs.getInt(1) > 0) {
                throw new SQLException(
                        "Sample data already exists in ACCOUNT_TBL.");
            }
        }

        String sql =
                "INSERT INTO ACCOUNT_TBL " +
                "(ACCOUNT_NUMBER, CUSTOMER_NAME, BALANCE) " +
                "VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            // Record 1
            ps.setString(1, "1234567890");
            ps.setString(2, "Reddy");
            ps.setFloat(3, 80000);
            ps.executeUpdate();

            // Record 2
            ps.setString(1, "1234567891");
            ps.setString(2, "Mahesh");
            ps.setFloat(3, 0);
            ps.executeUpdate();

            // Record 3
            ps.setString(1, "1234567892");
            ps.setString(2, "Dhanu");
            ps.setFloat(3, 100);
            ps.executeUpdate();

            // Record 4
            ps.setString(1, "1234567893");
            ps.setString(2, "Sam");
            ps.setFloat(3, 500);
            ps.executeUpdate();
        }
    }
}
