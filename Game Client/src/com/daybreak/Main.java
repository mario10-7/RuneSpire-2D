package com.daybreak;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.daybreak.network.management.ClientNetworkEngine;
import com.paramvirphagura.network.SocketClient;

public class Main {

	public static void main(String[] args) throws Exception {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		SocketClient socketClient = null;
		socketClient = new SocketClient("localhost", 1000);
		ClientNetworkEngine clientEngine = new ClientNetworkEngine();
		clientEngine.start(socketClient, ClientNetworkEngine.DEFAULT_STAGE);
		cfg.title = "Daybreak";
		cfg.useGL30 = true;
		cfg.resizable = false;
		cfg.width = 800;
		cfg.height = 600;
		cfg.addIcon("data/ic_launcher.png", FileType.Internal);
		new LwjglApplication(new Daybreak(clientEngine), cfg);
	}
}
