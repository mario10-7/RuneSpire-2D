package com.daybreak.Player;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.daybreak.Player.Skills.MiningSkill;
import com.daybreak.Player.Skills.Skill;
import com.daybreak.Player.Skills.WoodcuttingSkill;

public class PlayerSkills {

	Texture miningTab,woodcuttingTab;
	SpriteBatch batch;
	OrthographicCamera camera;
	
	public Skill mining,woodcutting;
	
	int x = 1294/2, y = (int) (501/1.5);
	int width = 130/2, height = (int) (100/1.5);
	
	
	
	public PlayerSkills() {
		mining = new MiningSkill();
		woodcutting = new WoodcuttingSkill();
		
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
		
		
		miningTab = new Texture(Gdx.files.internal("data/miningTab.png"));
		woodcuttingTab = new Texture(Gdx.files.internal("data/woodcuttingTab.png"));
	}
	
	public void render(){
		camera.update();
		
		batch.begin();
			batch.draw(miningTab,x,y-height);
			batch.draw(woodcuttingTab,x+width,y-height);
		batch.end();
	}

	public void clicked(int clickX, int clickY, int button) {
		
		
	}
	
	
}
