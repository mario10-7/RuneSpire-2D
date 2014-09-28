package com.daybreak.UI;

import java.util.HashMap;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.daybreak.Screens.GameScreen;

public class UIManager {

	public Stage stage;
	Texture bottomPanel,sidePanel;
	Image bottomPanelI,sidePanelI;
	TextureRegionDrawable chatPanel;
	Table chatTable;
	Table mouseOverTable;
	QuestTrackerUI questUI;
	
	BitmapFont font;
	Skin skin;
	

	
	public UIManager(){

		//create UI things
		stage = new Stage();
		skin = new Skin();
		
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.RED);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		
		font = new BitmapFont();
		skin.add("default", font);
		
		
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.over = skin.newDrawable("white", Color.RED);
		textButtonStyle.font = font;
		textButtonStyle.fontColor = Color.BLACK;
		skin.add("default", textButtonStyle);
		
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = font;
		labelStyle.fontColor = Color.BLACK;
		skin.add("default", labelStyle);
		
	
		chatTable = new Table();
		Table sideTable = new Table();
		chatTable.setSize(542, 600);
		chatTable.setPosition(0, 0);
		sideTable.setSize(258, 600);
		sideTable.setPosition(800-258, 0);
		stage.addActor(chatTable);
		stage.addActor(sideTable);
		
		bottomPanel = new Texture("data/chatarea.png");
		chatPanel = new TextureRegionDrawable(new TextureRegion(bottomPanel));
		
		sidePanel = new Texture("data/SidePanel2.png");
		sidePanelI = new Image(sidePanel);
		chatTable.setBackground(chatPanel);
		sideTable.add(sidePanelI);
		
		mouseOverTable = new Table();
		mouseOverTable.setPosition(0,Gdx.graphics.getHeight()-20);
		mouseOverTable.setSize(50,50);
		stage.addActor(mouseOverTable);
		
		questUI = new QuestTrackerUI(stage);
		
	}
	
	public void update(){
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
	}
	
	public void dialogue(String text,Array<Element> optionElements, int npcID){
		//Get option text
		String[] options = new String[optionElements.size];
		final HashMap<String,String> optionMap = new HashMap<String,String>();
		final HashMap<String,Integer> questMap = new HashMap<String,Integer>();
		final HashMap<String,Integer> questReturnMap = new HashMap<String,Integer>();
		
		for(int i = 0; i < optionElements.size;i++){
			options[i]=optionElements.get(i).getText();
			optionMap.put(options[i], optionElements.get(i).getAttribute("action"));
			//Put quests
			try{
			if(optionElements.get(i).getAttribute("quest")!=null){
				int questID = optionElements.get(i).getIntAttribute("quest");
				questMap.put(options[i],questID);
			}
			}catch(Exception e){}
			//put Quest returns
			try{
			if(optionElements.get(i).getAttribute("returnquest")!=null){
				int questID = optionElements.get(i).getIntAttribute("returnquest");
				questReturnMap.put(options[i],questID);
			}
			}catch(Exception e){}
			
		}
		
		String npcNameText = GameScreen.npcManager.getNPC(npcID).getName()+":";
		Label npcName = new Label(npcNameText,skin);
		npcName.setFontScale(1);
		chatTable.add(npcName);
		chatTable.row();
		
		Label textLabel = new Label(text,skin);
		textLabel.setFontScale(1);
		chatTable.add(textLabel);

		TextButton[] optionButtons = new TextButton[options.length];
		
		for(int i = 0; i < options.length; i++){
			final String optionText = options[i];
			optionButtons[i] = new TextButton(options[i],skin);
			optionButtons[i].addListener(new InputListener() {
				public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
					GameScreen.player.playerInteraction.updateInteraction(optionMap.get(optionText));
					if(questMap.get(optionText)!=null){
						GameScreen.player.playerQuestTracker.addQuest(questMap.get(optionText));
					}
					if(questReturnMap.get(optionText)!=null){
						if(GameScreen.player.playerQuestTracker.getQuest(questReturnMap.get(optionText)) != null){
							GameScreen.player.playerQuestTracker.getQuest(questReturnMap.get(optionText)).getReturnObjective().complete();
						}
					}
				return false;
				}});
			chatTable.row();
			chatTable.add(optionButtons[i]);
		}
	}
	
	public void clearDialogue(){
		chatTable.clear();
	}
	
	public void mouseOverDisplay(String display){
		Label mouseOver = new Label(display, skin);
		//mouseOver.setPosition(0, Gdx.graphics.getHeight()-20);
		mouseOverTable.addActor(mouseOver);
	}
	
	public void clearMouseOverDisplay(){
		mouseOverTable.clear();
	}
	
}
