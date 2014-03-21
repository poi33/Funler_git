package Funler_pack;

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

/* What type the square is:
	0 = (floor default tile), 
	1 = Wall tile (should not be able to walk into). 
	n = void; (all others are ignored by draw and logic)
*/

public class Tile {
	private int type;
	int x, y;

	Tile(int x, int y, int type) {
		this.type = type;
		this.x = x;
		this.y = y;
	}

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}

	int getType() {
		return type;
	}

	void setType(int a) {
		type = a;
	}

}
