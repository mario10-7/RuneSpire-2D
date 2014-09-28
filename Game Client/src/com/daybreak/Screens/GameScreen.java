package com.daybreak.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.daybreak.Daybreak;
import com.daybreak.Items.ItemManager;
import com.daybreak.NPC.NPC;
import com.daybreak.NPC.NPCManager;
import com.daybreak.Nodes.NodeManager;
import com.daybreak.Player.Player;
import com.daybreak.Quests.QuestManager;
import com.daybreak.UI.RightClickOptions;
import com.daybreak.UI.TabBar;
import com.daybreak.UI.UIManager;
import com.daybreak.Util.Log;
import com.daybreak.map.MapManager;
import com.daybreak.network.management.ClientNetworkEngine;

public class GameScreen implements Screen {

	float w, h;
	public static OrthographicCamera camera;
	public SpriteBatch batch, hudBatch;
	public static Player player;
	public static MapManager mapManager;
	public static UIManager uiManager;
	public static ItemManager iManager;
	public static Log log;
	public TabBar tabBar;
	public static NodeManager nodeManager;
	public static NPCManager npcManager;
	public static QuestManager questManager;
	private BitmapFont font;
	private RightClickOptions options;

	private static ClientNetworkEngine cne;

	public GameScreen(Daybreak game, ClientNetworkEngine cne) {
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());

		camera.setToOrtho(false, w, h);

		font = new BitmapFont();
		batch = game.getBatch();
		hudBatch = new SpriteBatch();

		// Create a new map
		mapManager = new MapManager();

		// Create items
		iManager = new ItemManager();

		// Create our player
		player = new Player();

		// Temp log
		log = new Log();

		options = new RightClickOptions(game);

		tabBar = new TabBar();

		// Nodes
		nodeManager = new NodeManager();

		// Add NPCs
		npcManager = new NPCManager();

		// Add quests
		questManager = new QuestManager();

		// Create new UIManager
		uiManager = new UIManager();

		player.load();

		// Add input processor
		Gdx.input.setInputProcessor(new InputMultiplexer(player.playerInput,
				uiManager.stage));

		this.cne = cne;
	}

	@Override
	public void render(float delta) {

		// Clear the screen.
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

		player.update(this);

		camera.update();
		mapManager.update(camera);

		uiManager.update();
		nodeManager.update();
		// Draw to screen
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(player.playerSprite, (int) (player.x), (int) (player.y));
		// render NPCs
		for (NPC npc : npcManager.NPCs) {
			batch.draw(npc.npcSprite, (int) (npc.npcPosition.getX()),
					(int) (npc.npcPosition.getY()));
			npc.update();
		}
		batch.end();

		// UI Rendering
		uiManager.stage.draw();

		// TabBar
		tabBar.render();

		hudBatch.begin();
		options.update(hudBatch, player);
		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		font.draw(hudBatch,
				"x : " + Gdx.input.getX() + ", y : " + Gdx.input.getY(), 10,
				580);

		hudBatch.end();

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

	public static ClientNetworkEngine getCNE() {
		return cne;
	}
}
