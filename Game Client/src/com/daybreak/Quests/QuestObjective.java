package com.daybreak.Quests;

public class QuestObjective {

	int ID;
	String type;
	String text;
	
	public QuestObjective(int ID,String type) {
		this.ID = ID;
		this.type = type;
	}
	
	public void complete(){}
	
	public boolean checkConditions() {
		return false;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	

}
