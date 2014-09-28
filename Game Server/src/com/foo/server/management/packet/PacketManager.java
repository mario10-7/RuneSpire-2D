package com.foo.server.management.packet;

import static com.foo.server.network.packet.opcode.Opcode.DISCONNECT;
import static com.foo.server.network.packet.opcode.Opcode.KEEP_ALIVE;

import java.io.IOException;

import com.foo.server.network.Session;
import com.foo.server.network.packet.PacketListener;

public abstract class PacketManager implements PacketListener {

	public static final int GREATEST_OPERATIONAL_CODE = KEEP_ALIVE;

	protected void handle(int operationCode, Session session) {
		switch (operationCode) {
		case DISCONNECT:
			session.disconnect();
			break;
		case KEEP_ALIVE:
			try {
				session.writePing();
				session.keepAlive();
			} catch (IOException ignored) {
				session.disconnect();
			}
			break;
		default:
			System.out.println("Unhandled packet with ID: " + operationCode);
		}
	}

}