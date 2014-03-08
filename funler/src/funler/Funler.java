package funler;

import processing.core.PApplet;

/**
 * 
 * @author Persijn , Julian
 * 
 *         This is the main class and where all the drawing of items should
 *         occur. Currently it only holds some maps variables used for
 *         generating the map.
 * 
 *         Debug mode is set here and used for error and faults if may occur.
 *         This mode is not yet done.
 * 
 * @param void
 */
public class Funler extends PApplet {

	public int mapWidth = 550;
	public int mapHeight = 550;

	public static final boolean DEBUG = false;

	public static final int TEXT_SIZE = 22;

	private int moveX = 0;
	private int moveY = 0;

	private int timer_start = 0;
	private int timer_stop = 0;

	GodMode mode;
	Map mapc;

	int clock;
	int the_time;
	int restart = 0;
	
	boolean beg = false;
	boolean debug = false;
	
	//keyValues
	int moveSpeed = 50;

	public void setup() {
		size(displayWidth, displayHeight);

		textSize(TEXT_SIZE);

		colorMode(RGB);
		frameRate(60);

		mapc = new Map(mapWidth, mapHeight, this);

		restart = millis();
		
		moveX = width/2-50;
	    moveY = height/2-50;

	}

	/**
	 * DO NOT USE THIS DRAW LOOP! :D
	 */

	public void draw() {
		timer_stop = millis();
		int delta_time = timer_stop - timer_start;
		draw(delta_time / 1000f);
		timer_start = millis();
	}

	/**
	 * THE NEW AND IMPROVED DRAW LOOP! now with delta time! (just like love2d)
	 * 
	 * @param dt
	 */
	public void draw(float dt) {
		if (DEBUG) {
			background(100, 200, 255);

			println(dt);

			text(frameRate, width - (5 * TEXT_SIZE), height - TEXT_SIZE);
			return;
		}

		the_time = millis();
		clock = (the_time - restart) / 1000;
		// PImage back = pic.backg();
		// back.resize(width, height);
		background(0);
		noStroke();
		mapc.drawMap(moveX, moveY);
		
		text(clock, 50, 50);	
		
		//The super player
		fill(255);
		rect(width / 2, height / 2, 50, 50);
		
		mapc.miniMap(moveX, moveY);

		if (debug == true) {
			if (mode == null) {
				mode = new GodMode(this);
			}
			mode.showAll(moveX, moveY, mapc.getTileMap());
		}
	}

	public boolean sketchFullScreen() {
		return true;
		// return false;
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


		if (key == CODED) {
			if (keyCode == UP && moveY < height / 2 - 50) {
				if (mapc.mapHit(moveX, moveY + moveSpeed) == false) {
					moveY += moveSpeed;
				}
			}
			if (keyCode == DOWN ) {
				if (mapc.mapHit(moveX, moveY - moveSpeed) == false) {
					moveY -= moveSpeed;
				}
			}
			if (keyCode == RIGHT ) {
				if (mapc.mapHit(moveX - moveSpeed, moveY) == false) {
					moveX -= moveSpeed;
				}
			}
			if (keyCode == LEFT && moveX < -50 + width / 2) {
				if (mapc.mapHit(moveX + moveSpeed, moveY) == false) {
					moveX += moveSpeed;
				}
			}
		}
	}

	public static void main(String _args[]) {
		PApplet.main(new String[] { funler.Funler.class.getName() });
	}
}
