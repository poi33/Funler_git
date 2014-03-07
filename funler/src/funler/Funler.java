package funler;

import processing.core.PApplet;

public class Funler extends PApplet {

	// Mutal size... both partly implemented
	public int[][] numMap = new int[50][50];

	private int moveX = 0;
	private int moveY = 0;

	GodMode mode;
	Map mapc;

	int clock;
	int the_time;
	int restart = 0;
	boolean beg = false;
	boolean debug = false;

	public void setup() {
		size(displayWidth, displayHeight);
		// size(300, 300);
		colorMode(RGB);
		frameRate(50);

		mapc = new Map(numMap.length, numMap[0].length, this);

		// initialize enemy

		/*
		 * for (int i=0; i<enemy.length; i++) { Badguy b = new Badguy();
		 * enemy[i] = b; }
		 */

		if (numMap[1][1] == 0) {
			moveX = width / 2 - 50;
			moveY = height / 2 - 50;
		} else if (numMap[numMap.length - 1][1] == 0) {
			moveX = width / 2 - numMap.length * 50 + 100;
			moveY = height / 2 - 50;
		} else if (numMap[numMap.length - 1][1] == 0) {
			// println("blong");
		} else {
			// println("pffft");
		}

		restart = millis();
	}

	public void draw() {
		the_time = millis();
		clock = (the_time - restart) / 1000;
		// PImage back = pic.backg();
		// back.resize(width, height);
		background(0);
		text(clock, 50, 50);

		noStroke();

		// mapc.drawMap();
		mapc.drawMap(true);

		// stroke(255, 255, 255);
		// if its within borders draw scare in the middle
		// if (moveX <= 0) {
		fill(0x9B080A);
		// rect(width/2, height/2, 50, 50);
		// image(mainC, width/2, height/2);
		rect(width / 2, height / 2, 50, 50);
		// }
		// else {
		// rect(width/2-moveX, height/2, 50, 50);
		// }
		// stroke(1);

		// for (Badguy b : enemy) {
		// b.drawEm();
		// }

		mapc.miniMap(moveX, moveY);

		if (debug == true) {
			if (mode == null) {
				mode = new GodMode();
			}
			mode.showAll(moveX, moveY);
		}
	}
	
	int[][] getNumMap() {
		return numMap;
	}

	public boolean sketchFullScreen() {
		return true;
		// return false;
	}

	/**
	 ** tests if the next move will be in a empty tile
	 ** 
	 * @parm xcordinant, ycordinants
	 ** @return boolean
	 */
	boolean mapHit(float xcor, float ycor) {
		float mapX = numMap.length;
		float mapY = numMap[0].length;

		for (int i = (int) mapX - 1; i > 0; i--) {
			for (int j = (int) mapY - 1; j > 0; j--) {
				if (numMap[i][j] == 1) {
					float dx = mapX * 50 - width / 2;
					float dy = mapY * 50 - height / 2;
					if ((mapX - i) * 50 - dx - 49 <= xcor
							&& (mapX - i) * 50 + 49 - dx >= xcor) {
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

	public void keyPressed() {
		if (key == ESC) {
			if (debug == true) {
				println("Debugg mode off");
				debug = false;
			} else {
				println("Debugg mode on");
				debug = true;
			}
			key = 0;
		} else if (key == 32) {
			exit();
		}

		int val = 10;

		if (key == CODED) {
			if (keyCode == UP && moveY < height / 2 - 50) {
				if (mapHit(moveX, moveY + val) == false) {
					moveY += val;
				}
			}
			// height/2 = the distance moved when map stops -map.length*50 /2
			// half the moving backgournd (map)
			if (keyCode == DOWN
					&& moveY > -(numMap[0].length * 50) + height / 2 + 100) {
				if (mapHit(moveX, moveY - val) == false) {
					moveY -= val;
				}
			}
			if (keyCode == RIGHT
					&& moveX > -numMap.length * 50 + width / 2 + 100) {
				if (mapHit(moveX - val, moveY) == false) {
					moveX -= val;
				}
			}
			if (keyCode == LEFT && moveX < -50 + width / 2) {
				if (mapHit(moveX + val, moveY) == false) {
					moveX += val;
				}
			}
		}
	}

	public static void main(String _args[]) {
		PApplet.main(new String[] { funler.Funler.class.getName() });
	}
}
