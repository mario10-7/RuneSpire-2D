package com.foo.server.network.packet;

public class PacketBuilder {

	public static Packet build(byte operationCode) {
		byte payload[] = new byte[1];
		payload[0] = operationCode;
		return new Packet(payload);
	}

	public static Packet build(byte[] payload) {
		return new Packet(payload);
	}

	public static Packet build(byte operationCode, byte[] data) {
		byte payload[] = new byte[1 + data.length];
		payload[0] = operationCode;
		System.arraycopy(data, 0, payload, 1, data.length);
		return new Packet(payload);
	}

}