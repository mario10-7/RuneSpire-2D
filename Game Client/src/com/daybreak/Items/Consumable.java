package com.daybreak.Items;

import com.daybreak.Screens.GameScreen;

public class Consumable extends Item{

	static final int HEALTH = 0;
	static final int MANA = 1;
	
	int effectValue;
	int effectType;
	String subType;
	
	public Consumable(int id, String name,boolean clickable, String subType, String type, int value,String itemType) {
		super(id,name,clickable,itemType);
	
		if(type.equals("health")){effectType = HEALTH;}
		if(type.equals("mana")){effectType = MANA;}
		
		this.subType = subType;
		effectValue = value;
	}
	
	public void use(){
		String action = "";
		//Determine if food or drink
		if(subType.equals("food")){
			action = "eat";
		}else if(subType.equals("drink")){
			action = "drink";
		}
		
		
		switch(effectType){
		case HEALTH:
			GameScreen.player.playerStats.addHealth(effectValue);
			GameScreen.log.addEntry("You "+action+" the "+ name + ", it heals " + effectValue + " health.");
			break;
		case MANA:
			GameScreen.player.playerStats.addMana(effectValue);
			GameScreen.log.addEntry("You "+action+" the "+ name + ", it heals "+ effectValue + " mana.");
			break;
		}
	}
}
