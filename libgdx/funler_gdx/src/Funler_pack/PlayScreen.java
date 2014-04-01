package Funler_pack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class PlayScreen implements Screen {
	
	public enum state {
		GAME_PAUSE, GAME_RUN, GAME_OVER, EXIT, DEBUG
	}

	
	public GodMode mode;
	public Map mapc;
	
	public state gamestate;
	private Funler main;

	public PlayScreen(Funler main) {
		this.main = main;
		gamestate = state.GAME_RUN;

		// mapc = new ProceduralMapGenerator(Funler.W, Funler.H);
		// mapc = new MapGen(mapWidth, mapHeight, this);
		// mapc = new CaveGen(49, 49, player);
		mapc = new SeedMap(49, 49, main.player, 3242342);

		// TODO Need to get the next to method to work for the diffrent classes
		// Tile emptyTile = mapc.getEmpty();
		// player.setPosition(-emptyTile.getX(), -emptyTile.getY());
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		update(delta);
		
		// draw map
		mapc.draw();

		if (Funler.DEBUG) {
			// TODO some debug stuff when needed
			// System.out.println(dt);

			// text(frameRate, width - (5 * TEXT_SIZE), height - (TEXT_SIZE +
			// 30));

			if (mode == null) {
				mode = new GodMode();
			}
			mode.showAll(main.player.x, main.player.y, mapc.getTileMap());

			// return;
		}

		// draw player
		main.player.draw();
		
	}
	
	private void update(float dt) {
		switch (gamestate) {
		case GAME_PAUSE:
			
			break;
		case GAME_RUN:
			mapc.update(dt);
			
			break;
		case GAME_OVER:

			break;
		case DEBUG:
			break;
		case EXIT:

			break;
		default:
			break;
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
