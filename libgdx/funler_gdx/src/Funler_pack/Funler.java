package Funler_pack;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Funler implements ApplicationListener {
	public static int W;
	public static int H;

	public static boolean DEBUG = false;

	public static final int TEXT_SIZE = 22;

	private int moveX = 0;
	private int moveY = 0;

	GodMode mode;
	Map mapc;

	private SpriteBatch batch;
	private ShapeRenderer sr;
	
	@Override
	public void create() {
		batch = new SpriteBatch();

		// textSize(TEXT_SIZE);

		// TODO Julain fixed framerate?
		// frameRate(60);

		//mapc = new ProceduralMapGenerator(Funler.W, Funler.H);

		//mapc = new MapGen(mapWidth, mapHeight, this);

		// Decide the size of the map here now
		mapc = new CaveGen(49, 49, 50);

		moveX = -mapc.mapX / 2 * Map.TILE_SIZE + W / 2;
		moveY = -mapc.mapY / 2 * Map.TILE_SIZE + H / 2;

		/*
		 * texture = new Texture(Gdx.files.internal("data/libgdx.png"));
		 * texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		 * 
		 * TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);
		 * 
		 * sprite = new Sprite(region); sprite.setSize(0.9f, 0.9f *
		 * sprite.getHeight() / sprite.getWidth());
		 * sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		 * sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
		 */
		
		sr = new ShapeRenderer();
	}

	@Override
	public void dispose() {
		batch.dispose();
		// texture.dispose();
	}

	@Override
	//Draw loop
	public void render() {		
		float deltaT = Gdx.graphics.getDeltaTime();
		
		// clearing screen + bg color
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (DEBUG) {
			System.out.println(deltaT);

			// text(frameRate, width - (5 * TEXT_SIZE), height - (TEXT_SIZE +
			// 30));

			if (mode == null) {
				mode = new GodMode();
			}
			mode.showAll(moveX, moveY, mapc.getTileMap());

			return;
		}

		mapc.drawMap(new Vector2(moveX, moveY));

		// The super player
		sr.begin(ShapeType.Filled);
		sr.setColor(0.5f, 1f, 0.5f, 1f);
		sr.rect(W / 2, H / 2, Map.TILE_SIZE, Map.TILE_SIZE);
		sr.end();
		
		mapc.miniMap(moveX, moveY);
	}

	@Override
	public void resize(int width, int height) {
		W = width;
		H = height;
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
