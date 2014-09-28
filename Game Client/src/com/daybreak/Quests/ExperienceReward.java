package com.daybreak.Quests;

public class ExperienceReward extends QuestReward {

	String skill;
	int amount;
	
	public ExperienceReward(int ID, String type, String skill, int amount) {
		super(ID,type);
		this.skill = skill;
		this.amount = amount;
	}
}
