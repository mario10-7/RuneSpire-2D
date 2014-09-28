package com.foo.server.network.packet.opcode;

public abstract class Opcode {

	public static final byte DISCONNECT = -1;
	public static final byte KEEP_ALIVE = 0;

	protected Opcode() {

	}

}