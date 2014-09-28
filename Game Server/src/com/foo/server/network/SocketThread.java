package com.foo.server.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketThread implements Runnable {

	private SocketServer socketServer;
	private Thread socketThread;
	private boolean isRunning;
	private String socketThreadName = "Socket Listener";
	private SocketListener socketListener;

	public SocketThread(SocketServer socketServer) {
		this.socketServer = socketServer;
		socketThread = new Thread(this, socketThreadName);
	}

	public void start() {
		if (isRunning)
			throw new IllegalStateException(socketThreadName
					+ " is already running.");
		isRunning = true;
		socketThread.start();
	}

	public void stop() {
		if (!isRunning)
			throw new IllegalStateException(socketThreadName
					+ " is already sleeping");
	}

	@Override
	public void run() {
		ServerSocket serverSocket = socketServer.getServerSocket();
		while (serverSocket.isBound() && isRunning) {
			try {
				fireSocketAcceptedEvent(serverSocket.accept());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void setSocketListener(SocketListener socketListener) {
		this.socketListener = socketListener;
	}

	public void fireSocketAcceptedEvent(Socket socket) {
		socketListener.socketAccepted(socket);
	}

}