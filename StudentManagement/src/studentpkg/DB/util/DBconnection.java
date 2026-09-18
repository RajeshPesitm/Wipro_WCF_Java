package studentpkg.DB.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBconnection {

	public DBconnection() {
		// TODO Auto-generated constructor stub
	}
	public static void check(String[] args) {

		// 1. Define connection parameters
		String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
		String user = "wcf";
		String password = "wcf123";

		System.out.println("====== ORACLE CONNECTIVITY TEST ======");

		try (Connection con = DriverManager.getConnection(url, user, password)) {
			// If we reach here, connection exists!
			System.out.println("[SUCCESS] Connected to the database successfully.");

			// 2. Check Identity (Who am I and where am I connected?)
			try (Statement stmt = con.createStatement()) {

				// Get Current User
				try (ResultSet rs = stmt.executeQuery("SELECT user FROM dual")) {
					if (rs.next()) {
						System.out.println("[INFO] Current Session User: " + rs.getString(1));
					}
				}

				// Get Container Name (Should be XEPDB1)
				try (ResultSet rs = stmt.executeQuery("SELECT sys_context('USERENV', 'CON_NAME') FROM dual")) {
					if (rs.next()) {
						System.out.println("[INFO] Connected Container  : " + rs.getString(1));
					}
				}
			}

			// 3. Check Table Visibility
			System.out.println("[INFO] Checking table visibility...");
			DatabaseMetaData metaData = con.getMetaData();

			// Oracle stores table names in UPPERCASE internally
			try (ResultSet tables = metaData.getTables(null, user.toUpperCase(), "STUDENT", null)) {
				if (tables.next()) {
					System.out.println("[SUCCESS] Your Java application CAN see the 'STUDENT' table!");
				} else {
					System.out.println("[FAIL] The 'STUDENT' table was NOT found in schema: " + user.toUpperCase());
					System.out.println("       Reason: The table might be in another schema, or lowercase in double quotes.");
				}
			}

		} catch (Exception e) {
			System.out.println("[ERROR] Connection failed!");
			e.printStackTrace();
		}

		System.out.println("======================================");
	}

}
