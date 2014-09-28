package com.daybreak.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.daybreak.Daybreak;
import com.daybreak.network.management.ClientNetworkEngine;

public class LoadingScreen implements Screen {

	private Daybreak game;
	private ClientNetworkEngine clientEngine;
	private BitmapFont font;
	private SpriteBatch batch;
	private Texture emptyT;
	private Texture fullT;
	private NinePatch empty;
	private NinePatch full;

	public LoadingScreen(Daybreak pgame,ClientNetworkEngine clientEngine) {
		this.game = pgame;
		this.clientEngine = clientEngine;
		font = new BitmapFont();
		batch = new SpriteBatch();
		emptyT = new Texture(Gdx.files.internal("data/load/empty.png"));
		fullT = new Texture(Gdx.files.internal("data/load/full.png"));
		empty = new NinePatch(new TextureRegion(emptyT, 24, 24), 8, 8, 8, 8);
		full = new NinePatch(new TextureRegion(fullT, 24, 24), 8, 8, 8, 8);
		// which assets do we want to be loaded
		game.assets = new AssetManager();
		game.assets.load("data/introwithbutton.png", Texture.class);
		game.assets.load("data/intro.png", Texture.class);
		game.assets.load("data/BottomPanel.png", Texture.class);
		game.assets.load("data/chatarea.png", Texture.class);
		game.assets.load("data/combatTab.png", Texture.class);
		game.assets.load("data/emoteTab.png", Texture.class);
		game.assets.load("data/EquippedOutline.png", Texture.class);
		game.assets.load("data/equipTab.png", Texture.class);
		game.assets.load("data/friendsTab.png", Texture.class);
		game.assets.load("data/ignoreTab.png", Texture.class);
		game.assets.load("data/libgdx.png", Texture.class);
		game.assets.load("data/inventoryTab.png", Texture.class);
		game.assets.load("data/logoutTab.png", Texture.class);
		game.assets.load("data/magicTab.png", Texture.class);
		game.assets.load("data/miningTab.png", Texture.class);
		game.assets.load("data/music/backgroundmusic.mp3", Music.class);
		game.assets.load("data/contextmenu.png", Texture.class);
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void render(float arg0) {
		// Clear the screen.
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

		batch.begin();
		empty.draw(batch, 40, 225, 720, 30);
		full.draw(batch, 40, 225, game.assets.getProgress() * 720, 30);
		font.drawMultiLine(batch, (int) (game.assets.getProgress() * 100)
				+ "% loaded", 400, 247, 0, BitmapFont.HAlignment.CENTER);
		batch.end();
		if (game.assets.update()) {
			// all the assets are loaded
			game.setScreen(new LoginScreen(game,clientEngine));
			dispose();
		}
	}

	@Override
	public void resize(int arg0, int arg1) {
	}

	@Override
	public void resume() {
	}

	@Override
	public void show() {
	}

	@Override
	public void dispose() {
	}
}