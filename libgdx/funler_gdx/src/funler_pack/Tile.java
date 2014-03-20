package Funler_pack;

import com.badlogic.gdx.math.Vector2;

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
	Vector2 position;
	private int y;

	Tile(int x, int y, int type) {
		this.type = type;
		position = new Vector2(x,y);
	}

	float getX() {
		return position.x;
	}

	float getY() {
		return position.y;
	}

	int getType() {
		return type;
	}

	void setType(int a) {
		type = a;
	}

}
