package com.foo.server.network;

import static com.foo.server.network.packet.opcode.Opcode.KEEP_ALIVE;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.foo.server.network.packet.Packet;
import com.foo.server.network.packet.PacketBuilder;
import com.foo.server.network.packet.PacketListener;

public abstract class Session implements Runnable {

	protected Socket socket;
	protected InputStream inputStream;
	protected OutputStream outputStream;

	protected Thread sessionThread;
	protected String sessionThreadName;

	protected PacketListener packetListener;

	protected static final int PING_TIMEOUT = 5;
	protected long lastKeepAlive;

	protected static final int CORRUPT_PACKET_COUNT_MAXIMUM = 5;
	protected int corruptPacketCounter;

	protected Disconnectable disconnectable;

	protected Session(Socket socket) throws IOException {
		this.socket = socket;
		inputStream = socket.getInputStream();
		outputStream = socket.getOutputStream();
	}

	public Socket getSocket() {
		return socket;
	}

	public Thread getThread() {
		return sessionThread;
	}

	public void setPacketListener(PacketListener packetListener) {
		this.packetListener = packetListener;
	}

	public void start() {
		if (sessionThread == null)
			sessionThread = new Thread(this, sessionThreadName);
		lastKeepAlive = System.currentTimeMillis();
		sessionThread.start();
	}

	public void stop() {
		sessionThread.interrupt();
	}

	public boolean isInterrupted() {
		return sessionThread.isInterrupted();
	}

	public void keepAlive() {
		lastKeepAlive = System.currentTimeMillis();
	}

	public void writePing() throws IOException {
		Packet packet = PacketBuilder.build(KEEP_ALIVE);
		int keepAlivePacketLength = packet.getLength();
		outputStream.write(keepAlivePacketLength);
		outputStream.write(packet.readBytes(keepAlivePacketLength));
	}

	public void corruptPacket() {
		corruptPacketCounter++;
	}

	public void setDisconnectable(Disconnectable disconnectable) {
		this.disconnectable = disconnectable;
	}

	public void disconnect() {
		if (disconnectable == null)
			/* TODO throw an exception */return;
		disconnectable.disconnectPerformed(this);
	}

	public void unregisterAllListeners() {
		packetListener = null;
		disconnectable = null;
	}

}