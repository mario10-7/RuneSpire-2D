package com.daybreak.server.network.management.packet;

import static com.daybreak.server.network.packet.opcode.PlayerOpcode.PLAYER_COORDINATE_UPDATE;

import com.paramvirphagura.network.Session;
import com.paramvirphagura.network.access.AccessSession;
import com.paramvirphagura.network.packet.Packet;

public class PlayerPacketManager extends PacketManager {

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
				case PLAYER_COORDINATE_UPDATE:
					byte playerCoordinateX = packet.readByte();
					byte playerCoordinateY = packet.readByte();
					System.out.println(playerCoordinateX + playerCoordinateY);	
				}
			}
		}
	}

}