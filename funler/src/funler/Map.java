package funler;

import java.util.LinkedList;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Map {
	PApplet parent;
	Tile [][] tileMap;
	int[][] numMap;
	
	int mapX;
	int mapY;
	
	LinkedList<int[]> listType = new LinkedList<int[]>();
	//int[][] sqMap;

	Map(int mapX, int mapY, PApplet parent) {
		this.parent = parent;
		
		tileMap = new Tile [mapX][mapY];
		
		this.mapX = mapX;
		this.mapY = mapY;
		
		numMap = new int[mapX][mapY]; 
		buildMap();
		//for testing purpuse
		//rand();
		//test();
		//rendMap();
	}

	void rand() {
		for (int i =0; i<tileMap.length; i++) {
			for (int j =0; j<tileMap[0].length; j++) {
				int r = (int) Math.random() * 4;
				if (r == 0) {
					numMap[i][j] = 1;
				} 
				else {
					numMap[i][j] = 0;
				}
			}
		}
	}

	void test() {
		for (int i =0; i<tileMap.length; i++) {
			for (int j =0; j<tileMap[0].length; j++) {    
				if (numMap[i][j] == 0) {
					tileMap[i][j] = new Tile(0);
				} 
				else {
					tileMap[i][j] = new Tile(1);
				}
			}
		}
	}

	void rendMap() {
		for (int i=0; i<numMap.length; i++) {
			for (int j=0; j<numMap[0].length; j++) {
				if (numMap[i][j] == 1) {
					findType(i, j);
				} 
				else {
					listType.add(new int[0]);
				}
			}
		}
	}

	void drawMap(boolean truth, int moveX, int moveY) {
		int s = listType.size();
		int row=0;
		int col=0;
		for (int i =0; i<s; i++) {
			if (i%tileMap.length == 0) {
				row = i/tileMap.length;
				col = 0;
			} 
			else {
				col = i%tileMap.length;
			}

			int[] nextTo = listType.get(i);

			if (nextTo.length != 9) {
				parent.fill(50, 50, 200);
			} 
			else {
				parent.fill(50, 200, 50);
			}
			parent.rect(row*50+moveX, col*50+moveY, 50, 50);
		}
	}

	void findType(int e, int a) {
		int [] around = new int[9];
		int count=0;
		for (int j=-1; j<2; j++) {
			for (int k=-1; k<2; k++) {
				int in = e+j;
				int jn = a+k;
				if (in <0 || jn <0 || in>=tileMap.length || jn>=tileMap[0].length) {
					count++;
				}
				else if (numMap[in][jn] != 0) {
					count++;
				}
				else if (j == -1 && k == -1) {
					around[count] = 1;
					count++;
				} 
				else if (j == -1 && k == 0) {
					around[count] = 1;
					count++;
				} 
				else if (j == -1 && k == 1) {
					around[count] = 1;
					count++;
				} 
				else if (j == 0 && k == -1) {
					around[count] = 1;
					count++;
				}
				else if (j == 0 && k == 0) {
					//println("4");
					around[count] = 1;
					count++;
				}
				else if (j == 0 && k == 1) {
					around[count] = 1;
					count++;
				}
				else if (j == 1 && k == -1) {
					around[count] = 1;
					count++;
				}
				else if (j == 1 && k == 0) {
					around[count] = 1;
					count++;
				}
				else if (j == 1 && k == 1) {
					around[count] = 1;
					count++;
				}
				else {
					//sudent be here any more :P
					count++;
				}
			}
		}

		listType.add(around);
	}

	int amtAround(int[] p) {
		int counter=0;
		for (int i : p) {
			if (i == 0) {
				counter++;
			}
		}
		return counter;
	}



	void buildMap() {
		//map generate (int map)
		for (int i=0; i<mapX; i+=2) {
			for (int j =0 ; j<mapY; j+=2) {
				int r = (int)Math.random() * 4;
				if (r == 0) {
					tileMap[i][j] = new Tile(1);
					tileMap[i+1][j] = new Tile(1);
					tileMap[i][j+1] = new Tile(1);
					tileMap[i+1][j+1] = new Tile(1);

					numMap[i][j] = 1;
					numMap[i+1][j] = 1;
					numMap[i][j+1] = 1;
					numMap[i+1][j+1] = 1;
				} 
				else {
					tileMap[i][j]= new Tile(0);
					tileMap[i+1][j]= new Tile(0);
					tileMap[i][j+1]= new Tile(0);
					tileMap[i+1][j+1]= new Tile(0);

					numMap[i][j] = 0;
					numMap[i+1][j] = 0;
					numMap[i][j+1] = 0;
					numMap[i+1][j+1] = 0;
				}
			}
		}
		rendMap();
	}

	void drawMap(int moveX, int moveY) {
		for (int i=0; i<tileMap.length; i++) {
			for (int j=0; j<tileMap[i].length; j++) {
				parent.fill(100, 50, 50);
				if (numMap[i][j] == 0) {
					//end of map check
					if (50*(i+1)+moveX > 0 && 50*(j+1)+moveY >=0 && 50*i+moveX <parent.width && 50*j + moveY <parent.height) {
						if (i == 0) {
							//image(pic.left(), 50*i+moveX, 50*j+moveY);
						} 
						else if (j == tileMap[i].length-1) {
							//image(pic.up(), 50*i+moveX, 50*j+moveY);
						} 
						else if (j == 0) {
							//image(pic.down(), 50*i+moveX, 50*j+moveY);
						} 
						else if (i == tileMap.length-1) {
							//image(pic.right(), 50*i+moveX, 50*j+moveY);
						}
						else {
							parent.rect(50*(i)+moveX, 50*(j)+moveY, 50, 50);
						}
					}/* 
	           else if (moveX > 0 && i == 0) {
	           image(pic.left(), 50*i, 50*j+moveY);
	           } else if (moveX >0 && j == map[i].length-1) {
	           image(pic.down(), 50*i, 50*j+moveY);
	           } else if (moveX > 0 && moveY > 0) {
	           rect(50*i, 50*j, 50, 50);
	           } else if (moveX > 0) {
	           rect(50*i, 50*j+moveY, 50, 50);
	           } else if (moveY > 0) {
	           rect(50*i+moveX, 50*j, 50, 50); 
	           }*/
				} 
				else {
					//void tile
				}
			}
		}
	}

	void miniMap(int moveX, int moveY) {
		parent.noStroke();
		//minimap scale
		float sc;
		if (tileMap.length >= 200) {
			sc = 1;
		} 
		else if (tileMap.length >= 100) {
			sc = 2.5f;
		}
		else if (tileMap.length >= 25) {
			sc = 5;
		} 
		else {
			sc = 10;
		}

		parent.fill(50, 150, 50);
		for (int i=0; i<tileMap.length; i++) {
			for (int j=0; j<tileMap[i].length; j++) {
				if (numMap[i][j] != 1) {
					parent.rect(parent.width-tileMap.length*sc+(i*sc), j*sc, sc, sc);
				}
			}
		}
		parent.fill(255);
		// reduse moveX to one intervall at the time.
		float miniX = (moveX-parent.width/2+tileMap.length*50/2)/10;
		float miniY = (moveY-parent.height/2+tileMap[0].length*50/2)/10;
		miniX = miniX*sc/5;
		miniY = miniY*sc/5;
		parent.rect(parent.width-(tileMap.length*sc)/2-miniX, (tileMap[0].length*sc)/2-miniY, sc, sc);
		parent.stroke(1);
	}
}
