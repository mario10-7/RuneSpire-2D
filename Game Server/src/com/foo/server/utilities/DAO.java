package com.foo.server.utilities;

import com.foo.server.network.Database;

public abstract class DAO {

	protected String path;
	protected String format;
	protected Database database;

	protected DAO(Database database) {
		//this.database = database;
	}

	protected DAO(String path, String format) {
		this.path = path;
		this.format = format;
	}

}