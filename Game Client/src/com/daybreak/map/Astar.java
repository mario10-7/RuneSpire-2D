package com.daybreak.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.daybreak.Nodes.Node;
import com.daybreak.Nodes.NodeAstar;
import com.daybreak.Screens.GameScreen;
import com.daybreak.Util.Vector2i;


public class Astar {
	static TiledMapTileLayer at = (TiledMapTileLayer) GameScreen.mapManager.backLayer;
	static TiledMapTileLayer at2 = (TiledMapTileLayer) GameScreen.mapManager.collisionLayer;
	
	private static Comparator<NodeAstar> nodeSorter = new Comparator<NodeAstar>() {
		public int compare(NodeAstar n0, NodeAstar n1) {
			if (n1.fCost < n0.fCost)
				return 1;
			if (n1.fCost > n0.fCost)
				return -1;
			return 0;
		}
	};
	
	public static List<NodeAstar> findPath(Vector2i start, Vector2i destination) {
		List<NodeAstar> openList = new ArrayList<NodeAstar>();
		List<NodeAstar> closedList = new ArrayList<NodeAstar>();
		NodeAstar current = new NodeAstar(start, null, 0, getDistance(start, destination));
		openList.add(current);
		while (openList.size() > 0) {
			Collections.sort(openList, nodeSorter);
			current = openList.get(0);
			if (current.tile.equals(destination)) {
				List<NodeAstar> path = new ArrayList<NodeAstar>();
				while (current.parent != null) {
					path.add(current);
					current = current.parent;
				}
				openList.clear();
				closedList.clear();
				return path;
			}
			openList.remove(current);
			closedList.add(current);
			for (int i = 0; i < 9; i++) {
				if (i == 4)
					continue;
				float x = current.tile.getX();
				float y = current.tile.getY();
				float xi = (i % 3) - 1;
				float yi = (i / 3) - 1;
				
				if (//!at2.getCell((int)(x+xi),(int)(y+yi)).getTile().getProperties().get("solid").equals(null) && 
						at.getCell((int)(x+xi),(int)( y+yi)).getTile().equals(null))
						continue;
					
				Vector2i a = new Vector2i(x + xi, y + yi);		
					double gCost = current.gCost + (getDistance(current.tile, a) == 1 ? 1 : 0.95);
				double hCost = getDistance(a, destination);
				NodeAstar node = new NodeAstar(a, current, gCost, hCost);
				if (vecInList(closedList, a) && gCost >= node.gCost)
					continue;
				if (!vecInList(openList, a) || gCost < node.gCost)
					openList.add(node);
				}
			
		}
		closedList.clear();
		return null;
	}

	private static float getDistance(Vector2i start, Vector2i goal) {
		float dx = start.getX() - goal.getX();
		float dy = start.getY() - goal.getY();
		return (float) Math.sqrt(dx * dx + dy * dy);
	}

	private static boolean vecInList(List<NodeAstar> closedList, Vector2i a) {
		for (NodeAstar n : closedList) {
			if (n.tile.equals(a))
				return true;
		}
		return false;
	}
}
