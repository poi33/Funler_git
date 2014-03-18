package Funler_pack;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class Funler implements ApplicationListener {

	public static boolean DEBUG = false;

	public static final int TEXT_SIZE = 22;

	private int moveX = 0;
	private int moveY = 0;

	GodMode mode;
	Map mapc;

	// private float clock;
	private float the_time;
	private float restart = 0;

	// private boolean beg = false;

	// private int moveSpeed = 50;

	public static int w;
	public static int h;

	private OrthographicCamera camera;
	private SpriteBatch batch;

	@Override
	public void create() {
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		System.out.println(w + " | "+ h);

		camera = new OrthographicCamera(1, h / w);
		batch = new SpriteBatch();

		// textSize(TEXT_SIZE);

		// TODO Julain fixed framerate?
		// frameRate(60);

		// mapc = new ProceduralMapGenerator(mapWidth, mapHeight, this);

		// mapc = new MapGen(mapWidth, mapHeight, this);

		// Decide the size of the map here now
		mapc = new CaveGen(49, 49, 50);

		restart = TimeUtils.millis();

		moveX = -mapc.mapX / 2 * Map.TILE_SIZE + w / 2;
		moveY = -mapc.mapY / 2 * Map.TILE_SIZE + h / 2;

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
	}

	@Override
	public void dispose() {
		batch.dispose();
		// texture.dispose();
	}

	@Override
	// Draw loop
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (DEBUG) {
			// println(dt);

			// text(frameRate, width - (5 * TEXT_SIZE), height - (TEXT_SIZE +
			// 30));

			if (mode == null) {
				mode = new GodMode();
			}
			mode.showAll(moveX, moveY, mapc.getTileMap());

			return;
		}

		the_time = TimeUtils.millis();
		// clock = (the_time - restart) / 1000;
		ShapeRenderer shapeRenderer = new ShapeRenderer();
		shapeRenderer.begin(ShapeType.Filled); 
		shapeRenderer.setColor(0, 0, 0, 1); //shapeRenderer.line(100, 100, 60, 60);
		shapeRenderer.rect(0, 0, w, h);
		shapeRenderer.end();

		// background(0);
		mapc.drawMap(new Vector2(moveX, moveY));

		// The super player
		ShapeRenderer sr = new ShapeRenderer();
		sr.begin(ShapeType.Filled);
		sr.setColor(0, 0, 0, 1);
		sr.rect(w / 2, h / 2, Map.TILE_SIZE, Map.TILE_SIZE);
		sr.end();
		
		//mapc.miniMap(moveX, moveY);

		
		

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		// sprite.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
