package com.daybreak.Quests;

public class ReturnObjective extends QuestObjective {

	int npc;
	boolean returned;
	
	public ReturnObjective(int ID, String type, int npc) {
		super(ID, type);
		this.npc = npc;
		returned = false;
	}
	
	public boolean checkConditions(){
		return returned;
	}
	
	public void complete(){
		returned = true;
		System.out.println("returned");
	}
}
