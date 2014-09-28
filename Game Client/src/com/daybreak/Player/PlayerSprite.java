package com.daybreak.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerSprite extends Sprite {

	private static String path = "data/stopped0000.png";

	public PlayerSprite() {
		super(new Texture(Gdx.files.internal(path)));
	}
}
