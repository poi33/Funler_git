package Funler_pack;


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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class GUIoption implements Screen {

	OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(),
			Gdx.graphics.getHeight());

	BitmapFont bfont;
	SpriteBatch batch;
	Skin skin;

	Stage options;

	private TextButton resume;
	private TextButton exit;
	private TextButton screenFix;

	Funler game;

	GUIoption(Funler main) {
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

		Table table = new Table();
		table.setFillParent(true);
		options.addActor(table);

		resume = new TextButton("Resume", skin);
		resume.setTransform(true);
		resume.setScale(2);
		table.addActor(resume);

		resume.setPosition(options.getWidth() / 2 - resume.getMinWidth() / 2,
				options.getHeight() / 2);

		resume.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreenGame();
			}
		});
		
		
		screenFix = new TextButton("screen", skin);
		screenFix.setPosition(options.getWidth() / 2 - screenFix.getMinWidth() / 2,
				options.getHeight() / 2 +200);
		screenFix.setTransform(true);
		screenFix.setScale(2);
		
		
		
		screenFix.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreenOption();
			}
		});
		
		table.addActor(screenFix);
		
		// table.add(new Image(skin.newDrawable("white",
		// Color.WHITE))).size(64);

		exit = new TextButton("Exit", skin);
		exit.setPosition(options.getWidth() / 2 - exit.getMinWidth() / 2,
				options.getHeight() / 2 - 400);
		exit.setTransform(true);
		exit.setScale(2);

		exit.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.exit();
			}
		});

		table.addActor(exit);
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
		System.out.println("Starting options");
		resetButtons();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		options.act();

		options.draw();
		batch.begin();
		bfont.draw(batch, "Welcome to Funler!!", Funler.W / 2, Funler.H - 100);
		bfont.draw(batch, "Menu Screen", Funler.W / 2, Funler.H - 130);
		batch.end();

		Table.drawDebug(options);
	}

	@Override
	public void hide() {
		System.out.println("exit menu");
	}
	
	/**
	 * resets the buttons states -> unclicked!! woah
	 * Consider destroy / recreate entire menu instead
	 */
	public void resetButtons(){
		//resume.setChecked(false);
		//exit.setChecked(false);
	}

}
