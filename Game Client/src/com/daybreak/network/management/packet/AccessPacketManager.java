package com.daybreak.network.management.packet;

import com.paramvirphagura.network.Session;
import com.paramvirphagura.network.access.AccessPacketListener;
import com.paramvirphagura.network.access.AccessSession;
import com.paramvirphagura.network.packet.Packet;

public class AccessPacketManager extends PacketManager implements
		AccessPacketListener {

	@Override
	public void packetRecieved(Packet packet, Session session) {
		if (!(session instanceof AccessSession))
			/* TODO throw an exception */return;
		if (packet == null)
			/* TODO throw an exception */return;
		else {
			byte operationCode = packet.readByte();
			System.out.println("Operation code: " + operationCode
					+ ", payload length: " + packet.getLength());
			if (operationCode <= GREATEST_OPERATIONAL_CODE)
				super.handle(operationCode, session);
			else {
				switch (operationCode) {

				}
			}
		}
	}

}