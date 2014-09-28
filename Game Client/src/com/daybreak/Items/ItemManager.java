package com.daybreak.Items;

import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class ItemManager {

	public ArrayList<Item> itemTemplates;
	
	public ItemManager() {
		itemTemplates = new ArrayList<Item>();
		loadItems();
	}
	
	public Item getItem(int ID){
		for(Item item : itemTemplates){
			if(item.getID()==ID){
				return item;
			}
		}
		return null;
	}
	

	public void buildConsumable(Element itemElement){
			//possible use to add stats or special effects
			int numberOfChildren = itemElement.getChildCount();
		
			//Item attributes
			int itemID = Integer.parseInt(itemElement.getAttribute("id"));
			String itemName = itemElement.getAttribute("name");
			String subType = itemElement.getAttribute("subtype");
			boolean clickable = itemElement.getBooleanAttribute("clickable");
			String itemType = itemElement.getAttribute("type");
			
			//Consumable general
			Element generalElement = itemElement.getChildByName("general");
			String effectType = generalElement.getAttribute("type");
			int effectValue = Integer.parseInt(generalElement.getAttribute("value"));
		
			
			Item item = new Consumable(itemID,itemName,clickable,subType,effectType,effectValue,itemType);
			itemTemplates.add(itemID, item);
		
	}
	
	public void buildWeapon(Element itemElement){
		//Item attributes
		int itemID = Integer.parseInt(itemElement.getAttribute("id"));
		String itemName = itemElement.getAttribute("name");
		String subType = itemElement.getAttribute("subtype");
		boolean clickable = itemElement.getBooleanAttribute("clickable");
		String itemType = itemElement.getAttribute("type");
		
		//Weapon general
		Element generalElement = itemElement.getChildByName("general");
		int damage = Integer.parseInt(generalElement.getAttribute("damage"));
		
		Item item = new Weapon(itemID, itemName,clickable,subType, damage,itemType);
		itemTemplates.add(itemID, item);
	}
	
	public void buildArmor(Element itemElement){
		//Item attributes
		int itemID = Integer.parseInt(itemElement.getAttribute("id"));
		String itemName = itemElement.getAttribute("name");
		String slot = itemElement.getAttribute("slot");
		boolean clickable = itemElement.getBooleanAttribute("clickable");
		String itemType = itemElement.getAttribute("type");
		
		//Armor general
		Element generalElement = itemElement.getChildByName("general");
		int armor = Integer.parseInt(generalElement.getAttribute("armor"));
		
		Item item = new Armor(itemID, itemName,clickable,slot, armor,itemType);
		itemTemplates.add(itemID, item);
	}
	
	public void buildSkillItem(Element itemElement){
		//Item attributes
		int itemID = Integer.parseInt(itemElement.getAttribute("id"));
		String itemName = itemElement.getAttribute("name");
		boolean clickable = itemElement.getBooleanAttribute("clickable");
		String itemType = itemElement.getAttribute("type");
		
		//Skill Item general
		Element generalElement = itemElement.getChildByName("general");
		String type = generalElement.getAttribute("type");
		int experience = generalElement.getIntAttribute("experience");
		
		Item item = new SkillItem(itemID, itemName, clickable, type,experience,itemType);
		itemTemplates.add(itemID, item);
	}
	

	public void loadItems(){
		XmlReader reader = new XmlReader();
		Element root = null;
		try {
			root = reader.parse(Gdx.files.internal("data/items/Items.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Array<Element> items = root.getChildrenByName("item");
		// Loop through each "Item"
		for (Element child : items) {
			
			//Builds new item based on type
			if(child.getAttribute("type").equals("consumable")){buildConsumable(child);}
			if(child.getAttribute("type").equals("weapon")){buildWeapon(child);}
			if(child.getAttribute("type").equals("armor")){buildArmor(child);}
			if(child.getAttribute("type").equals("skill")){buildSkillItem(child);}
			
		}
	}
}
