package com.foo.server.network;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private Connection connection;

	public Database(String address, String username, String password)
			throws SQLException {
		connection = DriverManager.getConnection(address, username, password);
	}

	public boolean isConnected() {
		try {
			return connection.isValid(5);
		} catch (SQLException e) {
			return false;
		}
	}

}