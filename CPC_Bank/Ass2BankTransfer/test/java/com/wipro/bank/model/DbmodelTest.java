package com.wipro.bank.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.wipro.bank.util.DBUtil;

public class DbmodelTest {

    private static Connection connection;

    @BeforeAll
    public static void setUp() throws SQLException {
        connection = DBUtil.getDBConnection();
    }

    // ------------------------------------------------------------
    // Test ACCOUNT_TBL creation
    // ------------------------------------------------------------
    @Test
    public void testAccountTableExists() throws SQLException {

        String sql =
                "SELECT COUNT(*) " +
                "FROM USER_TABLES " +
                "WHERE TABLE_NAME = 'ACCOUNT_TBL'";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            rs.next();

            assertEquals(1, rs.getInt(1),
                    "ACCOUNT_TBL should exist");
        }
    }

    // ------------------------------------------------------------
    // Test TRANSFER_TBL creation
    // ------------------------------------------------------------
    @Test
    public void testTransferTableExists() throws SQLException {

        String sql =
                "SELECT COUNT(*) " +
                "FROM USER_TABLES " +
                "WHERE TABLE_NAME = 'TRANSFER_TBL'";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            rs.next();

            assertEquals(1, rs.getInt(1),
                    "TRANSFER_TBL should exist");
        }
    }

    // ------------------------------------------------------------
    // Test Sequence creation
    // ------------------------------------------------------------
    @Test
    public void testSequenceExists() throws SQLException {

        String sql =
                "SELECT COUNT(*) " +
                "FROM USER_SEQUENCES " +
                "WHERE SEQUENCE_NAME = 'TRANSACTIONID_SEQ'";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            rs.next();

            assertEquals(1, rs.getInt(1),
                    "transactionId_seq should exist");
        }
    }

    // ------------------------------------------------------------
    // Test sample records
    // ------------------------------------------------------------
    @Test
    public void testSampleRecords() throws SQLException {

        String sql =
                "SELECT COUNT(*) " +
                "FROM ACCOUNT_TBL " +
                "WHERE ACCOUNT_NUMBER IN " +
                "('1234567890', '1234567891', '1234567892', '1234567893')";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            rs.next();

            assertEquals(4, rs.getInt(1),
                    "Four sample accounts should exist");
        }
    }

    // ------------------------------------------------------------
    // Test Reddy account
    // ------------------------------------------------------------
    @Test
    public void testReddyAccount() throws SQLException {

        String sql =
                "SELECT CUSTOMER_NAME, BALANCE " +
                "FROM ACCOUNT_TBL " +
                "WHERE ACCOUNT_NUMBER = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, "1234567890");

            try (ResultSet rs = ps.executeQuery()) {

                assertTrue(rs.next(),
                        "Reddy account should exist");

                assertEquals("Reddy", rs.getString("CUSTOMER_NAME"));

                assertEquals(80000.0,
                        rs.getDouble("BALANCE"),
                        0.01);
            }
        }
    }

    // ------------------------------------------------------------
    // Test Mahesh account with zero balance
    // ------------------------------------------------------------
    @Test
    public void testMaheshAccount() throws SQLException {

        String sql =
                "SELECT CUSTOMER_NAME, BALANCE " +
                "FROM ACCOUNT_TBL " +
                "WHERE ACCOUNT_NUMBER = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, "1234567891");

            try (ResultSet rs = ps.executeQuery()) {

                assertTrue(rs.next(),
                        "Mahesh account should exist");

                assertEquals("Mahesh",
                        rs.getString("CUSTOMER_NAME"));

                assertEquals(0.0,
                        rs.getDouble("BALANCE"),
                        0.01);
            }
        }
    }
    
    // ------------------------------------------------------------
    // Test Mahesh account with zero balance
    // ------------------------------------------------------------
    @AfterAll
    public static void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}

