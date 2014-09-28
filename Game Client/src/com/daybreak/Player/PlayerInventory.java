package com.daybreak.Player;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.daybreak.Items.Item;
import com.daybreak.Screens.GameScreen;

public class PlayerInventory {

	Item[][] inventoryList;
	OrthographicCamera camera;
	SpriteBatch batch;
	int x = 580, y = 310;
	int selectedX = -1, selectedY = -1;
	int moveX, moveY;
	int draggedY, draggedX;
	boolean dragged = false;

	public PlayerInventory() {
		inventoryList = new Item[4][7];
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
	}

	public void render() {
		camera.update();

		batch.begin();

		//Normal rendering
		if(!dragged){
			for (int ny = 0; ny < 7; ny++) {
				for (int nx = 0; nx < 4; nx++) {
					if (inventoryList[nx][ny] != null) {
						batch.draw(inventoryList[nx][ny].getSprite(),(int) (x + (nx * 50)),(int) (y - (ny * 43)));
					}
				}
			}
		//Rendering for dragging
		}else{
			for (int ny = 0; ny < 7; ny++) {
				for (int nx = 0; nx < 4; nx++) {
					if (inventoryList[nx][ny] != null) {
						if(nx==selectedX && ny==selectedY){
							batch.draw(inventoryList[nx][ny].getSprite(),draggedX-(50/2),(int)(draggedY-(43/2)));
						}else{
							batch.draw(inventoryList[nx][ny].getSprite(),(int)(x + (nx * 50)),(int)( y - (ny * 43)));
						}
					}
				}
			}
		}
		batch.end();
	}

	public boolean addItem(Item item) {
		for (int ny = 0; ny < 7; ny++) {
			for (int nx = 0; nx < 4; nx++) {
				if (inventoryList[nx][ny] == null) {
					inventoryList[nx][ny] = GameScreen.iManager.getItem(item.getID());
					return true;
				}else{
					//If full
					if(nx==3 && ny==6){
						GameScreen.log.addEntry("Inventory is full!");
						return false;
					}
				}
			}
		}
		return false;
	}
	
	public void addItem(int index, Item item){
		int x = index%4;
		int y = index/4;
		
		if(inventoryList[x][y] == null){
			inventoryList[x][y] = GameScreen.iManager.getItem(item.getID());
		}
	}
	
	public void removeItem(int itemID){
		for( int ny = 0; ny < 7; ny++){
			for(int nx = 0; nx < 4; nx++){
				if(inventoryList[nx][ny]==GameScreen.iManager.getItem(itemID)){
					inventoryList[nx][ny]=null;
					return;
				}
			}
		}
	}
	
	public void removeItem(int itemID,int amount){
		int removed = 0;
		for( int ny = 0; ny < 7; ny++){
			for(int nx = 0; nx < 4; nx++){
				if(inventoryList[nx][ny]==GameScreen.iManager.getItem(itemID)){
					inventoryList[nx][ny]=null;
					removed++;
					if(removed==amount){
						return;
					}
				}
			}
		}
	}
	
	public boolean contains(int ID, int amount){
		int counter = 0;
		for( int ny = 0; ny < 7; ny++){
			for(int nx = 0; nx < 4; nx++){
				if(inventoryList[nx][ny] == GameScreen.iManager.getItem(ID)){
					counter++;
				}
			}
		}
		if(counter >= amount) return true;
		return false;
	}

	public void clicked(int xClicked, int yClicked, int button) {
		int nx = (xClicked - x) / 50;
		int ny = (int) ((y - yClicked + 43) / 43);

		if (nx >= 0 && nx < 4 && ny >= 0 && ny < 7 && yClicked < 333) {
			if (inventoryList[nx][ny] != null) {
				// select item by inventory position
				selectedX = nx;
				selectedY = ny;
			}
		}
	}

	public void mouseUp(int xClicked, int yClicked, int button) {
		// Use item
		if (selectedX >= 0 && selectedX < 4 && selectedY >= 0 && selectedY < 7&& yClicked < 333) {
			if (!dragged) {
				if (inventoryList[selectedX][selectedY] != null) {
					inventoryList[selectedX][selectedY].use();
					if(inventoryList[selectedX][selectedY].isClickable()){
						inventoryList[selectedX][selectedY] = null;
					}
				}
				// Move or swap item
			} else {
				moveX = (xClicked - x) / 50;
				moveY = (int)((y - yClicked + 43)) / 43;
				
				if(moveX >= 0 && moveX < 4 && moveY >= 0 && moveY < 7&& yClicked < 333){
					if (inventoryList[moveX][moveY] == null) {
						inventoryList[moveX][moveY] = inventoryList[selectedX][selectedY];
						inventoryList[selectedX][selectedY] = null;
					} else {
						Item temp = inventoryList[selectedX][selectedY];

						inventoryList[selectedX][selectedY] = inventoryList[moveX][moveY];
						inventoryList[moveX][moveY] = temp;
					}
				}
			}
		}
		dragged = false;
		selectedX = selectedY = -1;
		moveX = moveY = -1;
		draggedX = draggedY = -1;
	}

	public void mouseDragged(int mouseX, int mouseY) {
		dragged = true;
		draggedX = mouseX;
		draggedY = mouseY;
	}
}
