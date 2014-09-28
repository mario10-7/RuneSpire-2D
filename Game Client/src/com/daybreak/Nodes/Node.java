package com.daybreak.Nodes;

import com.badlogic.gdx.math.Vector2;
import com.daybreak.Items.Item;


public class Node {

	int x, y;
	String type;
	Item contains;
	String experienceType;
	int experience;
	private float emptyTime = 0;
	private boolean empty = true;
	String name;
	
	

	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setType(String typeToSet){
		type = typeToSet;
	}
	
	public void setContains(Item item){
		contains = item;
	}
	
	public Item getContains(){
		return contains;
	}
	
	public String getType(){
		return type;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

	public String getExperienceType() {
		return experienceType;
	}

	public void setExperienceType(String experienceType) {
		this.experienceType = experienceType;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public float getEmptyTime() {
		return emptyTime;
	}

	public void setEmptyTime(float emptyTime) {
		this.emptyTime = emptyTime;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	
}
