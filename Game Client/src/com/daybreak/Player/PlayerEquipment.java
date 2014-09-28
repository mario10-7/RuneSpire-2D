package com.daybreak.Player;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.daybreak.Items.Armor;
import com.daybreak.Items.Item;
import com.daybreak.Items.Weapon;
import com.daybreak.Screens.GameScreen;

public class PlayerEquipment {

	SpriteBatch batch;
	OrthographicCamera camera;
	public static Texture backGround;
	BitmapFont font;
	
	int y = (int) 80, x = 610;
	
	//Slot definitions
	static final int HELMET = 0;
	static final int CHEST = 1;
	static final int WEAPON = 2;
	static final int SHIELD = 3;
	static final int LEGS = 4;
	
	
	
	Item[] equipped;
	
	public PlayerEquipment() {
		equipped = new Item[5];
		font = new BitmapFont();
		backGround = new Texture(Gdx.files.internal("data/EquippedOutline.png"));
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());

		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
	}
	
	public void equipWeapon(Weapon weapon){
		if(equipped[WEAPON]!=null){
			unEquipWeapon();
		}
		GameScreen.player.playerStats.addDamage(weapon.getDamage());
		equipped[WEAPON] = weapon;
	}
	
	public void equipArmor(Armor armor){
		int type = 0;
		if(armor.getSlot().equals("head")){type = HELMET;}
		if(armor.getSlot().equals("chest")){type = CHEST;}
		if(armor.getSlot().equals("legs")){type = LEGS;}
		if(armor.getSlot().equals("shield")){type = SHIELD;}
		
		if(equipped[type]!=null){
			unEquipArmor(type);
		}
		GameScreen.player.playerStats.addArmor(armor.getArmorValue());
		equipped[type] = armor;
	}
	
	public void unEquipArmor(int type) {
		if(equipped[type] != null){
			Armor armor = (Armor) GameScreen.iManager.getItem(equipped[type].getID());
			GameScreen.player.playerStats.addArmor(-armor.getArmorValue());
			GameScreen.player.playerInventory.addItem(equipped[type]);
			equipped[type] = null;
		}
		
	}

	public void render(){
		camera.update();
		
		batch.begin();
			batch.draw(backGround,x,y);
			
			if(equipped[HELMET] != null){
				batch.draw(equipped[HELMET].getSprite(),x+(int)(97/2),y+(int)(361/2));
			}
			if(equipped[CHEST] != null){
				batch.draw(equipped[CHEST].getSprite(),x+(int)(97/2),y+(int)(202/2));
			}
			if(equipped[WEAPON] != null){
				batch.draw(equipped[WEAPON].getSprite(),x+(int)(10/2),y+(int)(202/2));
			}
			if(equipped[SHIELD] != null){
				batch.draw(equipped[SHIELD].getSprite(),x+(int)(185/2),y+(int)(202/2));
			}
			if(equipped[LEGS] != null){
				batch.draw(equipped[LEGS].getSprite(),x+(int)(97/2),y+(int)(43/2));
			}
			font.draw(batch, "here", x+50, y+(int)(110));
		batch.end();
	}
	
	public void unEquipWeapon(){
		if(equipped[WEAPON] != null){
			Weapon weapon = (Weapon) GameScreen.iManager.getItem(equipped[WEAPON].getID());
			GameScreen.player.playerStats.addDamage(-weapon.getDamage());
			GameScreen.player.playerInventory.addItem(equipped[WEAPON]);
			equipped[WEAPON] = null;
		}
	}
	
	public void clicked(int clickX, int clickY, int button){
		//Check for head, chest and legs in the x-axis
		if(clickX > x+(int)(50) && clickX < x+(int)(50+30)){
			if(clickY>y+(int)(361/2) && clickY < y+(int)((361/2)+30)){
				unEquipArmor(HELMET);
			}
			if(clickY>y+(int)(80) && clickY < y+(int)(202+65/2)){
				unEquipArmor(CHEST);
			}
			if(clickY>y+(int)(43/2) && clickY < y+(43+65/2)){
				unEquipArmor(LEGS);
			}
			
		}
		if(clickX > x+(int)(10/2) && clickX < x+(int)(10+65/2)){
			if(clickY > y+(int)(202/2) && clickY < y+(int)(202+65/2)){
				unEquipWeapon();
			}
		}
		if(clickX > x+(int)(185/2) && clickX < x+(int)(185+65/2)){
			if(clickY > y+(int)(202/2) && clickY < y+(int)(202+65/2)){
				unEquipArmor(SHIELD);
			}
		}
	}
	
	
}
