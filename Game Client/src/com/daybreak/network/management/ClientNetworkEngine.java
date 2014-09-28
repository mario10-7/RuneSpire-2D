package com.daybreak.network.management;

import java.io.IOException;
import java.net.Socket;

import com.daybreak.network.management.packet.AccessPacketManager;
import com.paramvirphagura.network.Disconnectable;
import com.paramvirphagura.network.Session;
import com.paramvirphagura.network.SocketClient;
import com.paramvirphagura.network.access.AccessSession;
import com.paramvirphagura.network.packet.Packet;

public class ClientNetworkEngine implements Disconnectable {

	public static final int DEFAULT_STAGE = 0;

	private Session session;

	public void start(SocketClient socketClient, int stage) {
		try {
			switch (stage) {
			case 0:
				session = new AccessSession(socketClient.getSocket());
				session.setPacketListener(new AccessPacketManager());
				session.setDisconnectable(this);
				session.start();
				session.writePing();
				break;
			}
		} catch (IOException ignored) {
			System.err.println("There was an error starting.");
		}
	}

	public void writePacketToCurrentSession(Packet packet) {
		if (session != null)
			try {
				session.write(packet);
			} catch (IOException ignored) {
				session.disconnect();
			}
	}

	@Override
	public void disconnectPerformed(Session session) {
		try {
			Socket socketToClose = session.getSocket();
			if (!socketToClose.isInputShutdown())
				socketToClose.getInputStream().close();
			if (socketToClose.isOutputShutdown())
				socketToClose.getOutputStream().close();
			if (!socketToClose.isClosed())
				socketToClose.close();
			session.getThread().join(2000);
		} catch (IOException | InterruptedException ignored) {
		} finally {
			session.unregisterAllListeners();
			session = null;
			shutdown();
		}
	}

	private void shutdown() {
		System.out.println("Shutting down....");
		System.exit(0);
	}

}