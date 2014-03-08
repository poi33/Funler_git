package funler;

/**
 * 
 * @author Anonym
 * 
 *         The objects that store the information for map generation.
 * 
 * @param int type the type of tile.
 * @param int xcordinat
 * @param int ycordinat
 * 
 */

public class Tile {
	// What type the square is. 0 = wall, 1 = (current) default tile, 2 = void;
	private int type;
	private int xcor;
	private int ycor;

	Tile(int x, int y, int type) {
		this.type = type;
		this.xcor = x;
		this.ycor = y;
	}

	int getX() {
		return xcor;
	}

	int getY() {
		return ycor;
	}

	int getType() {
		return type;
	}

	void setType(int a) {
		type = a;
	}

}
