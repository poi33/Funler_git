package funler_pack;

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

	public int playerX;
	public int playerY;

	Map(int mapX, int mapY, int tile_size) {
		sr = new ShapeRenderer();
		TILE_SIZE = tile_size;

		mapCurr = mapDest = new Vector2();
		this.mapX = mapX;
		this.mapY = mapY;

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
			float dx = mapX * 50 - Funler.W / 2;
			if ((mapX - i) * 50 - dx - 49 <= moveX
					&& (mapX - i) * 50 + 49 - dx >= moveX)
				for (int j = mapY; j > 0; j--) {
					float dy = mapY * 50 - Funler.H / 2;
					if (mapY * 50 - j * 50 - dy - 49 <= moveY
							&& mapY * 50 - j * 50 + 49 - dy >= moveY) {
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
			float dx = mapX * 50 - Funler.W / 2;
			if ((mapX - i) * 50 - dx - 49 <= xcor
					&& (mapX - i) * 50 + 49 - dx >= xcor) {
				for (int j = mapY - 1; j > 0; j--) {
					if (tileMap[i][j].getType() == 1) {
						float dy = mapY * 50 - Funler.H / 2;

						if (mapY * 50 - j * 50 - dy - 49 <= ycor
								&& mapY * 50 - j * 50 + 49 - dy >= ycor) {
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
		if (tileMap.length >= 200) {
			sc = 1;
		} else if (tileMap.length >= 100) {
			sc = 2.5f;
		} else if (tileMap.length >= 25) {
			sc = 5;
		} else {
			sc = 10;
		}

		sr.setColor(new HexColor("0x329632"));
		for (int i = 0; i < tileMap.length; i++) {
			for (int j = 0; j < tileMap[i].length; j++) {
				if (tileMap[i][j].getType() != 1) {
					sr.rect(Funler.W - tileMap.length * sc + (i * sc), j * sc,
							sc, sc);
				}
			}
		}

		sr.setColor(new HexColor("#ffffff"));
		// reduse moveX to one interval at the time.
		float miniX = (playerX - Funler.W / 2 + tileMap.length * 50 / 2) / 10;
		float miniY = (playerY - Funler.H / 2 + tileMap[0].length * 50 / 2) / 10;
		miniX = miniX * sc / 5;
		miniY = miniY * sc / 5;
		sr.rect(Funler.W - (tileMap.length * sc) / 2 - miniX,
				(tileMap[0].length * sc) / 2 - miniY, sc, sc);

		sr.end();
	}

	public void update(float dt) {
		Vector2 vel = new Vector2();
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
	 * draws the map to the current screen
	 */
	public void draw() {
		sr.begin(ShapeType.Filled);

		for (int i = 0; i < mapX; i++) {
			if (i * 50 + mapDest.x > Funler.W - 100)
				continue;
			if (i * 50 + mapDest.x < 50)
				continue;
			for (int j = 0; j < mapY; j++) {
				if (j * 50 + mapDest.y > Funler.H - 50)
					break;
				if (j * 50 + mapDest.y < 0)
					continue;
				if (tileMap[i][j].getType() == 0) {
					sr.setColor(new HexColor("#634f0e"));
				} else {
					sr.setColor(new HexColor("#0e2563"));
				}
				sr.rect(i * 50 + mapCurr.x, j * 50 + mapCurr.y, 50, 50);
			}
		}

		sr.end();
	}
}
