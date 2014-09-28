package com.daybreak.network.management.packet;

import static com.paramvirphagura.network.packet.opcode.Opcode.DISCONNECT;
import static com.paramvirphagura.network.packet.opcode.Opcode.KEEP_ALIVE;

import java.io.IOException;

import com.paramvirphagura.network.Session;
import com.paramvirphagura.network.packet.PacketListener;

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
			} catch (IOException e) {
				session.disconnect();
			}
			break;
		default:
			System.out.println("Unhandled packet with ID: " + operationCode);
		}
	}

}