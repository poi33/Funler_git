package funler_pack;

import utils.HexColor;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Player {
	
	private ShapeRenderer sr;

	public Player(){
		sr = new ShapeRenderer();
	}
	
	/**
	 * does not currently do much, just a square
	 */
	public void draw(){
		sr.begin(ShapeType.Filled);
		sr.setColor( new HexColor("#ffffff"));
		sr.rect(Funler.W / 2, Funler.H / 2, Map.TILE_SIZE, Map.TILE_SIZE);
		sr.end();
	}
}
