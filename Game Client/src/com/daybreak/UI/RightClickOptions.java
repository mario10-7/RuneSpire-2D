package com.daybreak.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.daybreak.Daybreak;
import com.daybreak.Player.Player;

public class RightClickOptions {	

		private Texture menuT;
		public Sprite menu;
		public static boolean popup = false;
		BitmapFont font;
		static int clickX;
		static int clickY;
		
		static String[] options = {"Click here","Cancel","bla","bla"};
	
		public RightClickOptions(Daybreak game) {
			menuT = game.assets.get("data/contextmenu.png", Texture.class);
			menu = new Sprite(menuT);
			font = new BitmapFont();
			font.setScale(0.9f,0.9f);
		}

		public void update(SpriteBatch hudBatch, Player player) {
			if (popup) {
				menu.setSize(80, options.length*15+5);
				menu.setPosition(clickX,600-clickY-options.length*15-5);
				menu.draw(hudBatch);
				for (int i = 0; i < options.length; i++) {
				font.draw(hudBatch, options[i],clickX+3,600-clickY-15*i-5);
				}
			}
		}
				public static void clicked(int screenX, int screenY, int button) {
					if (button == Buttons.RIGHT) {
			        	popup = true;
			        	clickX = screenX;
			        	clickY = screenY;
			        }
					
				}

				public static void mousemoved(int screenX,int screenY) {
					if (screenX < clickX-10 || screenX > clickX + 90 || screenY > clickY+(options.length*15)+5+10 || screenY < clickY-10) {
			    		popup = false;
			    	}
				}
		
	}
