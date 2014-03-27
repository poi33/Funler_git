package Funler_pack;

import utils.HexColor;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Funler implements ApplicationListener {
	public static int W;
	public static int H;

	public static boolean DEBUG = false;

	public static final int TEXT_SIZE = 5;
	public static int TILE_SIZE = 50;

	public GodMode mode;
	public Map mapc;

	public Player player;
	
	@Override
	public void create() {
		// textSize(TEXT_SIZE);

		//mapc = new ProceduralMapGenerator(Funler.W, Funler.H);

		//mapc = new MapGen(mapWidth, mapHeight, this);

		player = new Player();
		//NB! Seed currently tryed implement (and of course our seed is 42)
		//mapc = new CaveGen(49, 49, player);
		mapc = new SeedMap(49, 49, player, 3242342);
		//Tile emptyTile = mapc.getEmpty();
		//player.setPosition(-emptyTile.getX(), -emptyTile.getY());

		GameInput input = new GameInput( this );
		Gdx.input.setInputProcessor(input);
		
		
	}

	@Override
	//Draw loop
	public void render() {		
		float dt = Gdx.graphics.getDeltaTime();
		
		update(dt); // update game state
		draw(); // draw everything
	}

	private void update(float dt) {
		mapc.update(dt);
	}

	private void draw() {
		// clearing screen + bg color
		HexColor c = new HexColor("#aaaaaa", "ff");
		Gdx.gl.glClearColor(c.r, c.g, c.b, c.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// draw map
		mapc.draw();

		if (DEBUG) {
			// TODO some debug stuff when needed
			//System.out.println(dt);

			// text(frameRate, width - (5 * TEXT_SIZE), height - (TEXT_SIZE +
			// 30));

			if (mode == null) {
				mode = new GodMode();
			}
			mode.showAll(player.x, player.y, mapc.getTileMap());

			//return;
		}
		
		// draw player
		player.draw();
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
	public void dispose() {

	}

	@Override
	public void resume() {
	}
}
