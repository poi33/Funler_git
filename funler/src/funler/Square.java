package funler;

import java.util.ArrayList;

public class Square {
	//What type the square is. 0 = wall, 1 = (current) default tile, 2 = void;
	private int type;
	//what orientation the pic is going to be.
	int orientation = 0;

	Square(int type) {
		this.type = type;
	}

	int getType() {
		return type;
	}

	void setType(int a) {
		type = a;
	}

	//array of all the item current on this tile
	void getItems() {
		//todo
	} 
}
