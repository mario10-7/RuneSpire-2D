package com.daybreak.UI;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.daybreak.Screens.GameScreen;

public class TabBar {

	Texture inventoryTab,equipTab,skillTab,questTab,prayerTab,magicTab,combatTab,friendsTab,ignoreTab,emoteTab,settingsTab,logoutTab,musicTab;
	OrthographicCamera camera;
	SpriteBatch batch;
	int x = 800-258+48-35, y = 365;
	int tabOffset = 35;
	int state = 0;
	
	//Define states
	final int COMBAT = 0;
	final int SKILL = 1;
	final int QUEST = 2;
	final int INVENTORY = 3;
	final int EQUIP = 4;
	final int PRAYER = 5;
	final int MAGIC = 6;
	final int CLANCHAT = 7;
	final int FRIENDS = 8;
	final int IGNORE = 9;
	final int LOGOUT = 10;
	final int SETTINGS = 11;
	final int EMOTE = 12;
	final int MUSIC = 13;
	
	public TabBar() {
		inventoryTab = new Texture(Gdx.files.internal("data/inventoryTab.png"));
		combatTab = new Texture(Gdx.files.internal("data/combatTab.png"));
		magicTab = new Texture(Gdx.files.internal("data/magicTab.png"));
		prayerTab = new Texture(Gdx.files.internal("data/prayerTab.png"));
		equipTab = new Texture(Gdx.files.internal("data/equipTab.png"));
		skillTab = new Texture(Gdx.files.internal("data/skillTab.png"));
		questTab = new Texture(Gdx.files.internal("data/questTab.png"));
		friendsTab = new Texture(Gdx.files.internal("data/friendsTab.png"));
		ignoreTab = new Texture(Gdx.files.internal("data/ignoreTab.png"));
		logoutTab = new Texture(Gdx.files.internal("data/logoutTab.png"));
		settingsTab = new Texture(Gdx.files.internal("data/settingsTab.png"));
		emoteTab = new Texture(Gdx.files.internal("data/emoteTab.png"));
		musicTab = new Texture(Gdx.files.internal("data/musicTab.png"));
		
		
		camera = new OrthographicCamera();
		batch = new SpriteBatch();
		
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
	}
	
	public void render(){
		camera.update();
		
		batch.begin();
		//Render icons
		batch.draw(combatTab,x,y);
		batch.draw(skillTab,x+(tabOffset*1),y);
		batch.draw(questTab,x+(tabOffset*2),y);
		batch.draw(inventoryTab,x+(tabOffset*3)-3,y);
		batch.draw(equipTab,x+(tabOffset*4)-3,y);
		batch.draw(prayerTab,x+(tabOffset*5)-2,y);
		batch.draw(magicTab,x+(tabOffset*6),y);
		//bottom icons
		batch.draw(friendsTab,x+(tabOffset*1)+1,y-355);
		batch.draw(ignoreTab,x+(tabOffset*2)+1,y-355);
		batch.draw(logoutTab,x+(tabOffset*3)+1,y-355);
		batch.draw(settingsTab,x+(tabOffset*4),y-355);
		batch.draw(emoteTab,x+(tabOffset*5)+1,y-355);
		batch.draw(musicTab,x+(tabOffset*6),y-355);
		
		batch.end();
		
		batch.begin();
		
		switch(state){
		case INVENTORY:
			GameScreen.player.playerInventory.render();
			break;
		case EQUIP:
			GameScreen.player.playerEquipment.render();
			break;
		case SKILL:
			GameScreen.player.playerSkills.render();
			break;
		case QUEST:
			GameScreen.uiManager.questUI.render();
			break;
		}
		
		batch.end();
	}
	
	public void clicked(int screenX, int screenY, int button){
		int nx = (screenX-x)/35;
		int ny = (screenY-y)/35;
		
		if(ny == 0 && screenY > y){
			switch(nx){
			case INVENTORY:
				state = INVENTORY;
				GameScreen.uiManager.questUI.leave();
				break;
			case EQUIP:
				state = EQUIP;
				GameScreen.uiManager.questUI.leave();
				break;
			case SKILL:
				state = SKILL;
				GameScreen.uiManager.questUI.leave();
				break;
			case QUEST:
				state = QUEST;
				GameScreen.uiManager.questUI.leave();
				GameScreen.uiManager.questUI.enter();
			}
		}
		
		switch(state){
		case INVENTORY:
			GameScreen.player.playerInventory.clicked(screenX,screenY,button);
			break;
		case EQUIP:
			GameScreen.player.playerEquipment.clicked(screenX, screenY, button);
			break;
		case SKILL:
			GameScreen.player.playerSkills.clicked(screenX, screenY, button);
		}
	}
	
	public void mouseUp(int xClicked, int yClicked, int button){
		switch(state){
		case INVENTORY:
			GameScreen.player.playerInventory.mouseUp(xClicked, yClicked, button);
			break;
		}
	}
	
	public void mouseDragged(int mouseX, int mouseY){
		switch(state){
		case INVENTORY:
			GameScreen.player.playerInventory.mouseDragged(mouseX, mouseY);
			break;
		}
	}
}
