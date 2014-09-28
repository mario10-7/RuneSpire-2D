package com.daybreak.Nodes;

import java.util.ArrayList;
import java.util.List;


import com.badlogic.gdx.Gdx;
import com.daybreak.Screens.GameScreen;

public class NodeManager {

	Node[][] nodeList;
 	ArrayList<Node> emptyNodes;
	
	public NodeManager() {
		nodeList = new Node[GameScreen.mapManager.getWidth()][GameScreen.mapManager.getHeight()];
		emptyNodes = new ArrayList<Node>();
		loadMines();
		loadTrees();
	}

	public void loadMines(){
		for (int y = 0; y < GameScreen.mapManager.getHeight(); y++) {
			for (int x = 0; x < GameScreen.mapManager.getWidth(); x++) {
				if (GameScreen.mapManager.mineLayer.getCell(x, y) != null) {
					//Need to change later to add functionality for different types of each node and what they may contain and experience granted
					Node node = new Node();
					node.setPosition(x, y);
					node.setType("mine");
					node.setExperienceType("mining");
					node.setContains(GameScreen.iManager.getItem(Integer.valueOf((String) (GameScreen.mapManager.mineLayer.getCell(x, y).getTile().getProperties().get("item")))));
					node.setName(GameScreen.mapManager.mineLayer.getCell(x, y).getTile().getProperties().get("name").toString());
					node.setEmpty(false);
					addNode(x,y,node);
				}
			}
		}
	}
	
	public void loadTrees(){
		for (int y = 0; y < GameScreen.mapManager.getHeight(); y++) {
			for (int x = 0; x < GameScreen.mapManager.getWidth(); x++) {
				if (GameScreen.mapManager.treeLayer.getCell(x, y) != null) {
					//Need to change later to add functionality for different types of each node and what they may contain and experience granted
					Node node = new Node();
					node.setPosition(x, y);
					node.setType("tree");
					node.setExperienceType("woodcutting");
					node.setContains(GameScreen.iManager.getItem(Integer.valueOf((String) GameScreen.mapManager.treeLayer.getCell(x, y).getTile().getProperties().get("item"))));
					node.setName(GameScreen.mapManager.treeLayer.getCell(x, y).getTile().getProperties().get("name").toString());
					node.setEmpty(false);
					addNode(x,y,node);
				}
			}
		}
	}
	
	public Node getNode(int x, int y){
		if(x < 0 || x > GameScreen.mapManager.getWidth()) return null;
		if(y < 0 || y > GameScreen.mapManager.getHeight()) return null;
		return nodeList[x][y];
	}
	
	public void addNode(int x, int y, Node node){
		if(x < 0 || x > GameScreen.mapManager.getWidth()) return;
		if(y < 0 || y > GameScreen.mapManager.getHeight()) return;
		nodeList[x][y] = node;
	}
	
	public void harvestNode(int playerX, int playerY){
		Node adjacent = nodeAdjacent(playerX,playerY);
		
		if(adjacent != null){
			if(adjacent.getType().equals("tree")){harvestTree(adjacent);}
			if(adjacent.getType().equals("mine")){harvestMine(adjacent);}
		}
	}
	
	public void harvestTree(Node node){
			if(!node.isEmpty()){
				boolean hasSpace = GameScreen.player.playerInventory.addItem(node.getContains());
				if(hasSpace){
					GameScreen.log.addEntry("You manage to cut some: "+node.getContains().getName());
					GameScreen.mapManager.foregroundLayer.setCell(node.getX(), node.getY(), null);
					GameScreen.mapManager.foregroundLayer.setCell(node.getX(), node.getY()+1, null);
					GameScreen.mapManager.foregroundLayer.setCell(node.getX(), node.getY()+2, null);
					GameScreen.mapManager.foregroundLayer.setCell(node.getX(), node.getY()+3, null);
					GameScreen.mapManager.foregroundLayer.setCell(node.getX()+1, node.getY()+1, null);
					GameScreen.mapManager.foregroundLayer.setCell(node.getX()+1, node.getY()+2, null);
					GameScreen.mapManager.foregroundLayer.setCell(node.getX()+1, node.getY()+3, null);
					GameScreen.mapManager.foregroundLayer.setCell(node.getX()-1, node.getY()+1, null);
					GameScreen.mapManager.foregroundLayer.setCell(node.getX()-1, node.getY()+2, null);
					GameScreen.mapManager.foregroundLayer.setCell(node.getX()+2, node.getY()+1, null);
					GameScreen.mapManager.foregroundLayer.setCell(node.getX()+2, node.getY()+2, null);
					GameScreen.player.playerSkills.woodcutting.addExperience(node.getContains().getExperience());
					GameScreen.log.addEntry("exp : " + node.getContains().getExperience());
					node.setEmpty(true);
					emptyNodes.add(node);
				}
			}else{
				GameScreen.log.addEntry("There is nothing to cut.");
			}
	}
	
	public void harvestMine(Node node){
		if(!node.isEmpty()){
			boolean hasSpace = GameScreen.player.playerInventory.addItem(node.getContains());
			if(hasSpace){
				GameScreen.log.addEntry("You manage to mine some: "+node.getContains().getName());
				GameScreen.player.playerSkills.mining.addExperience(node.getContains().getExperience());
				node.setEmpty(true);
				emptyNodes.add(node);
			}
		}else{
			GameScreen.log.addEntry("There is nothing to mine.");
		}
	}

	
	public Node nodeAdjacent(int playerX, int playerY){
		if(playerX>0 && playerY > 0){
			if(nodeList[playerX][playerY-1]!=null && nodeList[playerX][playerY-1].contains!=null) return getNode(playerX,playerY-1);
			if(nodeList[playerX][playerY+1]!=null && nodeList[playerX][playerY+1].contains!=null) return getNode(playerX,playerY+1);
			if(nodeList[playerX-1][playerY]!=null && nodeList[playerX-1][playerY].contains!=null) return getNode(playerX-1,playerY);
			if(nodeList[playerX+1][playerY]!=null && nodeList[playerX+1][playerY].contains!=null) return getNode(playerX+1,playerY);
		}
		return null;
	}
	
	
	//For regenerating nodes
	//Temporary time of 20s
	//Need to add tree top
	public void update(){
		ArrayList<Node> notEmpty = new ArrayList<Node>();
		for(Node node : emptyNodes){
			node.setEmptyTime(node.getEmptyTime()+Gdx.graphics.getDeltaTime());
			if(node.getEmptyTime()>=20){
				System.out.println("true");
				node.setEmptyTime(0);
				nodeList[node.getX()][node.getY()].setEmpty(false);
				notEmpty.add(node);
				addNode(node.getX(),node.getY(),node);
			}
		}
		for(Node node : notEmpty){
			emptyNodes.remove(node);
			
		}
	}
	
	
	
}
