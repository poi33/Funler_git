package Funler_pack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class GameInput implements InputProcessor {

	Funler funler; // main funler instance

	public GameInput(Funler funler) {
		this.funler = funler;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Input.Keys.ESCAPE:
			Gdx.app.exit();
			break;
		case Input.Keys.LEFT:
			if (funler.mainscreen.mapc.mapHit(1))
				funler.player.x -= 1;
			break;
		case Input.Keys.RIGHT:
			if (funler.mainscreen.mapc.mapHit(3))
				funler.player.x += 1;
			break;
		case Input.Keys.UP:
			if (funler.mainscreen.mapc.mapHit(2))
				funler.player.y += 1;
			break;
		case Input.Keys.DOWN:
			if (funler.mainscreen.mapc.mapHit(4))
				funler.player.y -= 1;
			break;
		case Input.Keys.M:
			funler.player.hide();
			funler.mainscreen.mapc.swapMini();
			break;
		case Input.Keys.O:

			if (funler.getScreen().getClass().equals(PlayScreen.class)) {
				Gdx.input.setInputProcessor(funler.guiop.options);
				funler.setGuiOptions();
			}
			break;
		case Input.Keys.R:
			funler.mainscreen.mapc.generateNew();
			break;
		default:
			return false;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		switch (character) {
		case 'a':
		case 'A':
			if (funler.mainscreen.mapc.mapHit(1))
				funler.player.x -= 1;
			break;
		case 'd':
		case 'D':
			if (funler.mainscreen.mapc.mapHit(3))
				funler.player.x += 1;
			break;
		case 'w':
		case 'W':
			if (funler.mainscreen.mapc.mapHit(2))
				funler.player.y += 1;
			break;
		case 's':
		case 'S':
			if (funler.mainscreen.mapc.mapHit(4))
				funler.player.y -= 1;
			break;
		default:
			return false;
		}
		return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
