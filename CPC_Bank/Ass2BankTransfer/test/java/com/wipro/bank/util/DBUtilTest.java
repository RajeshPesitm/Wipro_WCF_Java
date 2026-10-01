package com.wipro.bank.util;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DBUtilTest {

	@Test
    @DisplayName("Should return a live connection when the database is running")
	void testGetDBConnection_Success() {
        // Act
        Connection connection = DBUtil.getDBConnection();

        // Assert
        assertNotNull(connection, "Connection failed! Check if your Oracle DB is running on port 1521.");
        
        // Cleanup: Close the connection if it succeeded to save DB resources
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                fail("Failed to close the test connection cleanly: " + e.getMessage());
            }
        }
	}

}
