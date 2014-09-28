package com.daybreak;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.daybreak.Screens.LoadingScreen;
import com.daybreak.network.management.ClientNetworkEngine;

public class Daybreak extends Game {

	private SpriteBatch batch;
	FPSLogger fps;
	public AssetManager assets;
	private ClientNetworkEngine clientEngine;
	
	public Daybreak(ClientNetworkEngine clientEngine) {
		this.clientEngine = clientEngine;
	}

	@Override
	public void create() {
		fps = new FPSLogger();
		batch = new SpriteBatch();
		setScreen(new LoadingScreen(this,clientEngine));
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	@Override
	public void dispose() {
		batch.dispose();
		assets.dispose();
		super.dispose();
	}

	@Override
	public void render() {
		super.render();
		// fps.log();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}