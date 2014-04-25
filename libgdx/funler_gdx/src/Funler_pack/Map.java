package Funler_pack;

import utils.HexColor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

abstract class Map implements MapGenerator {

	protected ShapeRenderer sr;

	protected Tile[][] curChunk;


	private Vector2 mapCurr; // current draw position
	public Vector2 mapDest; // moving towards

	protected int mapX;
	protected int mapY;
	private float moveSpeed = 500;

	protected Player player;

	protected Boolean fullmap = false; // draw a map of the playable area

	Map(int mapX, int mapY, Player player) {
		sr = new ShapeRenderer();

		this.player = player;
		this.mapX = mapX;
		this.mapY = mapY;
		mapCurr = new Vector2(player.x, player.y);
		mapDest = mapCurr.cpy().scl(Funler.TILE_SIZE);

		curChunk = new Tile[mapX][mapY];
	}

	Map(Player player) {
		sr = new ShapeRenderer();
		this.player = player;
		mapCurr = new Vector2(player.x, player.y);
		mapDest = mapCurr.cpy().scl(Funler.TILE_SIZE);
	}

	Map(Tile[][] curChunk) {
		sr = new ShapeRenderer();
		mapX = curChunk.length;
		mapY = curChunk[0].length;
		this.curChunk = curChunk;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see funler.mapGenerator#getcurChunk()
	 */
	public Tile[][] getCurrentChunk() {
		return curChunk;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see funler.mapGenerator#getCurrentTile(int, int)
	 */
	public Tile getCurrentTile() {

		return null;
	}

	/***
	 * Random generated tiles
	 */
	void randGen() {
		for (int i = 0; i < mapX; i++) {
			for (int j = 0; j < mapY; j++) {
				int r = (int) (Math.random() * 2);
				if (r == 0) {
					// curChunk[i][j] = new Tile(i, j, 1);
					curChunk[i][j] = new Tile(i, j, 1);
				} else {
					curChunk[i][j] = new Tile(i, j, 0);
				}
			}
		}
	}

	public Tile getEmpty() { //
		for (int i = 0; i < mapX; i++) {
			for (int j = 0; j < mapY; j++) {
				// System.out.println(curChunk[i][j].getType());
				if (curChunk[i][j].getType() == 0)
					return curChunk[i][j];
			}
		}
		return null;
	}

	/***
	 * Random generated tiles (in blocks not singles)
	 */

	void randomBlock() {
		// map generate (int map)
		for (int i = 0; i < mapX; i += 2) {
			for (int j = 0; j < mapY; j += 2) {
				if (i + 1 >= mapX)
					continue;
				if (j + 1 >= mapY)
					break;
				int r = (int) (Math.random() * 4);
				if (r == 0) {
					curChunk[i][j].setType(1);
					curChunk[i + 1][j].setType(1);
					curChunk[i][j + 1].setType(1);
					curChunk[i + 1][j + 1].setType(1);

				} else {
					curChunk[i][j].setType(0);
					curChunk[i + 1][j].setType(0);
					curChunk[i][j + 1].setType(0);
					curChunk[i + 1][j + 1].setType(0);

				}
			}
		}
	}

	/**
	 * Return if the tile to the next direction is a tile or not. Note direction
	 * of the switch: 1 = left; 2 = up; 3 = right; 4 = down;
	 * 
	 * @param direction
	 * @return boolean
	 */
	public boolean mapHit(int direction) {
		int x = player.x;
		int y = player.y;
		switch (direction) {
		case 1:
			x--;
			break;
		case 2:
			y++;
			break;
		case 3:
			x++;
			break;
		case 4:
			y--;
			break;
		}
		if (x < 0 || y < 0)
			return true;
		if (curChunk[x][y].getType() == 1) {
			return true;
		} else {
			return false;
		}

	}

	public void drawMiniMap() {
		sr.begin(ShapeType.Filled);
		float scX = Gdx.graphics.getWidth() / (float) curChunk.length;
		float scY = Gdx.graphics.getHeight() / (float) curChunk[0].length;

		float pushX = 0;
		float pushY = 0;
		if (scX > scY) {
			scX = scY;
			pushX = ((scY * curChunk.length) - Funler.W) / 2;
		} else {
			scY = scX;
			pushY = ((scX * curChunk[0].length) - Funler.H) / 2;
		}

		for (int i = 0; i < curChunk.length; i++) {
			for (int j = 0; j < curChunk[0].length; j++) {
				if (curChunk[i][j].getType() != 1) {
					sr.setColor(new HexColor("0x329632"));
				} else {
					sr.setColor(new HexColor("0x3D9999"));
				}
				sr.rect(i * scX - pushX, j * scY - pushY, scX, scY);
			}
		}
		sr.setColor(new HexColor("#ffffff"));
		// reduse moveX to one interval at the time.
		sr.rect(player.x * scX - pushX, player.y * scY - pushY, scX, scY);

		sr.end();
	}

	public void drawMiniMap(int chunkX, int chunkY) {
		//sr.begin(ShapeType.Filled);
		float scX = Gdx.graphics.getWidth() / (float) curChunk.length;
		float scY = Gdx.graphics.getHeight() / (float) curChunk[0].length;

		float pushX = 0;
		float pushY = 0;
		if (scX > scY) {
			scX = scY;
			pushX = ((scY * curChunk.length) - Funler.W) / 2;
		} else {
			scY = scX;
			pushY = ((scX * curChunk[0].length) - Funler.H) / 2;
		}

		for (int i = 0; i < curChunk.length; i++) {
			for (int j = 0; j < curChunk[0].length; j++) {
				if (curChunk[i][j].getType() != 1) {
					sr.setColor(new HexColor("0x329632"));
				} else {
					sr.setColor(new HexColor("0x3D9999"));
				}
				sr.rect(i * scX - pushX, j * scY - pushY, scX, scY);
			}
		}
		sr.setColor(new HexColor("#ffffff"));
		sr.rect((player.x - (chunkX * curChunk.length)) * scX - pushX,
				(player.y - (chunkY * curChunk.length)) * scY - pushY, scX, scY);

		//sr.end();
	}

	public void update(float dt) {
		Vector2 vel = new Vector2();
		// TODO
		mapDest = new Vector2(player.x * Funler.TILE_SIZE, player.y
				* Funler.TILE_SIZE);
		vel = mapDest.cpy();
		vel.sub(mapCurr);
		if (vel.len() < Funler.TILE_SIZE / 10) {
			mapCurr = mapDest.cpy();
		} else {
			vel.nor();
			vel.scl(moveSpeed * dt);
			mapCurr.add(vel);
		}
	}

	/*
	 * draws the map to the current screen M key for draw minimap
	 */
	public void draw() {
		if (fullmap) {
			drawMiniMap();
			return;
		}
		sr.begin(ShapeType.Filled);
		int sw = ((Funler.W / 2) - (Funler.W / 2 % Funler.TILE_SIZE));
		int sh = ((Funler.H / 2) - (Funler.H / 2 % Funler.TILE_SIZE));
		for (int i = 0; i < mapX; i++) {
			if (i * Funler.TILE_SIZE + mapCurr.x + sw > Funler.W - 100)
				continue;
			if (i * Funler.TILE_SIZE + mapCurr.x + sw < Funler.TILE_SIZE)
				continue;
			for (int j = 0; j < mapY; j++) {
				if (j * Funler.TILE_SIZE + mapCurr.y + sh > Funler.H
						- Funler.TILE_SIZE)
					break;
				if (j * Funler.TILE_SIZE + mapCurr.y + sh < 0)
					continue;
				if (curChunk[i][j].getType() == 0) {
					sr.setColor(new HexColor("#634f0e"));
				} else {
					sr.setColor(new HexColor("#0e2563"));
				}
				sr.rect(i * Funler.TILE_SIZE + mapCurr.x + sw, j
						* Funler.TILE_SIZE + mapCurr.y + sh, Funler.TILE_SIZE,
						Funler.TILE_SIZE);
			}
		}

		sr.end();
	}

	void swapMini() {
		fullmap = fullmap ? false : true;
	}
}
