package com.khadri.jakarta.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UtilDao {

	public Connection getNewConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/2024_batch", "root", "root");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Driver class Exception : " + cnfe);
		} catch (SQLException sqle) {
			System.out.println("SQL Exception " + sqle);
		}

		return con;

	}
}
