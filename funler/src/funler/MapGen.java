package funler;

import processing.core.PApplet;

public class MapGen extends Map {

	MapGen(int mapX, int mapY, PApplet parent) {
		super(mapX, mapY, parent);
		
		randGen();
	}

	void roomGen() {
		int roomSize = (int) Math.random() * 4;
		if (roomSize > 2)
			roomSize = 4;
		if (roomSize <= 2)
			roomSize = 3;
		// map generate (int map)
		for (int i = 0; i < mapX; i += roomSize) {
			for (int j = 0; j < mapY; j += 4) {
				int r = (int) (Math.random() * roomSize);
				if (r == 0) {
					genRoom(roomSize, i, j);
				} else {
					staticRoom(roomSize, i, j, 0);
				}
			}
		}
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

	
	void staticRoom(int roomSize, int tilex, int tiley, int type) {
		for (int i = 0; i < roomSize; i++)
			for (int j = 0; j < roomSize; j++)
				if (tilex + i < mapX && tiley + j < mapY) {
					tileMap[tilex + i][tiley + j] = new Tile(tilex + i, tiley
							+ j, type);
				}
	}

	void genRoom(int roomSize, int tilex, int tiley) {
		int type = 0;

		for (int i = 0; i < roomSize; i++) {
			for (int j = 0; j < roomSize; j++) {
				if (i == 1 || j == 1 || i == roomSize || j == roomSize) {
					type = 1;
				}
				if (tilex + i < mapX && tiley + j < mapY)
				tileMap[tilex + i][tiley + j] = new Tile(tilex + i, tiley + j, type);
			}
		}
	}

}
