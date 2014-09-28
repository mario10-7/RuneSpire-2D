package com.foo.server.management.packet;

import static com.foo.server.network.packet.opcode.AccessOpcode.REGISTER;

import com.foo.server.network.Session;
import com.foo.server.network.access.AccessListener;
import com.foo.server.network.access.AccessPacketListener;
import com.foo.server.network.access.AccessSession;
import com.foo.server.network.access.LoginEvent;
import com.foo.server.network.packet.Packet;

public class AccessPacketManager extends PacketManager implements
		AccessPacketListener {

	private AccessListener accessListener;

	public void setAccessListener(AccessListener accessListener) {
		this.accessListener = accessListener;
	}

	@Override
	public void packetRecieved(Packet packet, Session session) {
		if (!(session instanceof AccessSession))
			/* TODO throw an exception */;
		if (packet == null)
			System.out
					.println("Improper use of API: a null packet was given to the access packet manager....");
		else {
			byte operationCode = packet.readByte();
			System.out.println("Operation code: " + operationCode
					+ ", payload length: " + packet.getLength());
			if (operationCode <= GREATEST_OPERATIONAL_CODE)
				super.handle(operationCode, session);
			else {
				switch (operationCode) {
				case REGISTER:
					byte usernameLength = packet.readByte(),
					passwordLength = packet.readByte();
					String username = new String(
							packet.readBytes(usernameLength));
					String password = new String(
							packet.readBytes(passwordLength));
					System.out.println("Username: " + username + "\tPassword: "
							+ password);
					accessListener.loginPerformed(new LoginEvent(username,
							password));
				}
			}
		}
	}

}