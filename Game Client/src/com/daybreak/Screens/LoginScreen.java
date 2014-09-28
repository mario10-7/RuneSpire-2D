package com.daybreak.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.daybreak.Daybreak;
import com.daybreak.network.management.ClientNetworkEngine;

public class LoginScreen implements Screen {

	Daybreak game;
	private SpriteBatch batch;
	private Texture backgroundT,musicT,musicmutedT;
	private Sprite background,music,musicmuted;
	private Music backgroundMusic;
	private boolean muted = false;
	private ClientNetworkEngine cne;

	public LoginScreen(Daybreak pgame,ClientNetworkEngine cne) {
		this.game = pgame;
		this.cne = cne;
		batch = new SpriteBatch();
		backgroundMusic = game.assets.get("data/music/backgroundmusic.mp3", Music.class);
		backgroundT = game.assets.get("data/introwithbutton.png", Texture.class);
		background = new Sprite(backgroundT);
		musicT = new Texture(Gdx.files.internal("data/music.png"));
		music = new Sprite(musicT);
		musicmutedT = new Texture(Gdx.files.internal("data/musicmute.png"));
		musicmuted = new Sprite(musicmutedT);
		music.setPosition(800-38, 2);
		musicmuted.setPosition(800-38, 2);
		backgroundMusic.setLooping(true);
		backgroundMusic.play();
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

		batch.begin();
		background.draw(batch);
		
		if (!muted) {
			music.draw(batch);
		} else {
			musicmuted.draw(batch);
		}
		
		batch.end();
		
		Gdx.input.setInputProcessor(new InputAdapter() {
		    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		        if (button == Buttons.LEFT) {
		        	if (Gdx.input.getX() < 800-2 && Gdx.input.getY() < 600-2 && Gdx.input.getX() >= 800-38 && Gdx.input.getY() >= 600-38){
		    			if (muted) {
		    				backgroundMusic.play();
		    				muted = false;
		    				backgroundMusic.setVolume(1f);
		    			} else {
		    				backgroundMusic.pause();
		    				muted = true;
		    				backgroundMusic.setVolume(0f);
		    			}
		    		}
		        }
				return false;
		    }
		});
		if (Gdx.input.isButtonPressed(0) && Gdx.input.getX() < 10+64 && Gdx.input.getY() < 10+32 && Gdx.input.getX() >= 10 && Gdx.input.getY() >= 10){
			game.setScreen(new GameScreen(game, cne));
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
		backgroundMusic.dispose();
	}
}