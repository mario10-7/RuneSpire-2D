package com.daybreak.Player;

import com.badlogic.gdx.math.Vector2;
import com.daybreak.Screens.GameScreen;

public class PlayerPosition extends Vector2 {
	
	private static final long serialVersionUID = 1L;
	
	public Vector2 oldPosition;
	
	public PlayerPosition() {
		this.x = 1*32;
		this.y = 1*32;
	
		oldPosition = new Vector2();
		oldPosition.set(this.x,this.y);
	}

	

}
