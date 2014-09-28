package com.daybreak.Player;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.daybreak.Screens.GameScreen;
import com.daybreak.UI.RightClickOptions;

public class PlayerInput implements InputProcessor{

	PlayerMovement.direction dir = null;
	boolean keyPressed = false;
	GameScreen gs;
	
	int clickX,clickY;
	
	@Override
	public boolean keyDown(int keycode) {
		switch(keycode){
		case Keys.UP:
			dir = PlayerMovement.direction.UP;
			break;
		case Keys.DOWN:
			dir = PlayerMovement.direction.DOWN;
			break;
		case Keys.LEFT:
			dir = PlayerMovement.direction.LEFT;
			break;
		case Keys.RIGHT:
			dir = PlayerMovement.direction.RIGHT;
			break;
		case Keys.CONTROL_LEFT:
			GameScreen.log.getCommand();
			break;
		case Keys.SPACE:
			GameScreen.nodeManager.harvestNode((int)GameScreen.player.playerPosition.x/32, (int)GameScreen.player.playerPosition.y/32);
			GameScreen.npcManager.interact((int)GameScreen.player.playerPosition.x/32, (int)GameScreen.player.playerPosition.y/32);
			break;
		default:
			dir = null;
			break;
		}
		keyPressed = true;
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button == 0 && gs != null && gs.tabBar != null) {
		gs.tabBar.clicked(screenX, Gdx.graphics.getHeight()-screenY ,button);
		}
		RightClickOptions.clicked(screenX,screenY,button);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (gs != null && gs.tabBar != null) {
		gs.tabBar.mouseUp(screenX, Gdx.graphics.getHeight()-screenY,button);
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (gs != null && gs.tabBar != null) {
		gs.tabBar.mouseDragged(screenX, Gdx.graphics.getHeight()-screenY);
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		if(screenX < 1248/2 && screenY < 672/2){
			//800 is initial cam pos
			int x = (int) ((screenX+GameScreen.camera.position.x-(800/2))/32);
			//222 is initial cam pos, 228 is UI offset
			int y = (int) ((-screenY+GameScreen.camera.position.y+(222+228/2))/32);
			if(GameScreen.nodeManager.getNode(x, y)!=null){
				GameScreen.uiManager.mouseOverDisplay(GameScreen.nodeManager.getNode(x, y).getName());
			}else{
				GameScreen.uiManager.clearMouseOverDisplay();
			}
			RightClickOptions.mousemoved(screenX,screenY);
		}
		
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
	public PlayerMovement.direction update(GameScreen gs){
		this.gs = gs;
		if(keyPressed == false){
			dir = null;
		}
		keyPressed = false;
		return dir;
		
	}
	
	public int getClickX(){
		return clickX;
	}
	public int getClickY(){
		return clickY;
	}
}
