package Funler_pack;


/******
 * Remade this clas so its a layer to be applied to a map.
 * In other words this just convers a given map (preferably single random tile genereated)
 * In case of lazyness there is a method inside this class that will make random generated for you(not with seed)
 * @author Anonym
 *
 */
public class CaveGen {
	public int percentAreWalls;
	int rows;
	int columns;
	
	Tile[][] tileset;

	/*CaveGen(Tile[][] tileset) {
		rows = tileset.length;
		columns = tileset[0].length;
		
		this.tileset = tileset;
		
		percentAreWalls = 40;

	}*/
	
	Tile[][] getTiles() {
		return tileset;
	}
	
	public void setTiles(Tile[][] mrT) {
		tileset = mrT;
	}

	public Tile[][] makeCaverns(Tile[][] tileset) {
		this.tileset = tileset;
		rows = tileset.length;
		columns = tileset[0].length;
		
		// By initilizing column in the outter loop, its only created ONCE
		for (int i = 0, j; i < rows; i++) {
			for (j = 0; j < columns; j++) {
				tileset[i][j].setType(placeWallLogic(i, j));
			}
		}
		return tileset;
	}

	int placeWallLogic(int x, int y) {
		int numWalls = GetAdjacentWalls(x, y, 1, 1);

		if (tileset[x][y].getType() == 1) {
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

		if (tileset[x][y].getType() == 1) {
			return true;
		}

		if (tileset[x][y].getType() == 0) {
			return false;
		}
		return false;
	}

	boolean isOutOfBounds(int x, int y) {
		if (x < 0 || y < 0) {
			return true;
		} else if (x > rows - 1 || y > columns - 1) {
			return true;
		}
		return false;
	}

	/*public void blankMap() {
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < column; column++) {
				tileset[row][column] = new Tile(row, column, 0);
			}
		}
	}*/

	/*public void randomFillMap() {
		blankMap();

		int mapMiddle = 0; // Temp variable
		for (int row = 0; row < columns; row++) {
			for (int column = 0; column < rows; column++) {
				// If coordinants lie on the the edge of the map (creates a
				// border)
				if (row == 0 || column == 0) {
					tileset[row][column].setType(1);
				} else if (row == rows - 1 || column == column - 1) {
					tileset[column][row].setType(1);
				}
				// Else, fill with a wall a random percent of the time
				else {
					mapMiddle = (column / 2);

					if (row == mapMiddle && column == mapMiddle) {
						tileset[row][column].setType(0);
					} else {
						tileset[row][column]
								.setType(randomPercent(percentAreWalls));
					}
				}
			}
		}
	}*/

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
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				int r = (int) (Math.random() * 4.0f);
				if (r == 0) {
					tileset[i][j] = new Tile(i, j, 1);
				} else {
					tileset[i][j] = new Tile(i, j, 0);
				}
			}
		}
	}
	
}
