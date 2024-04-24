
package databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connection {
	public static String SENDERS_EMAILID = "achyut100@outlook.com";
	public static String SENDERS_PASSWORD = "Dhanraj@100";

	public static Connection get_DBConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iGadget_db", "root", "");

		} catch (Exception e) {
			System.out.println(e);
		}
		return conn;
	}

}
