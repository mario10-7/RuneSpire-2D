package com.daybreak.Quests;

public class ItemReward extends QuestReward {

	int itemID;
	int amount;
	
	public ItemReward(int ID,String type, int amount, int itemID) {
		super(ID,type);
		this.itemID = itemID;
		this.amount = amount;
	}
}
