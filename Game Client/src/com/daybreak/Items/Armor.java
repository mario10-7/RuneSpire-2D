package com.daybreak.Items;

import com.daybreak.Screens.GameScreen;

public class Armor extends Item {

	String slot;
	int armorValue;
	
	public Armor(int id, String name,boolean clickable, String slot, int armorValue,String itemType) {
		super(id,name,clickable,itemType);
		this.slot = slot;
		this.armorValue = armorValue;
	}

	public void use() {
		GameScreen.player.playerEquipment.equipArmor((Armor) GameScreen.iManager.getItem(ID));
	}
	
	public int getArmorValue() {
		return armorValue;
	}
	
	public String getSlot() {
		return slot;
	}
	
	
	
}
