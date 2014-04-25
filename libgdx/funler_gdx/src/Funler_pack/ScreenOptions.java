package Funler_pack;

import java.awt.Dimension;
import java.awt.Toolkit;

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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class ScreenOptions implements Screen {

	OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(),
			Gdx.graphics.getHeight());

	BitmapFont bfont;
	Skin skin;

	public Stage stage;
	Table table;
	
	boolean fullScreen;

	private TextButton full;
	private TextButton scrDef;
	private TextButton res[];
	
	private TextButton back;
	private Label text;

	Funler game;

	ScreenOptions(Funler main) {
		fullScreen = Gdx.graphics.isFullscreen();
		
		bfont = new BitmapFont();
		this.game = main;
		stage = new Stage();

		skin = new Skin();

		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		// Store the default libgdx font under the name "default".
		BitmapFont bfont = new BitmapFont();
		bfont.scale(Gdx.graphics.getHeight()/1000);
		skin.add("default", bfont);

		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.BLACK);
		textButtonStyle.font = bfont;
		skin.add("default", textButtonStyle);

		table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		table.row();

		full = new TextButton("Fullscreen "+fullScreen, skin);
		table.add(full);
		table.row();
		
		full.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				fullScreen = fullScreen ? false : true;
				full.setText("Fullscreen " + fullScreen);
			}
			
		});
		
		scrDef = new TextButton("Set screen", skin);
		table.add(scrDef);
		table.row();
		
		scrDef.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Dimension screendim = Toolkit.getDefaultToolkit().getScreenSize();
				int sw = screendim.width;
				int sh = screendim.height;
				Gdx.graphics.setDisplayMode(sw, sh, fullScreen);
				game.resize(sw, sh);
			}
			
		});
		
		
		
		res = new TextButton[1];
		res[0] = new TextButton("1600 - 900", skin);
		table.add(res[0]);
		table.row();

		res[0].addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.graphics.setDisplayMode(1600, 900, fullScreen);
				game.resize(1600, 900);
				game.setScreenOption();
			}
		});
		
		

		// table.add(new Image(skin.newDrawable("white",
		// Color.WHITE))).size(64);

		back = new TextButton("back", skin);
		table.add(back);

		back.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				game.setGuiOptions();
			}
		});
		table.layout();
	}
	
	@Deprecated
	private TextButton simpleButton(String s, float downY) {
		TextButton init = new TextButton(s, skin);
		init.setPosition(Gdx.graphics.getWidth() /2, Gdx.graphics.getHeight()/downY);
		init.setTransform(true);
		init.setScale(2);
		return init;
	}
	
	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height);
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

		stage.act();
		stage.draw();	
	}

	@Override
	public void hide() {
	}

}
