package com.daybreak.network.management;

import java.io.IOException;

import com.paramvirphagura.network.access.AccessListener;
import com.paramvirphagura.network.access.AccessSession;
import com.paramvirphagura.network.access.LoginEvent;
import com.paramvirphagura.network.access.RegisterEvent;
import com.paramvirphagura.network.packet.PacketBuilder;

public class AccessManager extends Manager implements AccessListener {

	public AccessManager(AccessSession accessSession) {
		super(accessSession);
	}

	@Override
	public void loginPerformed(LoginEvent loginEvent) {
		String username = loginEvent.getUsername(), password = loginEvent
				.getPassword();
		byte[] usernameData = username.getBytes(), passwordData = password
				.getBytes();
		byte[] data = new byte[2 + username.length() + password.length()];
		int position = 0;
		data[position++] = (byte) usernameData.length;
		data[position++] = (byte) passwordData.length;
		System.arraycopy(usernameData, 0, data, position,
				username.getBytes().length);
		position += usernameData.length;
		System.arraycopy(passwordData, 0, data, position, passwordData.length);
		try {
			session.write(PacketBuilder.build((byte) 1, data));
		} catch (IOException e) {
			session.disconnect();
		}
	}

	@Override
	public void registerPerformed(RegisterEvent arg0) {

	}

}