package com.foo.server.network;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketServer {

	public static final int MAXIMUM_PORT_NUMBER = 65535;
	public static final int[] RESERVED_PORTS = {};

	private ServerSocket serverSocket;

	public SocketServer(int port) throws IOException {
		if (port > -1 && port < MAXIMUM_PORT_NUMBER)
			;
		for (int portCheck : RESERVED_PORTS)
			if (portCheck == port)
				;
		serverSocket = new ServerSocket(port);
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public int getLocalPort() {
		return serverSocket.getLocalPort();
	}

	public void close() throws IOException {
		serverSocket.close();
	}

}