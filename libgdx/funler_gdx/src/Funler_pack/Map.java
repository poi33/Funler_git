package Funler_pack;

import utils.HexColor;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

abstract class Map implements MapGenerator {

	protected ShapeRenderer sr;

	protected Tile[][] tileMap;

	private Vector2 mapCurr; // current draw position
	public Vector2 mapDest; // moving towards

	protected int mapX;
	protected int mapY;
	private float moveSpeed = 500;

	protected Player player;

	private Boolean fullmap = false; // draw a map of the playable area

	Map(int mapX, int mapY, Player player) {
		sr = new ShapeRenderer();

		this.player = player;
		this.mapX = mapX;
		this.mapY = mapY;
		mapCurr = new Vector2(player.x, player.y);
		mapDest = mapCurr.cpy().scl(Funler.TILE_SIZE);

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
					if (mapY * Funler.TILE_SIZE - j * Funler.TILE_SIZE - dy
							- 49 <= moveY
							&& mapY * Funler.TILE_SIZE - j * Funler.TILE_SIZE
									+ 49 - dy >= moveY) {
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
				int r = (int) (Math.random() * 2);
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
				System.out.println(tileMap[i][j].getType());
				if(tileMap[i][j].getType() == 0) return tileMap[i][j];
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
				if (i + 1 >= mapX)
					continue;
				if (j + 1 >= mapY)
					break;
				int r = (int) (Math.random() * 4);
				if (r == 0) {
					tileMap[i][j].setType(1);
					tileMap[i + 1][j].setType(1);
					tileMap[i][j + 1].setType(1);
					tileMap[i + 1][j + 1].setType(1);

				} else {
					tileMap[i][j].setType(0);
					tileMap[i + 1][j].setType(0);
					tileMap[i][j + 1].setType(0);
					tileMap[i + 1][j + 1].setType(0);

				}
			}
		}
	}

	/**
	 * Return if the tile to the next direction is a tile or not.
	 * 
	 * Note direction of the switch: 1 = left; 2 = up; 3 = right; 4 = down;
	 * 
	 * @param horizontal
	 * @param vertican
	 * @return boolean
	 */
	public boolean mapHit(int direction) {
		int x = -player.x;
		int y = -player.y;
		switch (direction) {
		case 1:
			x--;
			break;
		case 2:
			y++;
			break;
		case 3:
			x++;
			break;
		case 4:
			y--;
			break;
		}
		if (x < mapX && y < mapY && x > 0 && y > 0) {
			if (tileMap[x][y].getType() == 1) {
				return true;
			} else {
				return false;
			}

		} else {
			return true;
		}

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
			sc = 3;
		} else if (mapX >= 100) {
			sc = 5;
		} else if (mapX >= 25) {
			sc = 10;
		} else {
			sc = 40;
		}

		for (int i = 0; i < mapX; i++) {
			for (int j = 0; j < mapY; j++) {
				System.out.println(player.x + ", " + player.y);
				if (player.x == i && player.y == j) {
					sr.setColor(new HexColor("0xffffff"));
					sr.rect( sc + (i * sc), j * sc,
							sc, sc);
				}
				else if (tileMap[i][j].getType() != 1) {
					sr.setColor(new HexColor("0x329632"));
					sr.rect( sc + (i * sc), j * sc,
							sc, sc);
				}
			}
		}
		sr.setColor(new HexColor("#ffffff"));
		// reduse moveX to one interval at the time.
		sr.rect(-player.x * sc, -player.y * sc, sc, sc);

		sr.end();
	}

	public void update(float dt) {
		Vector2 vel = new Vector2();
		// TODO
		mapDest = new Vector2(player.x * Funler.TILE_SIZE, player.y
				* Funler.TILE_SIZE);
		vel = mapDest.cpy();
		vel.sub(mapCurr);
		if (vel.len() < Funler.TILE_SIZE / 10) {
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
		int sw = ((Funler.W / 2) - (Funler.W / 2 % Funler.TILE_SIZE));
		int sh = ((Funler.H / 2) - (Funler.H / 2 % Funler.TILE_SIZE));
		for (int i = 0; i < mapX; i++) {
			if (i * Funler.TILE_SIZE + mapCurr.x + sw > Funler.W - 100)
				continue;
			if (i * Funler.TILE_SIZE + mapCurr.x + sw < Funler.TILE_SIZE)
				continue;
			for (int j = 0; j < mapY; j++) {
				if (j * Funler.TILE_SIZE + mapCurr.y + sh > Funler.H
						- Funler.TILE_SIZE)
					break;
				if (j * Funler.TILE_SIZE + mapCurr.y + sh < 0)
					continue;
				if (tileMap[i][j].getType() == 0) {
					sr.setColor(new HexColor("#634f0e"));
				} else {
					sr.setColor(new HexColor("#0e2563"));
				}
				sr.rect(i * Funler.TILE_SIZE + mapCurr.x + sw, j
						* Funler.TILE_SIZE + mapCurr.y + sh, Funler.TILE_SIZE,
						Funler.TILE_SIZE);
			}
		}

		sr.end();
	}

	void swapMini() {
		fullmap = fullmap ? false : true;
	}
}
