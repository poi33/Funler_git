package funler;

import processing.core.PApplet;
import processing.core.PVector;

abstract class Map implements MapGenerator {
	PApplet parent;
	Tile[][] tileMap;

	public static int TILE_SIZE;

	PVector mapCurr; // current draw position
	PVector mapDest; // moving towards

	protected int mapX;
	protected int mapY;
	private float moveSpeed = 500;

	Map(int mapX, int mapY, int tile_size, PApplet parent) {
		this.parent = parent;

		TILE_SIZE = tile_size;

		mapCurr = mapDest = new PVector();
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
			float dx = mapX * 50 - parent.width / 2;
			if ((mapX - i) * 50 - dx - 49 <= moveX
					&& (mapX - i) * 50 + 49 - dx >= moveX)
				for (int j = mapY; j > 0; j--) {
					float dy = mapY * 50 - parent.height / 2;
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
	 * @see funler.mapGenerator#drawMap(int, int)
	 */

	public void drawMap(PVector newDest, float dt) {
		mapDest = newDest;
		PVector vel = new PVector();
		vel = newDest.get();
		vel.sub(mapCurr);
		if(vel.mag() < Map.TILE_SIZE/10){
			mapCurr = mapDest.get();
		}
		else {
			vel.normalize();
			vel.mult(moveSpeed * dt);
			mapCurr.add(vel);
		}
		for (int i = 0; i < mapX; i++) {
			if (i * 50 + newDest.x > parent.width - 100)
				continue;
			if (i * 50 + newDest.x < 50)
				continue;
			for (int j = 0; j < mapY; j++) {
				if (j * 50 + newDest.y > parent.height - 50)
					break;
				if (j * 50 + newDest.y < 0)
					continue;
				if (tileMap[i][j].getType() == 0) {
					parent.fill(99, 79, 14);
				} else {
					parent.fill(14, 34, 99);
				}
				parent.rect(i * 50 + mapCurr.x, j * 50 + mapCurr.y, 50, 50);
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
			float dx = mapX * 50 - parent.width / 2;
			if ((mapX - i) * 50 - dx - 49 <= xcor
					&& (mapX - i) * 50 + 49 - dx >= xcor) {
				for (int j = mapY - 1; j > 0; j--) {
					if (tileMap[i][j].getType() == 1) {
						float dy = mapY * 50 - parent.height / 2;

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
	public void miniMap(int moveX, int moveY) {
		parent.noStroke();
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

		parent.fill(50, 150, 50);
		for (int i = 0; i < tileMap.length; i++) {
			for (int j = 0; j < tileMap[i].length; j++) {
				if (tileMap[i][j].getType() != 1) {
					parent.rect(parent.width - tileMap.length * sc + (i * sc),
							j * sc, sc, sc);
				}
			}
		}
		parent.fill(255);
		// reduse moveX to one intervall at the time.
		float miniX = (moveX - parent.width / 2 + tileMap.length * 50 / 2) / 10;
		float miniY = (moveY - parent.height / 2 + tileMap[0].length * 50 / 2) / 10;
		miniX = miniX * sc / 5;
		miniY = miniY * sc / 5;
		parent.rect(parent.width - (tileMap.length * sc) / 2 - miniX,
				(tileMap[0].length * sc) / 2 - miniY, sc, sc);
		parent.stroke(1);
	}
}
