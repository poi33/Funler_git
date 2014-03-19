package funler_pack;

/**
 * This class will create grid based maps procedurally!
 * 
 * goal is to generate different types of maps, such as dungeons and open
 * terrain.
 * 
 * @author j
 */
public class ProceduralMapGenerator extends Map {
	ProceduralMapGenerator(int mapX, int mapY, int tile_size) {
		super(mapX, mapY, tile_size);
		
		this.mapX = mapX;
		this.mapY = mapY;
		
		
	}

	@Override
	public void generateNew() {
		
	}
}
