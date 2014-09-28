package com.daybreak.Items;

import com.daybreak.Screens.GameScreen;

public class Weapon extends Item{

	int damage;
	String subType;
	
	
	public Weapon(int id, String name, boolean clickable, String subType,int damage,String itemType) {
		super(id, name, clickable,itemType);
		this.subType = subType;
		this.damage = damage;
	}
	
	public void use(){
		GameScreen.player.playerEquipment.equipWeapon((Weapon) GameScreen.iManager.getItem(ID));
	}
	
	public int getDamage(){
		return damage;
	}
	
	public String getSubType() {
		return subType;
	}


}
