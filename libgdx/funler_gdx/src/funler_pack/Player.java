package Funler_pack;

import utils.HexColor;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Player {

	private ShapeRenderer sr;

	public Vector2 position;

	private Boolean invis = false;

	public Player() {
		sr = new ShapeRenderer();
		position = new Vector2();
	}
	
	public Player(float x, float y) {
		sr = new ShapeRenderer();
		position = new Vector2(x, y);
	}

	/**
	 * does not currently do much, just a square or not a square
	 */
	public void draw() {
		if (invis)
			return;
		sr.begin(ShapeType.Filled);
		sr.setColor(new HexColor("#ffffff"));
		sr.rect(Funler.W / 2, Funler.H / 2, Map.TILE_SIZE, Map.TILE_SIZE);
		sr.end();
	}
	
	void setPosition(Vector2 position) {
		this.position = position;
	}
	
	void setPosition(float x, float y) {
		position.x = x;
		position.y = y;
	}

	float getx() {
		return position.x;
	}

	float gety() {
		return position.y;
	}

	void hide() {
		invis = invis ? false : true;
	}
}
