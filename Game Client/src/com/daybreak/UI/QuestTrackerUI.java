package com.daybreak.UI;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.daybreak.Screens.GameScreen;

public class QuestTrackerUI {

	Stage stage;
	Skin skin;
	int x = 1249+46/2;
	int y = (int) (46/1.5);
	Table questTable;
	TextButton[] questButtons;
	TextButtonStyle textButtonStyle;
	BitmapFont font;

	public QuestTrackerUI(Stage stage) {
		//create UI things
		this.stage = stage;
		skin = new Skin();
		
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		
		font = new BitmapFont();
		skin.add("default", font);
		
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.over = skin.newDrawable("white", Color.RED);
		textButtonStyle.font = font;
		skin.add("default", textButtonStyle);


		questTable = new Table();
		questTable.setPosition(x+(200/2), y+(int)(200/1.5));
		questTable.setSize(260/2, (int)(455/1.5));
		questTable.align(Align.top);
		
		stage.addActor(questTable);

		questButtons = new TextButton[GameScreen.questManager.quests.size()];
	}

	public void enter() {
		if (!GameScreen.player.playerQuestTracker.quests.isEmpty()) {
			for (int i = 0; i < GameScreen.questManager.quests.size(); i++) {
				if(GameScreen.player.playerQuestTracker.quests.get(i).isCompleted()){
					setFontCompleted();
				}else if(GameScreen.player.playerQuestTracker.quests.get(i).isActive()){
					setFontActive();
				}
				
				questButtons[i] = new TextButton(GameScreen.player.playerQuestTracker.quests.get(i).getName(), skin);
				questTable.row();
				questTable.add(questButtons[i]);
			}
		}
	}

	public void leave() {
		questTable.clear();
	}
	
	public void setFontCompleted(){
		textButtonStyle.fontColor = Color.GREEN;
		skin.add("default", textButtonStyle);
	}
	
	public void setFontActive(){
		textButtonStyle.fontColor = Color.YELLOW;
		skin.add("default", textButtonStyle);
	}
	

	public void render() {
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));

	}
}
