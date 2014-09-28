package com.daybreak.Items;

public class SkillItem extends Item {

	String type;

	
	public SkillItem(int id, String name, boolean clickable, String type, int experience,String itemType) {
		super(id,name,clickable,itemType);
		this.type = type;
		harvestExperience = experience;
	}

	public void use() {
	}
}
