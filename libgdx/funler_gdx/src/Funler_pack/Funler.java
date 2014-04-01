package Funler_pack;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Funler extends Game implements ApplicationListener {
	public static int W;
	public static int H;

	public GUIoption guiop;
	public PlayScreen mainscreen;
	public Player player;
	GameInput input;

	public static boolean DEBUG = false;

	public static final int TEXT_SIZE = 5;
	public static int TILE_SIZE = 50;
	

	@Override
	public void create() {
		player = new Player();
		guiop = new GUIoption(this);
		mainscreen = new PlayScreen(this);
		
		setScreen(guiop); 
		Gdx.input.setInputProcessor(guiop.options);
	}

	@Override
	// Draw loop
	public void render() {
		float dt = Gdx.graphics.getDeltaTime();
		getScreen().render(dt);
	}
	
	public void setScreenOptions() {
		Gdx.input.setInputProcessor(guiop.options);
		setScreen(guiop);
	}
	
	public void setScreenGame() {
		if (input == null) 
		 input = new GameInput(this);
		
		Gdx.input.setInputProcessor(input);
		setScreen(mainscreen);
	}

	private void draw() {
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
		guiop.options.dispose();
		guiop.batch.dispose();
		guiop.skin.dispose();
		//guiop.bfont.dispose();
	}

	@Override
	public void resume() {
	}
}
