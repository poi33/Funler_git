package Funler_pack;

import utils.HexColor;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class SeedMap extends Map implements MapGenerator {

	int chunkSize = 100;
	int chunkx = 0;
	int chunky = 0;
	int clover;

	protected Tile[][] cloverChunk;

	private float seed;

	// cave tool. Transforms a tile[][] into desired "cave" structure
	private CaveGen caveTool;

	SeedMap(int mapX, int mapY, Player player, float seed) {
		super(mapX, mapY, player);
		this.seed = seed;
		init();
	}

	SeedMap(Player player, float seed) {
		super(player);
		this.seed = seed;
		init();

	}

	void init() {
		// cloverChunk = new Tile[chunkSize * 2][chunkSize * 2];
		cloverChunk = new Tile[chunkSize*2][chunkSize*2];
		caveTool = new CaveGen();
	}

	@Override
	public boolean mapHit(int direction) {
		int x = player.x - (chunkx * curChunk.length);
		int y = player.y - (chunky * curChunk[0].length);
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

		int tmpx = 0;
		int tmpy = 0;

		switch (clover) {
		case 4:
			tmpx = chunkSize;
			break;
		case 3:
			break;
		case 2:
			tmpx = chunkSize;
			tmpy = chunkSize;
			break;
		case 1:
			tmpy = chunkSize;
			break;
		}

		System.out.println(cloverChunk.length);
		if (cloverChunk[tmpx + x][tmpy + y].getType() == 1) {
			return true;
		} else {
			return false;
		}

	}

	int seedit(int i, int j) {
		int calc;
		float an;

		Vector2 tmp = new Vector2(i, j);
		Vector2 tmp2 = new Vector2(-i, j);
		Vector2 tmp3 = new Vector2(i, -j);
		Vector2 tmp4 = new Vector2(-i, -j);

		an = i + seed / 360f;
		tmp = tmp.setAngleRad(an);
		an = j + seed / 360f;
		tmp2 = tmp2.setAngleRad(an);
		an = seed / (seed * i + 1) / 360f;
		tmp3 = tmp3.setAngleRad(an);
		an = seed / (seed * j + 1) / 360f;
		tmp4 = tmp4.setAngleRad(an);

		float avrage;
		float avrage2;
		avrage = tmp.x + tmp2.x / 2;
		avrage2 = tmp.y + tmp2.y / 2;
		Vector2 av = new Vector2(avrage, avrage2);
		avrage = tmp3.x + tmp4.x / 2;
		avrage2 = tmp3.y + tmp4.y / 2;
		Vector2 av2 = new Vector2(avrage, avrage2);

		av2 = av.add(av2);

		calc = (int) (av2.x + av2.y);

		if (calc % 2 == 0)
			return 0;
		else
			return 1;
	}

	/***
	 * Overrides the draw function of the map.class. This draws (in chunks) all
	 * content to the map Draws only what is within the map range. (last mention
	 * should be get rid of because it gives an advantage to players that have
	 * bigger screens)
	 * 
	 */
	// Calculation matrix.
	// +1 for top
	// +3 for bottom
	// +1 for left
	// +0 for right

	// |--------|
	// |1+1|1+0 |
	// |3+1|3+0 |
	// |--------|
	@Override
	public void draw() {

		chunkx = (int) Math.ceil((player.x + 1) / (double) chunkSize) - 1;
		chunky = (int) Math.ceil((player.y + 1) / (double) chunkSize) - 1;

		curChunk = genChunk(chunkx, chunky);
		curChunk = caveTool.makeCaverns(curChunk);

		sr.begin(ShapeType.Filled);
		if (fullmap) {
			drawMiniMap(chunkx, chunky);
			sr.end();
			return;
		}

		// where in the current chunk calculation
		int lastClover = clover;
		clover = 0;
		if ((player.x - (chunkx * chunkSize)) > chunkSize / 2)
			clover += 0;
		else
			clover += 1;
		if ((player.y - (chunky * chunkSize)) > chunkSize / 2)
			clover += 1;
		else
			clover += 3;

		boolean change = false;
		if (clover != lastClover) {
			change = true;
		}

		// center / current chunk
		drawChunk(chunkx, chunky, curChunk);
		Tile[][] first, sec, tri;
		switch (clover) {
		case 4:
			first = genChunk(chunkx - 1, chunky);
			sec = genChunk(chunkx - 1, chunky - 1);
			tri = genChunk(chunkx, chunky - 1);
			drawChunk(chunkx - 1, chunky, caveTool.makeCaverns(first));
			drawChunk(chunkx - 1, chunky - 1, caveTool.makeCaverns(sec));
			drawChunk(chunkx, chunky - 1, caveTool.makeCaverns(tri));
			if (change)
				combine(first, curChunk, sec, tri);
			break;
		case 3:
			first = genChunk(chunkx + 1, chunky);
			sec = genChunk(chunkx + 1, chunky - 1);
			tri = genChunk(chunkx, chunky - 1);
			drawChunk(chunkx + 1, chunky, caveTool.makeCaverns(first));
			drawChunk(chunkx + 1, chunky - 1, caveTool.makeCaverns(sec));
			drawChunk(chunkx, chunky - 1, caveTool.makeCaverns(tri));
			if (change)
				combine(curChunk, first, tri, sec);
			break;
		case 2:
			first = genChunk(chunkx - 1, chunky);
			sec = genChunk(chunkx - 1, chunky + 1);
			tri = genChunk(chunkx, chunky + 1);
			drawChunk(chunkx - 1, chunky, caveTool.makeCaverns(first));
			drawChunk(chunkx - 1, chunky + 1, caveTool.makeCaverns(sec));
			drawChunk(chunkx, chunky + 1, caveTool.makeCaverns(tri));
			if (change)
				combine(tri, sec, first, curChunk);
			break;
		case 1:
			first = genChunk(chunkx + 1, chunky);
			sec = genChunk(chunkx + 1, chunky + 1);
			tri = genChunk(chunkx, chunky + 1);
			drawChunk(chunkx + 1, chunky, caveTool.makeCaverns(first));
			drawChunk(chunkx + 1, chunky + 1, caveTool.makeCaverns(sec));
			drawChunk(chunkx, chunky + 1, caveTool.makeCaverns(tri));
			if (change)
				combine(first, sec, curChunk, tri);
			break;
		}

		sr.end();
	}

	void combine(Tile[][] left, Tile[][] right, Tile[][] leftdown,
			Tile[][] rightDown) {
		for (int i = 0; i < cloverChunk.length; i++) {
			for (int j = 0; j < cloverChunk[0].length; j++) {
				if (i >= chunkSize && j >= chunkSize)
					cloverChunk[i][j] = rightDown[i - chunkSize][j - chunkSize];
				else if (i >= chunkSize)
					cloverChunk[i][j] = right[i-chunkSize][j];
				else if (j >= chunkSize)
					cloverChunk[i][j] = leftdown[i][j-chunkSize];
				else
					cloverChunk[i][j] = left[i][j];

			}
		}
	}

	public Tile[][] genChunk(int chunkX, int chunkY) {
		Tile[][] tmpChunk = new Tile[chunkSize][chunkSize];

		for (int i = 0; i < chunkSize; i++) {
			for (int j = 0; j < chunkSize; j++) {
				tmpChunk[i][j] = new Tile(i, j, seedit(
						i + (chunkX * chunkSize), j + (chunkY * chunkSize)));
			}
		}

		return tmpChunk;
	}

	public void drawChunk(int chunkX, int chunkY, Tile[][] curChunk) {
		int px = player.x;
		int py = player.y;

		float mw = Funler.W / 2;
		float mh = Funler.H / 2;

		int chunkCalcX = (chunkX * Funler.TILE_SIZE) * chunkSize;
		int chunkCalcY = (chunkY * Funler.TILE_SIZE) * chunkSize;

		for (Tile[] i : curChunk) {
			for (Tile j : i) {
				if ((j.getX() - px) * Funler.TILE_SIZE + mw + chunkCalcX > Funler.W - 50
						|| (j.getX() - px) * Funler.TILE_SIZE + mw + chunkCalcX < 0)
					continue;
				if ((j.getY() - py) * Funler.TILE_SIZE + mh + chunkCalcY > Funler.H - 50
						|| (j.getY() - py) * Funler.TILE_SIZE + mh + chunkCalcY < 0)
					continue;
				if (j.getType() == 1)
					sr.setColor(new HexColor("#634f0e"));
				else
					sr.setColor(new HexColor("#0e2563"));
				sr.rect((j.getX() - px) * Funler.TILE_SIZE + mw + chunkCalcX,
						(j.getY() - py) * Funler.TILE_SIZE + mh + chunkCalcY,
						Funler.TILE_SIZE, Funler.TILE_SIZE);
			}
		}
	}

	public void generateNew() {
		seed += 0.5f;
	}

}
