package com.daybreak.NPC;

import java.io.IOException;
import java.util.ArrayList;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.daybreak.Screens.GameScreen;

public class NPCManager {

	public ArrayList<NPC> NPCs;
	
	public NPCManager() {
		NPCs = new ArrayList<NPC>();
		loadNPCs();
	}
	
	public void interact(int playerX, int playerY){
		NPC adjacent = getAdjacent(playerX,playerY);
		if(adjacent!=null){
			String dialogue = "start";
			Element dialogueElement = loadDialogue(dialogue,adjacent.getID());
			GameScreen.player.playerInteraction.newInteraction(dialogueElement, adjacent.getID());
		}
	}
	
	public NPC getNPC(int ID){
		return NPCs.get(ID);
	}
	
	public NPC getAdjacent(int playerX, int playerY){
		for(NPC npc : NPCs){
			if(playerX == npc.npcPosition.getX()/32 && playerY == npc.npcPosition.getY()/32-1) return npc;
			if(playerX == npc.npcPosition.getX()/32 && playerY == npc.npcPosition.getY()/32+1) return npc;
			if(playerX == npc.npcPosition.getX()/32-1 && playerY == npc.npcPosition.getY()/32) return npc;
			if(playerX == npc.npcPosition.getX()/32+1 && playerY == npc.npcPosition.getY()/32) return npc;
		}
		return null;
	}
	
	
	public void buildNPC(Element npcElement){
		//Attributes
		int ID = Integer.parseInt(npcElement.getAttribute("id"));
		String name = npcElement.getAttribute("name");
		NPC npc = new NPC(ID,name);
		
		//Components
		Element components = npcElement.getChildByName("components");
		if(components.getChildByName("position")!=null){
			npc.addPosition(buildPosition(components.getChildByName("position")));
		}
		if(components.getChildByName("sprite")!=null){
			npc.addSprite(buildSprite(components.getChildByName("sprite")));
		}
		NPCs.add(ID,npc);
	}
	
	public NPCPosition buildPosition(Element positionElement){
		int x = Integer.parseInt(positionElement.getAttribute("x"));
		int y = Integer.parseInt(positionElement.getAttribute("y"));
		NPCPosition position = new NPCPosition(x*32,y*32);
		return position;
	}
	
	public NPCSprite buildSprite(Element spriteElement){
		String name = spriteElement.getAttribute("name");
		NPCSprite sprite = new NPCSprite(name);
		return sprite;
	}
	
	public void loadNPCs(){
		XmlReader reader = new XmlReader();
		Element root = null;
		try {
			root = reader.parse(Gdx.files.internal("data/NPCs/npcs.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Array<Element> npcs = root.getChildrenByName("npc");
		//Loop through each "npcs"
		for(Element child : npcs){
			buildNPC(child);
		}
	}
	
	public Element loadDialogue(String dialogue,int ID){
		XmlReader reader = new XmlReader();
		Element root = null;
		try {
			root = reader.parse(Gdx.files.internal("data/NPCs/npcs.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Array<Element> npcs = root.getChildrenByName("npc");
		//Loop through each "npcs"
		for(Element child : npcs){
			if(child.getAttribute("id").equals(Integer.toString(ID))){
				Array<Element> dialogues = child.getChildrenByName("dialogue");
				//look through dialogues
				for(Element d : dialogues){
					if(d!=null){
						if(d.getAttribute("id").equals(dialogue)){
							return d;
						}
					}
				}
			}
		}
		return null;
	}
}
