package com.dasol.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
	public static Connection getConnection() throws Exception {
		return DriverManager.getConnection("jdbc:apache:commons:dbcp:myboard");
	}
}
