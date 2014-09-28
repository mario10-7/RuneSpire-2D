package com.daybreak.map;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.daybreak.Screens.GameScreen;

public class MapManager{
	
	private TiledMap map;
	public TiledMapTileLayer collisionLayer,foregroundLayer,backLayer;
	public TiledMapTileLayer mineLayer,treeLayer;
	OrthogonalTiledMapRenderer renderer;
	int width;
	int height;
	
	
	public MapManager() {
		map = new TmxMapLoader().load("data/maps/map2.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		
		
		//get width and Height of map
		MapProperties properties = map.getProperties();
		
		width = (properties.get("width",Integer.class)-1)*32;
		height = (properties.get("height",Integer.class)-1)*32;
		
		//Get collision layer
		backLayer = (TiledMapTileLayer) map.getLayers().get(0);
		
		collisionLayer = (TiledMapTileLayer) map.getLayers().get("Collision");
		
		//foreground
		foregroundLayer = (TiledMapTileLayer) map.getLayers().get("Foreground");
		
		
		//Layers for different nodes
		mineLayer = (TiledMapTileLayer) map.getLayers().get("Mines");
		treeLayer = (TiledMapTileLayer) map.getLayers().get("Trees");

	}
	
	public void update(OrthographicCamera cam){
		cam.zoom = 1.3f;
		renderer.setView(cam);
		renderer.render();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
