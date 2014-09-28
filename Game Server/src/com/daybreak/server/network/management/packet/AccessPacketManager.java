package com.daybreak.server.network.management.packet;

import static com.paramvirphagura.network.packet.opcode.AccessOpcode.REGISTER;

import com.paramvirphagura.network.Session;
import com.paramvirphagura.network.access.AccessListener;
import com.paramvirphagura.network.access.AccessPacketListener;
import com.paramvirphagura.network.access.AccessSession;
import com.paramvirphagura.network.access.LoginEvent;
import com.paramvirphagura.network.packet.Packet;

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