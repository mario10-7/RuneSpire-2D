package com.daybreak.Quests;

import com.daybreak.Screens.GameScreen;

public class CollectObjective extends QuestObjective {

	int amount;
	int itemID;
	
	public CollectObjective(int ID, String type, int amount, int itemID) {
		super(ID,type);
		this.amount = amount;
		this.itemID = itemID;
	}
	
	public boolean checkConditions(){
		if(GameScreen.player.playerInventory.contains(itemID, amount)){
			return true;
		}
		return false;
	}
	
	public int getItemID() {
		return itemID;
	}
	
	public int getAmount() {
		return amount;
	}
}
