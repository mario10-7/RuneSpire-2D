package com.foo.server.network;

import java.net.Socket;

public interface SocketListener {

	void socketAccepted(Socket socket);

}