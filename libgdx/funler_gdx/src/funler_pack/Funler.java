package Funler_pack;

import utils.HexColor;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Funler implements ApplicationListener {
	public static int W;
	public static int H;

	public static boolean DEBUG = false;

	public static final int TEXT_SIZE = 22;
	public static int TILE_SIZE = 50;

	/*private int moveX = 0;
	private int moveY = 0;*/

	public GodMode mode;
	public Map mapc;

	public Player player;
	
	@Override
	public void create() {
		// textSize(TEXT_SIZE);

		//mapc = new ProceduralMapGenerator(Funler.W, Funler.H);

		//mapc = new MapGen(mapWidth, mapHeight, this);

		player = new Player();
		mapc = new CaveGen(49, 49, TILE_SIZE, player);
		player.setPosition(mapc.getEmpty());

		/*moveX = -mapc.mapX / 2 * Map.TILE_SIZE + W / 2;
		moveY = -mapc.mapY / 2 * Map.TILE_SIZE + H / 2;*/

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
		HexColor c = new HexColor("#aaaaaaff");
		Gdx.gl.glClearColor(c.r, c.g, c.b, c.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (DEBUG) {
			// TODO some debug stuff when needed
			//System.out.println(dt);

			// text(frameRate, width - (5 * TEXT_SIZE), height - (TEXT_SIZE +
			// 30));

			if (mode == null) {
				mode = new GodMode();
			}
			mode.showAll(player.getx(), player.gety(), mapc.getTileMap());

			return;
		}

		// draw map
		mapc.draw();

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
