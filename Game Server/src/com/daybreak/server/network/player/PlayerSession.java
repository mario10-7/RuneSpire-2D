package com.daybreak.server.network.player;

import java.io.IOException;
import java.net.Socket;

import com.paramvirphagura.network.Session;

public class PlayerSession extends Session {

	protected PlayerSession(Socket socket) throws IOException {
		super(socket);
	}

	@Override
	public void run() {
		
	}

}