package com.foo.server.network.access;

import java.io.IOException;
import java.net.Socket;

import com.foo.server.network.Session;
import com.foo.server.network.packet.Packet;
import com.foo.server.network.packet.PacketListener;

public class AccessSession extends Session {

	private AccessPacketListener accessPacketListener;

	public AccessSession(Socket socket) throws IOException {
		super(socket);
		sessionThreadName = "AccessThread: "
				+ socket.getLocalSocketAddress().toString();
	}

	@Override
	public void start() {
		super.start();
	}

	@Override
	public void stop() {
		super.stop();
	}

	@Override
	public void setPacketListener(PacketListener packetListener) {
		if (!(packetListener instanceof AccessPacketListener))
			/* TODO throw an exception */return;
		super.setPacketListener(packetListener);
		this.accessPacketListener = (AccessPacketListener) this.packetListener;
	}

	@Override
	public void run() {
		long currentTime = 0;
		while (!socket.isClosed()
				&& ((currentTime = System.currentTimeMillis()) - lastKeepAlive) / 1000 <= PING_TIMEOUT
				&& !isInterrupted()) {
			try {
				if (corruptPacketCounter >= CORRUPT_PACKET_COUNT_MAXIMUM)
					break;
				if (inputStream.available() >= 1
						&& accessPacketListener != null) {
					int length = inputStream.read();
					if (inputStream.available() >= length
							&& accessPacketListener != null) {
						byte[] data = new byte[length];
						inputStream.read(data, 0, length);
						accessPacketListener.packetRecieved(new Packet(data),
								this);
					} else {
						System.out
								.println("Corrupt packet for access session: "
										+ sessionThreadName);
					}
				} else {
					System.out.println("No buffer to read for access thread: "
							+ sessionThreadName + ".");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ignored) {
						break;
					}
				}
			} catch (IOException ignored) {
				disconnect();
			}
		}
		if ((currentTime - lastKeepAlive) / 1000 > PING_TIMEOUT
				|| corruptPacketCounter >= CORRUPT_PACKET_COUNT_MAXIMUM) {
			disconnect();
		}
	}

}