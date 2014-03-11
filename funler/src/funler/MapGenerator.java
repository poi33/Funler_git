package funler;

public interface MapGenerator {

	public abstract Tile[][] getTileMap();

	public abstract Tile getCurrentTile(int moveX, int moveY);

	/****
	 * Draws the play field / terrain
	 * 
	 * @param moveX
	 * @param moveY
	 */

	public abstract void drawMap(int moveX, int moveY);

	/**
	 ** A hit ditection on the map tests if the next move will be in a empty tile
	 ** 
	 * @parm xcordinant, ycordinants
	 ** @return boolean
	 */
	public abstract boolean mapHit(float xcor, float ycor);

	/***
	 * Takes in movement and prints out a cool little minimap.
	 * 
	 * @param moveX
	 * @param moveY
	 */
	public abstract void miniMap(int moveX, int moveY);

}