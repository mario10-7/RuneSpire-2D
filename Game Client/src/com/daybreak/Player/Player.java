package com.daybreak.Player;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.daybreak.Items.Armor;
import com.daybreak.Items.Item;
import com.daybreak.Items.Weapon;
import com.daybreak.Screens.GameScreen;
import com.daybreak.network.management.ClientNetworkEngine;


public class Player {
	public PlayerSprite playerSprite;
	public PlayerPosition playerPosition;
	public PlayerInput playerInput;
	public PlayerMovement playerMovement;
	public PlayerStats playerStats;
	public PlayerInventory playerInventory;
	public PlayerEquipment playerEquipment;
	public PlayerSkills playerSkills;
	public PlayerInteraction playerInteraction;
	public PlayerQuestTracker playerQuestTracker;
	public int id;
	public int x,y;
	public float tileSize = 32f;
	public String username = "";
	public String password = "";
	
	
	public Player() {
		playerSprite = new PlayerSprite();
		playerPosition = new PlayerPosition();
		playerInput = new PlayerInput();
		playerMovement = new PlayerMovement();
		playerStats = new PlayerStats();
		playerInventory = new PlayerInventory();
		playerEquipment = new PlayerEquipment();
		playerSkills = new PlayerSkills();
		playerInteraction = new PlayerInteraction();
		playerQuestTracker = new PlayerQuestTracker();
	}
	
	public void update(GameScreen gs){
		playerMovement.moveDirection = playerInput.update(gs);
		playerMovement.update();
		playerInteraction.update();
		playerQuestTracker.update();
	}
	
	public void load(){
		XmlReader reader = new XmlReader();
		Element root = null;
		try {
			root = reader.parse(Gdx.files.internal("data/player.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//LOAD INVENTORY ITEMS
		Array<Element> inventoryItems = root.getChildByName("inventory").getChildrenByName("item");
		for(Element slotElement : inventoryItems){
			if(!slotElement.getAttribute("id").equals("null")){
				int itemID = Integer.parseInt(slotElement.getAttribute("id"));
				int slot = Integer.parseInt(slotElement.getAttribute("slot"));
				playerInventory.addItem(slot, GameScreen.iManager.getItem(itemID));
			}
		}
		
		//LOAD EQUIPPED ITEMS
		Array<Element> equippedItems = root.getChildByName("equipment").getChildrenByName("item");
		for(Element slotElement : equippedItems){
			String intendedSlot = slotElement.getAttribute("slot");
			if(!slotElement.getAttribute("id").equals("null")){
				int itemID = slotElement.getIntAttribute("id");
				Item item = GameScreen.iManager.getItem(itemID);
				if(item.getType().equals("weapon")){
					playerEquipment.equipWeapon((Weapon) GameScreen.iManager.getItem(itemID));
				}else if(item.getType().equals("armor")){
					Armor armorItem = (Armor) item;
					if(intendedSlot.equals(armorItem.getSlot())){
						playerEquipment.equipArmor((Armor) GameScreen.iManager.getItem(itemID));
					}
				}
			}
		}
		
		//LOAD POSITION
		Element position = root.getChildByName("position");
		int x = position.getIntAttribute("x");
		int defaultX = 8, defaultY = 12;
		int y = position.getIntAttribute("y");
		int offsetX = x-defaultX;
		int offsetY = y-defaultY;
		GameScreen.camera.translate(offsetX*32,offsetY*32);
		GameScreen.player.x = x*32;
		GameScreen.player.y = y*32;
		
	}
	

	public void setId(int myPlayerId) {
		this.id = myPlayerId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
