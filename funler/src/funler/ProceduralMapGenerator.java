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
	ProceduralMapGenerator(int mapX, int mapY, int tile_size, PApplet parent) {
		super(mapX, mapY, tile_size, parent);
		
		this.parent = parent;

		this.mapX = mapX;
		this.mapY = mapY;
		
		
	}

	@Override
	public void generateNew() {
		
	}
}
