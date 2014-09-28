package com.daybreak.Player;

import static com.daybreak.network.packet.opcode.PlayerOpcode.PLAYER_COORDINATE_UPDATE;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.daybreak.Nodes.NodeAstar;
import com.daybreak.Screens.GameScreen;
import com.daybreak.Util.Vector2i;
import com.daybreak.map.Astar;
import com.paramvirphagura.network.packet.PacketBuilder;

public class PlayerMovement {

	private int time = 0;

	private float targetX, targetY;

	private List<NodeAstar> path;

	public direction moveDirection = null;

	public static enum direction {
		LEFT, DOWN, UP, RIGHT;
	}

	public void update() {
		if (Gdx.input.isButtonPressed(0) && Gdx.input.getX() < 540
				&& Gdx.input.getY() < 404) {
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			GameScreen.camera.unproject(pos);
			targetX = pos.x;
			targetY = pos.y;
			return;
		}
		time++;
		Vector2i start = new Vector2i((int) (GameScreen.player.x / 32),
				(int) (GameScreen.player.y / 32));
		Vector2i destination = new Vector2i((int) (targetX / 32),
				(int) (targetY / 32));
		if (time % 5 == 0 && targetX != 0 && targetY != 0)
			path = Astar.findPath(start, destination);
		if (path != null) { // if there is no path
			if (path.size() > 0) {

				Vector2i vec = path.get(path.size() - 1).tile;
				if (GameScreen.player.x < vec.getX() * 32) {
					GameScreen.player.x += 2f;
					GameScreen.camera.translate(2f, 0);
				}
				if (GameScreen.player.x > vec.getX() * 32) {
					GameScreen.player.x -= 2f;
					GameScreen.camera.translate(-2f, 0);
				}
				if (GameScreen.player.y < vec.getY() * 32) {
					GameScreen.player.y += 2f;
					GameScreen.camera.translate(0, 2f);
				}
				if (GameScreen.player.y > vec.getY() * 32) {
					GameScreen.player.y -= 2f;
					GameScreen.camera.translate(0, -2f);
				}
				byte[] data = new byte[2];
				data[0] = (byte) GameScreen.player.x;
				data[0] = (byte) GameScreen.player.y;
				GameScreen.getCNE().writePacketToCurrentSession(
						PacketBuilder.build(PLAYER_COORDINATE_UPDATE));
			}
		}
	}

}
