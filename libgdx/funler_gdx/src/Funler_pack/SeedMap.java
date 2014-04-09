package Funler_pack;

import utils.HexColor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;

public class SeedMap extends Map implements MapGenerator {

	int chunkSize = 50;
	int chunkx = 0; 
	int chunky = 0;

	private float seed;

	// cave tool. Transforms a tile[][] into desired "cave" structure
	private CaveGen caveTool;

	SeedMap(int mapX, int mapY, Player player, float seed) {
		super(mapX, mapY, player);
		this.seed = seed;
		caveTool = new CaveGen();
	}

	SeedMap(Player player, float seed) {
		super(player);
		this.seed = seed;
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
		if (x < 0 || y < 0 || x >= 50 || y >= 50)
			return true;
		if (curChunk[x][y].getType() == 1) {
			return true;
		} else {
			return false;
		}

	}

	int seedit(int i, int j) {
		// i += 5000;
		// j += 5000;
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

	@Override
	public void draw() {
		
		chunkx = (int) Math.ceil((player.x + 1) / (double) chunkSize) - 1;
		chunky = (int) Math.ceil((player.y + 1) / (double) chunkSize) - 1;

		
		curChunk = genChunk(chunkx, chunky);
		curChunk = caveTool.makeCaverns(curChunk);
		
		if (fullmap) {
			drawMiniMap(chunkx, chunky);
			return;
		}
		
		sr.begin(ShapeType.Filled);
		
		// center chunk
		drawChunk(chunkx, chunky, curChunk);

		// left chunk
		drawChunk(chunkx - 1, chunky,
				caveTool.makeCaverns(genChunk(chunkx - 1, chunky)));
		// up chunk
		drawChunk(chunkx, chunky - 1,
				caveTool.makeCaverns(genChunk(chunkx, chunky - 1)));
		// right chunk
		drawChunk(chunkx + 1, chunky,
				caveTool.makeCaverns(genChunk(chunkx + 1, chunky)));
		// down chunk
		drawChunk(chunkx, chunky + 1,
				caveTool.makeCaverns(genChunk(chunkx, chunky + 1)));

		// int tmpx = Gdx.graphics.getWidth() / Funler.TILE_SIZE - 1;
		// int tmpy = Gdx.graphics.getHeight() / Funler.TILE_SIZE - 1;

		/*
		 * for (int xer=1; xer<tmpx; xer++) { for (int yer=1; yer<tmpy; yer++) {
		 * if (seedit(px + xer, py + yer) == 1) sr.setColor(new
		 * HexColor("#634f0e")); else sr.setColor(new HexColor("#0e2563"));
		 * 
		 * sr.rect(xer * Funler.TILE_SIZE, yer * Funler.TILE_SIZE,
		 * Funler.TILE_SIZE, Funler.TILE_SIZE); } }
		 */
		sr.end();

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
