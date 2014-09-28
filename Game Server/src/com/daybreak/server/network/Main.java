package com.daybreak.server.network;

import static com.daybreak.server.network.Settings.Network.Server.DEFAULT_BACKUP_PORT;
import static com.daybreak.server.network.Settings.Network.Server.DEFAULT_PORT;

import java.io.IOException;
import java.sql.SQLException;

import com.daybreak.server.network.management.ServerEngine;
import com.paramvirphagura.network.SocketServer;
import com.paramvirphagura.network.SocketThread;

/**
 * 
 * @author Paramvir Phagura
 *
 */

public final class Main {

	private Main() {
		/**
		 * This class cannot be instantiated.
		 */
	}

	public static void main(String[] arguments) throws Exception {
		if (arguments.length > 0)
			switch (arguments[0]) {
			case "default":
				startServer(DEFAULT_PORT, DEFAULT_BACKUP_PORT);
			}
		else
			printUsage();
	}

	/**
	 * Prints the usage of this application.
	 */
	private static void printUsage() {
		System.out.println("Usage:");
	}

	/**
	 * Starts the server on a given port number. If the port is in use by
	 * another application, use the given backup port.
	 * 
	 * @param port
	 * @param backup_port
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void startServer(int port, int backupPort)
			throws IOException, SQLException {
		System.out.println("Initializing server.....");
		SocketServer socketServer;
		try {
			socketServer = new SocketServer(port);
		} catch (Exception ignored) {
			socketServer = new SocketServer(backupPort);
		}
		System.out.println("Hosting server on port: "
				+ socketServer.getLocalPort());
		SocketThread socketThread = new SocketThread(socketServer);
		ServerEngine serverEngine = new ServerEngine(socketThread);
		socketThread.setSocketListener(serverEngine);
		System.out.println("Listening for incoming client connections....");
		socketThread.start();
	}
}