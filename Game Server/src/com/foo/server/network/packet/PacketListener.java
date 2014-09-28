package com.foo.server.network.packet;

import com.foo.server.network.Session;

public abstract interface PacketListener {

	void packetRecieved(Packet packet, Session session);

}