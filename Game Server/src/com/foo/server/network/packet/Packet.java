package com.foo.server.network.packet;

public class Packet {

	private byte[] payload;
	private boolean handled;
	private int readCounter;

	public Packet(byte[] payload) {
		this.payload = payload;
	}

	public boolean isHandled() {
		return handled;
	}

	public void setHandled(boolean handled) {
		this.handled = handled;
	}

	public int getLength() {
		return payload.length;
	}

	public byte readByte() {
		return payload[readCounter++];
	}

	public byte[] readBytes(int amount) {
		byte[] data = new byte[amount];
		System.arraycopy(payload, readCounter, data, 0, amount);
		readCounter += amount;
		return data;
	}

}