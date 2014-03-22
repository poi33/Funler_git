package Funler_pack;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GodMode {
	private ShapeRenderer sr;
	private BitmapFont font;
	private SpriteBatch batch;
	
	
	GodMode() {
		sr = new ShapeRenderer();
		font = new BitmapFont();
		batch = new SpriteBatch();
			
		font.setScale(1);
	}

	void showAll(float moveX, float moveY, Tile[][] tileMap) {
		sr.begin(ShapeType.Filled);
		batch.begin();
		moveX = moveX * Funler.TILE_SIZE;
		moveY = moveY * Funler.TILE_SIZE;
		
		float mapX = tileMap.length;
		float mapY = tileMap[0].length;

		for (int i = (int) mapX - 1; i > 0; i--) {
			for (int j = (int) mapY - 1; j > 0; j--) {
				String we = i + "|" + j;
				
				font.draw(batch, we,50 * (i - 1) + moveX, 50 * (j - 1) + moveY + 50);
				
				if (tileMap[i][j].getType() == 1) {
					if (50 * (i + 1) + moveX > 0 && 50 * (j + 1) + moveY >= 0
							&& 50 * i + moveX < Funler.W
							&& 50 * j + moveY < Funler.H) {
						float dx = mapX * 50 - Funler.W / 2;
						float dy = mapY * 50 - Funler.H / 2;
						if ((mapX - i) * 50 - dx - 49 <= moveX
								&& (mapX - i) * 50 + 49 - dx >= moveX) {
							
							sr.setColor(50,50, 200, 255);
							sr.rect(i * 50 + moveX, j * 50 + moveY, 30, 30);
						}
						if (mapY * 50 - j * 50 - dy - 49 <= moveY
								&& mapY * 50 - j * 50 + 49 - dy >= moveY) {
							sr.setColor(200,50,50, 255);
							sr.rect(i * 50 + moveX, j * 50 + moveY, 30, 30);
						}
					}
				}
			}
		}
		batch.end();
		sr.end();
	}

}
