package com.daybreak.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public abstract class Item {

	int ID;
	boolean clickable;
	String name ="";
	Texture sprite;
	int harvestExperience;
	String type = "";
	
	public Item(int id, String name, boolean clickable, String type) {
		this.ID = id;
		this.name = name;
		this.clickable = clickable;
		this.type = type;
		
		sprite = new Texture(Gdx.files.internal("data/items/"+ID+".png"));
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public Texture getSprite(){
		return sprite;
	}
	
	public int getID(){
		return ID;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean isClickable(){
		return clickable;
	}
	
	public abstract void use();
	
	public int getExperience(){
		return harvestExperience;
	}

	public String getType() {
		return type;
	}
	
	
}
