package funler;

import processing.core.PApplet;

/**
 * This class will create grid based maps procedurally!
 * 
 * goal is to generate different types of maps, such as dungeons and open
 * terrain.
 * 
 * @author j
 */
public class ProceduralMapGenerator extends Map {
	ProceduralMapGenerator(int mapX, int mapY, PApplet parent) {
		super(mapX, mapY, parent);
		
		this.parent = parent;

		this.mapX = mapX;
		this.mapY = mapY;
		
		
	}
}
