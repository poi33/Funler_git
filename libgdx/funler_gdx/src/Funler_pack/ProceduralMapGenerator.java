package Funler_pack;

/**
 * This class will create grid based maps procedurally!
 * 
 * goal is to generate different types of maps, such as dungeons and open
 * terrain.
 * 
 * @author j
 */
public class ProceduralMapGenerator extends Map {
	ProceduralMapGenerator(int mapX, int mapY, Player player, int seed) {
		super(mapX, mapY, player, seed);
		
		this.mapX = mapX;
		this.mapY = mapY;
		
		
	}

	@Override
	public void generateNew() {
		
	}
}
