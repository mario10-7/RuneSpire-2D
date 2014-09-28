package com.daybreak.NPC;

import java.util.List;

import com.daybreak.Nodes.NodeAstar;
import com.daybreak.Screens.GameScreen;
import com.daybreak.Util.Vector2i;
import com.daybreak.map.Astar;

public class NPCMovement {

	private int time = 0;

	private List<NodeAstar> path;

	public direction moveDirection = null;

	public static enum direction {
		LEFT, DOWN, UP, RIGHT;
	}

	public void update() {

		time++;

//		Vector2i start = new Vector2i(
//				GameScreen.npcManager.NPCs.get(0).npcPosition.x/32,
//				GameScreen.npcManager.NPCs.get(0).npcPosition.y/32);
//		Vector2i destination = new Vector2i(
//				(int) GameScreen.player.playerPosition.x/32,
//				(int) GameScreen.player.playerPosition.y/32);
		//if (time % 3 == 0)
			//path = Astar.findPath(start, destination);
//		if (path != null) {
//			if (path.size() > 0) {
//
//				Vector2i vec = path.get(path.size() - 1).tile;
//				if (GameScreen.npcManager.NPCs.get(0).npcPosition.x/32 < vec
//						.getX()) {
//				//	GameScreen.npcManager.NPCs.get(0).npcPosition.x += 2f;
//				}
//				if (GameScreen.npcManager.NPCs.get(0).npcPosition.x/32 > vec
//						.getX()) {
//					//GameScreen.npcManager.NPCs.get(0).npcPosition.x -= 2f;
//				}
//				if (GameScreen.npcManager.NPCs.get(0).npcPosition.y/32 < vec
//						.getY()) {
//					//GameScreen.npcManager.NPCs.get(0).npcPosition.y += 2f;
//				}
//				if (GameScreen.npcManager.NPCs.get(0).npcPosition.y/32 > vec
//						.getY()) {
//					//GameScreen.npcManager.NPCs.get(0).npcPosition.y -= 2f;
//				}
//
//			}
//		}

	}

}
