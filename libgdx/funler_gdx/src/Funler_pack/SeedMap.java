package Funler_pack;

import utils.HexColor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class SeedMap extends Map implements MapGenerator {

	private float seed;
	private CaveGen caveTool;

	SeedMap(int mapX, int mapY, Player player, float seed) {
		super(mapX, mapY, player);
		this.seed = seed;
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
		int px = player.x;
		int py = player.y;

		int chuncksize = 100;

		int chunckx = player.x / chuncksize;
		int chuncky = player.y / chuncksize;
		Tile[][] cur = new Tile[50][50];
		

		sr.begin(ShapeType.Filled);
		for (int i = 0; i < chuncksize / 2; i++) {
			for (int j = 0; j < chuncksize / 2; j++) {
				cur[i][j] = new Tile(i, j, seedit(i, j));
			}
		}
		caveTool = new CaveGen(50, 50, cur);
		caveTool.makeCaverns();
		cur = caveTool.getTileMap();

		int k = 0;
		for (Tile[] i : cur) {
			for (Tile j : i) {
				if (j.getType() == 1)
					sr.setColor(new HexColor("#634f0e"));
				else
					sr.setColor(new HexColor("#0e2563"));

				sr.rect((j.getX() - px) * Funler.TILE_SIZE, (j.getY() - py)
						* Funler.TILE_SIZE, Funler.TILE_SIZE, Funler.TILE_SIZE);
			}
		}
		

		int tmpx = Gdx.graphics.getWidth() / Funler.TILE_SIZE - 1;
		int tmpy = Gdx.graphics.getHeight() / Funler.TILE_SIZE - 1;

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

	public void generateNew() {
		seed++;
	}

}
