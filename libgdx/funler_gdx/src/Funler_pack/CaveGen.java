package Funler_pack;

public class CaveGen extends Map {

	CaveGen(int mapX, int mapY, Player player, int seed) {
		super(mapX, mapY, player, seed);

		// randGen();
		mapWidth = mapX;
		mapHeight = mapY;
		percentAreWalls = 40;

		randomFillMap();
		makeCaverns();

	}

	public int mapWidth;
	public int mapHeight;
	public int percentAreWalls;

	public void makeCaverns() {
		// By initilizing column in the outter loop, its only created ONCE
		for (int column = 0, row = 0; row <= mapHeight - 1; row++) {
			for (column = 0; column <= mapWidth - 1; column++) {
				tileMap[column][row].setType(placeWallLogic(column, row));
			}
		}
	}

	int placeWallLogic(int x, int y) {
		int numWalls = GetAdjacentWalls(x, y, 1, 1);

		if (tileMap[x][y].getType() == 1) {
			// Is wall
			if (numWalls >= 4) {
				return 1;
			}
			if (numWalls < 3) {
				return 0;
			}

		} else {
			// Is not wall
			if (numWalls >= 5) {
				return 1;
			}
		}
		return 0;
	}

	public int GetAdjacentWalls(int x, int y, int scopeX, int scopeY) {
		int startX = x - scopeX;
		int startY = y - scopeY;
		int endX = x + scopeX;
		int endY = y + scopeY;

		int iX = startX;
		int iY = startY;

		int wallCounter = 0;

		for (iY = startY; iY <= endY; iY++) {
			for (iX = startX; iX <= endX; iX++) {
				if (!(iX == x && iY == y)) {
					if (IsWall(iX, iY)) {
						wallCounter += 1;
					}
				}
			}
		}
		return wallCounter;
	}

	boolean IsWall(int x, int y) {
		// Consider out-of-bound a wall
		if (isOutOfBounds(x, y)) {
			return true;
		}

		if (tileMap[x][y].getType() == 1) {
			return true;
		}

		if (tileMap[x][y].getType() == 0) {
			return false;
		}
		return false;
	}

	boolean isOutOfBounds(int x, int y) {
		if (x < 0 || y < 0) {
			return true;
		} else if (x > mapWidth - 1 || y > mapHeight - 1) {
			return true;
		}
		return false;
	}

	public void blankMap() {
		for (int row = 0; row < mapHeight; row++) {
			for (int column = 0; column < mapWidth; column++) {
				tileMap[row][column] = new Tile(row, column, 0);
			}
		}
	}

	public void randomFillMap() {
		blankMap();

		int mapMiddle = 0; // Temp variable
		for (int row = 0; row < mapHeight; row++) {
			for (int column = 0; column < mapWidth; column++) {
				// If coordinants lie on the the edge of the map (creates a
				// border)
				if (row == 0 || column == 0) {
					tileMap[row][column].setType(1);
				} else if (row == mapWidth - 1 || column == mapHeight - 1) {
					tileMap[column][row].setType(1);
				}
				// Else, fill with a wall a random percent of the time
				else {
					mapMiddle = (mapHeight / 2);

					if (row == mapMiddle && column == mapMiddle) {
						tileMap[row][column].setType(0);
					} else {
						tileMap[row][column]
								.setType(randomPercent(percentAreWalls));
					}
				}
			}
		}
	}

	int randomPercent(int percent) {
		if (percent >= Math.random() * 100) {
			return 1;
		}
		return 0;
	}

	/***
	 * Random generated tiles
	 */
	void randGen() {
		for (int i = 0; i < mapX; i++) {
			for (int j = 0; j < mapY; j++) {
				int r = (int) (Math.random() * 4.0f);
				if (r == 0) {
					tileMap[i][j] = new Tile(i, j, 1);
				} else {
					tileMap[i][j] = new Tile(i, j, 0);
				}
			}
		}
	}

	/**
	 * This method is for generating a new map. Manly used for debug could be
	 * used for multiple generated instances
	 */
	@Override
	public void generateNew() {
		// randomFillMap();

		randomBlock();
		tileMap[6][5].setType(0);
		Tile tmp = getEmpty();
		player.setPosition(-tmp.getX(), -tmp.getY());

		// makeCaverns();
	}

}
