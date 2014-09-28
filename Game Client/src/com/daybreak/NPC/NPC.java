package com.daybreak.NPC;

public class NPC {
	int ID;
	String name;
	
	public NPCPosition npcPosition;
	public NPCSprite npcSprite;
	public NPCMovement npcMovement;
	
	public NPC(int id, String npcName) {
		ID = id;
		name = npcName;
		npcMovement = new NPCMovement();
	}
	
	public void update() {
		npcMovement.update();
	}
	
	public void addPosition(NPCPosition position){
		npcPosition = position;
	}
	
	public void addSprite(NPCSprite sprite){
		npcSprite = sprite;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
