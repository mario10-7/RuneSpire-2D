package com.foo.server.management;

import static com.foo.server.Settings.Database.User.USER_DATABASE_ADDRESS;
import static com.foo.server.Settings.Database.User.USER_DATABASE_PASSWORD;
import static com.foo.server.Settings.Database.User.USER_DATABASE_USERNAME;
import static com.foo.server.Settings.Socket.User.MAXIMUM_AMOUNT_OF_SESSIONS;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import com.foo.server.management.packet.AccessPacketManager;
import com.foo.server.network.Database;
import com.foo.server.network.Disconnectable;
import com.foo.server.network.Session;
import com.foo.server.network.SocketListener;
import com.foo.server.network.SocketThread;
import com.foo.server.network.access.AccessListener;
import com.foo.server.network.access.AccessSession;
import com.foo.server.network.access.LoginEvent;
import com.foo.server.network.access.RegisterEvent;

public class ServerEngine implements SocketListener, Disconnectable,
		AccessListener {

	private SocketThread socketThread;

	private Database userDatabase;
	private Session[] activeSessions = new Session[MAXIMUM_AMOUNT_OF_SESSIONS];
	private AccessPacketManager accessPacketManager;

	public ServerEngine(SocketThread socketThread) {
		this.socketThread = socketThread;
		try {
			userDatabase = new Database(USER_DATABASE_ADDRESS,
					USER_DATABASE_USERNAME, USER_DATABASE_PASSWORD);
		} catch (SQLException e) {
			/* TODO make the user DAO read/write user text files instead */
		} finally {
			accessPacketManager = new AccessPacketManager();
			accessPacketManager.setAccessListener(this);
		}
	}

	@Override
	public void socketAccepted(Socket socket) {
		synchronized (activeSessions) {
			try {
				for (int activeSessionsCounter = 0; activeSessionsCounter < activeSessions.length; activeSessionsCounter++)
					if (activeSessions[activeSessionsCounter] == null) {
						System.out.println("Socket accepted!");
						AccessSession accessSession = (AccessSession) (activeSessions[activeSessionsCounter] = new AccessSession(
								socket));
						accessSession.setPacketListener(accessPacketManager);
						accessSession.setDisconnectable(this);
						accessSession.start();
						break;
					}
			} catch (IOException e) {
				System.out.println("Error creating new user.");
			}
		}
	}

	@SuppressWarnings("finally")
	@Override
	public void disconnectPerformed(Session session) {
		synchronized (activeSessions) {
			for (int activeSessionsCounter = 0; activeSessionsCounter < activeSessions.length; activeSessionsCounter++) {
				if (activeSessions[activeSessionsCounter] == session)
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
						activeSessions[activeSessionsCounter] = null;
						System.out.println("A session was removed.");
						break;
					}
			}
		}
	}

	@Override
	public void registerPerformed(RegisterEvent registerEvent) {
		/* TODO connect to MySQL/file database and process/validate registration */
	}

	@Override
	public void loginPerformed(LoginEvent loginEvent) {
		/* TODO connect to MySQL/file database and validate login */
	}

}