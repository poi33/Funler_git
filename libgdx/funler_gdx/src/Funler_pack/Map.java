package Funler_pack;

import utils.HexColor;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

abstract class Map implements MapGenerator {

	private ShapeRenderer sr;

	protected Tile[][] tileMap;

	public static int TILE_SIZE;

	private Vector2 mapCurr; // current draw position
	public Vector2 mapDest; // moving towards

	protected int mapX;
	protected int mapY;
	private float moveSpeed = 500;
	
	private Player player;
	
	private Boolean fullmap = false; //draw a map of the playable area

	Map(int mapX, int mapY, int tile_size, Player player) {
		sr = new ShapeRenderer();
		TILE_SIZE = tile_size;

		this.player = player;
		this.mapX = mapX;
		this.mapY = mapY;
		mapCurr = mapDest = new Vector2(player.x, player.y);
		
		tileMap = new Tile[mapX][mapY];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see funler.mapGenerator#getTileMap()
	 */
	public Tile[][] getTileMap() {
		return tileMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see funler.mapGenerator#getCurrentTile(int, int)
	 */
	public Tile getCurrentTile(int moveX, int moveY) {
		for (int i = mapX; i > 0; i--) {
			float dx = mapX * Funler.TILE_SIZE - Funler.W / 2;
			if ((mapX - i) * Funler.TILE_SIZE - dx - 49 <= moveX
					&& (mapX - i) * Funler.TILE_SIZE + 49 - dx >= moveX)
				for (int j = mapY; j > 0; j--) {
					float dy = mapY * Funler.TILE_SIZE - Funler.H / 2;
					if (mapY * Funler.TILE_SIZE - j * Funler.TILE_SIZE - dy - 49 <= moveY
							&& mapY * Funler.TILE_SIZE - j * Funler.TILE_SIZE + 49 - dy >= moveY) {
						return tileMap[i][j];
					}
				}
		}
		System.out
				.println("Error in getCurrentTile.\nProbebly moved outside the playable area");
		return null;
	}

	/***
	 * Random generated tiles
	 */
	void randGen() {
		for (int i = 0; i < mapX; i++) {
			for (int j = 0; j < mapY; j++) {
				int r = (int) (Math.random() * 4.0f);
				if (r == 0) {
					// tileMap[i][j] = new Tile(i, j, 1);
					tileMap[i][j] = new Tile(i, j, 1);
				} else {
					tileMap[i][j] = new Tile(i, j, 0);
				}
			}
		}
	}
	
	public Tile getEmpty() {
		for(int i=0; i<mapX; i++) {
			for(int j=0; j<mapY; j++) {
				if(tileMap[i][j].getType() == 1) return tileMap[i][j];
			}
		}
		return null;
	}

	/***
	 * Random generated tiles (in blocks not singles)
	 */

	void randomBlock() {
		// map generate (int map)
		for (int i = 0; i < mapX; i += 2) {
			for (int j = 0; j < mapY; j += 2) {
				int r = (int) Math.random() * 4;
				if (r == 0) {
					tileMap[i][j] = new Tile(i, j, 1);
					tileMap[i + 1][j] = new Tile(i + 1, j, 1);
					tileMap[i][j + 1] = new Tile(i, j + 1, 1);
					tileMap[i + 1][j + 1] = new Tile(i + 1, j + 1, 1);

				} else {
					tileMap[i][j] = new Tile(i, j, 0);
					tileMap[i + 1][j] = new Tile(i + 1, j, 0);
					tileMap[i][j + 1] = new Tile(i, j + 1, 0);
					tileMap[i + 1][j + 1] = new Tile(i + 1, j + 1, 0);

				}
			}
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see funler.mapGenerator#mapHit(float, float)
	 */
	public boolean mapHit(float xcor, float ycor) {
		for (int i = mapX - 1; i > 0; i--) {
			// The player is always in the center of the screen width/2 (this is
			// what we must calc with).
			float dx = mapX * Funler.TILE_SIZE - Funler.W / 2;
			if ((mapX - i) * Funler.TILE_SIZE - dx - 49 <= xcor
					&& (mapX - i) * Funler.TILE_SIZE + 49 - dx >= xcor) {
				for (int j = mapY - 1; j > 0; j--) {
					if (tileMap[i][j].getType() == 1) {
						float dy = mapY * Funler.TILE_SIZE - Funler.H / 2;

						if (mapY * Funler.TILE_SIZE - j * Funler.TILE_SIZE - dy - 49 <= ycor
								&& mapY * Funler.TILE_SIZE - j * Funler.TILE_SIZE + 49 - dy >= ycor) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see funler.mapGenerator#miniMap(int, int)
	 */
	public void drawMiniMap() {
		sr.begin(ShapeType.Filled);
		// minimap scale
		float sc;
		if (mapX >= 200) {
			sc = 10;
		} else if (mapX >= 100) {
			sc = 20;
		} else if (mapX >= 25) {
			sc = 30;
		} else {
			sc = 40;
		}

		sr.setColor(new HexColor("0x329632"));
		for (int i = 0; i < mapX; i++) {
			for (int j = 0; j < mapY; j++) {
				if (tileMap[i][j].getType() != 1) {
					sr.rect( sc + (i * sc), j * sc,
							sc, sc);
				}
			}
		}

		sr.setColor(new HexColor("#ffffff"));
		// reduse moveX to one interval at the time.
		sr.rect((-player.x/TILE_SIZE*sc)+(mapX*sc)/2, (-player.y/TILE_SIZE*sc)+(mapY*sc/2), sc, sc);

		sr.end();
	}

	public void update(float dt) {
		Vector2 vel = new Vector2();
		//TODO
		mapDest = new Vector2(player.x, player.y);
		vel = mapDest.cpy();
		vel.sub(mapCurr);
		if (vel.len() < Map.TILE_SIZE / 10) {
			mapCurr = mapDest.cpy();
		} else {
			vel.nor();
			vel.scl(moveSpeed * dt);
			mapCurr.add(vel);
		}
	}
	
	/*
	 * draws the map to the current screen M key for draw minimap
	 */
	public void draw() {
		if (fullmap) {
			drawMiniMap();
			return;
		}
		sr.begin(ShapeType.Filled);

		for (int i = 0; i < mapX; i++) {
			if (i * Funler.TILE_SIZE + mapCurr.x > Funler.W - 100)
				continue;
			if (i * Funler.TILE_SIZE + mapCurr.x < Funler.TILE_SIZE)
				continue;
			for (int j = 0; j < mapY; j++) {
				if (j * Funler.TILE_SIZE + mapCurr.y > Funler.H - Funler.TILE_SIZE)
					break;
				if (j * Funler.TILE_SIZE + mapCurr.y < 0)
					continue;
				if (tileMap[i][j].getType() == 0) {
					sr.setColor(new HexColor("#634f0e"));
				} else {
					sr.setColor(new HexColor("#0e2563"));
				}
				sr.rect(i * Funler.TILE_SIZE + mapCurr.x, j * Funler.TILE_SIZE + 
							mapCurr.y, Funler.TILE_SIZE, Funler.TILE_SIZE);
			}
		}

		sr.end();
	}
	
	void swapMini() {
		fullmap = fullmap ? false : true;
	}
}
