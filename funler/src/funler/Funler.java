package funler;

import processing.core.PApplet;

public class Funler extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final boolean DEBUG = true;

	public static final int TEXT_SIZE = 22;
	
	private int timer_start = 0;
	private int timer_stop = 0;

	public void setup() {
		size(900, 800);
		textSize(TEXT_SIZE);
	}

	
	
	public void draw() {
		timer_stop = millis();
		int delta_time = timer_stop - timer_start;
		draw( delta_time / 1000f );
		timer_start = millis();
	}
	
	public void draw( float dt ){
		if (DEBUG) {
			background(100,200,255);
			
			println(dt);
			
			text(frameRate, width - (5*TEXT_SIZE), height - TEXT_SIZE);
		}
		else {
			
		}		
	}

	public static void main(String _args[]) {
		PApplet.main(new String[] { funler.Funler.class.getName() });
	}
}
