package funler;

import processing.core.PApplet;


public class Funler extends PApplet {

	public void setup() {
	    size(123, 321);
	}

	public void draw() {
	}
	
	public static void main(String _args[]) {
		PApplet.main(new String[] { funler.Funler.class.getName() });
	}
}
