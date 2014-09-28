package com.daybreak.NPC;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class NPCSprite extends Sprite{
	
	public NPCSprite(String name) {
		super(new Texture(Gdx.files.internal("data/npcs/"+name+".png")));
	}
	
	public void setSprite(String spriteName){
		this.setTexture(new Texture(Gdx.files.internal("data/npcs/"+spriteName+".png")));
		this.setSize(32, 32);
		this.setCenter(16,16);
	}
}
