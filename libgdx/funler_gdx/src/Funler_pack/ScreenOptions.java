package Funler_pack;

import utils.HexColor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class ScreenOptions implements Screen {

	OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(),
			Gdx.graphics.getHeight());

	BitmapFont bfont;
	SpriteBatch batch;
	Skin skin;

	Stage options;
	Table table;
	
	boolean fullScreen;

	private TextButton full;
	private TextButton res1;
	private TextButton res2;
	private TextButton res3;
	private TextButton res4;
	private TextButton res5;
	private TextButton res6;
	private TextButton res7;
	private TextButton res8;
	private TextButton res9;
	
	private TextButton back;

	Funler game;

	ScreenOptions(Funler main) {
		fullScreen = Gdx.graphics.isFullscreen();
		
		bfont = new BitmapFont();
		this.game = main;
		batch = new SpriteBatch();
		options = new Stage();

		skin = new Skin();

		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		// Store the default libgdx font under the name "default".
		skin.add("default", new BitmapFont());

		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);

		table = new Table();
		table.setFillParent(true);
		options.addActor(table);

		full = simpleButton("Fullscreen"+full, -100);
		table.addActor(full);
		res1 = simpleButton("1600 - 900", 0);
		table.addActor(res1);

		res1.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.graphics.setDisplayMode(1600, 900, true);
			}
		});

		// table.add(new Image(skin.newDrawable("white",
		// Color.WHITE))).size(64);

		back =simpleButton("Back", 700);
		table.addActor(back);

		back.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				game.setGuiOptions();
			}
		});

	}
	
	private TextButton simpleButton(String s, int downY) {
		TextButton init = new TextButton(s, skin);
		init.setPosition(options.getWidth() / 2 - init.getMinWidth() / 2,
				options.getHeight() / 2 - downY);
		init.setTransform(true);
		init.setScale(2);
		return init;
	}
	
	@Override
	public void resize(int width, int height) {
		options.setViewport(width, height, true);
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

	@Override
	public void show() {
		//Could alternativly be our initialsasjon of class
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		options.act();

		options.draw();
		batch.begin();
		bfont.draw(batch, "Screen Options", Funler.W / 2, Funler.H - 130);
		batch.end();

		Table.drawDebug(options);
	}

	@Override
	public void hide() {
		System.out.println("exit menu");
	}

}
