package Funler_pack;

import utils.HexColor;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Player {

	private ShapeRenderer sr;

	public int x, y; // position in the tile_map

	private Boolean invis = false;

	public Player() {
		this(0,0);
	}
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		
		sr = new ShapeRenderer();
	}

	/**
	 * does not currently do much, just a square or not a square
	 */
	public void draw() {
		if (invis)
			return;
		sr.begin(ShapeType.Filled);
		sr.setColor(new HexColor("#ffffff", "00"));
		//Calculate offsets from tile grid.
		float ox = (Funler.W/2) % Funler.TILE_SIZE;
		float oy = (Funler.H/2) % Funler.TILE_SIZE;
		sr.rect(Funler.W / 2 - ox, Funler.H / 2 - oy, Map.TILE_SIZE, Map.TILE_SIZE);
		sr.end();
	}
	
	void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	void hide() {
		invis = invis ? false : true;
	}
}
