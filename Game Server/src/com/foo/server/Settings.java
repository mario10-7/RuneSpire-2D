package com.foo.server;

public final class Settings {

	private Settings() {

	}

	public static final boolean DEBUG = true;

	public static class Network {

		public static class Server {

			public static final int DEFAULT_PORT = 15830,
					DEFAULT_BACKUP_PORT = -1;

		}

	}

	public static class Database {

		public static class User {

			public static final String USER_DATABASE_ADDRESS = null;
			public static final String USER_DATABASE_USERNAME = null,
					USER_DATABASE_PASSWORD = null;

		}

	}

	public static class Socket {

		public static class User {

			public static final int MAXIMUM_AMOUNT_OF_SESSIONS = 10;

		}

	}

}