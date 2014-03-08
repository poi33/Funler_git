package funler;

import processing.core.PApplet;

public class Map {
	PApplet parent;
	Tile[][] tileMap;

	int mapX;
	int mapY;

	Map(int mapX, int mapY, PApplet parent) {
		this.parent = parent;

		tileMap = new Tile[mapX][mapY];

		this.mapX = mapX;
		this.mapY = mapY;

		randGen();
		// randomBlock();

	}

	Tile[][] getTileMap() {
		return tileMap;
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

	/****
	 * Draws the play field / terrain
	 * 
	 * @param moveX
	 * @param moveY
	 */

	void drawMap(int moveX, int moveY) {
		for (int i = 0; i < mapX; i++) {
			if (i*50+moveX > parent.width-100) continue;
			if (i*50+moveX < 50) continue;
			for (int j = 0; j < mapY; j++) {
				if (j*50+moveY > parent.height-50) break;
				if (j*50+moveY < 0) continue;
				if (tileMap[i][j].getType() == 0) {
					parent.fill(99, 79, 14);
				} else {
					parent.fill(14, 34, 99);
				}
				parent.rect(i * 50 + moveX, j * 50 + moveY, 50, 50);
			}
		}
	}

	/**
	 ** A hit ditection on the map tests if the next move will be in a empty tile
	 ** 
	 * @parm xcordinant, ycordinants
	 ** @return boolean
	 */
	boolean mapHit(float xcor, float ycor) {
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

	/***
	 * Takes in movement and prints out a cool little minimap.
	 * 
	 * @param moveX
	 * @param moveY
	 */
	void miniMap(int moveX, int moveY) {
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
