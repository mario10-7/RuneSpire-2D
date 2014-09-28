package com.daybreak.Player;


import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.daybreak.Screens.GameScreen;


public class PlayerInteraction {
	
	boolean interacting = false;
	Element dialogue;
	float playerX,playerY;
	int interactingWith;
	
	
	public void update(){
		if(interacting){
			//Check for breaks
			if(playerX!=GameScreen.player.playerPosition.x || playerY!=GameScreen.player.playerPosition.y){
				breakInteraction();
			}
		}
	}
	
	public void newInteraction(Element dialogueE,int ID){
		breakInteraction();
		
		interacting = true;
		dialogue = dialogueE;
		interactingWith = ID;
		playerX = GameScreen.player.playerPosition.x;
		playerY = GameScreen.player.playerPosition.y;
		
		
		//Find text
		String text = dialogue.getChildByName("text").getText();
		// Find options
		Array<Element> optionElements = dialogue.getChildrenByName("option");

		GameScreen.uiManager.dialogue(text, optionElements,interactingWith);
	}
	
	public void updateInteraction(String option){
		if(option.equals("exit")){
			breakInteraction();
			return;
		}
		
		
		dialogue = GameScreen.npcManager.loadDialogue(option, interactingWith);
		
		GameScreen.uiManager.clearDialogue();
		
		//Find text
		String text = dialogue.getChildByName("text").getText();
		// Find options
		Array<Element> optionElements = dialogue.getChildrenByName("option");
		
		GameScreen.uiManager.dialogue(text, optionElements,interactingWith);
	}
	
	public void breakInteraction(){
		interacting = false;
		dialogue = null;
		GameScreen.uiManager.clearDialogue();
	}
	
	public String getDialogue(){
		return dialogue.getAttribute("id");
	}
}
