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
		switch(keycode){
		case Input.Keys.ESCAPE:
			Gdx.app.exit();
			break;
		case Input.Keys.LEFT:
			funler.player.x += 1;
			break;
		case Input.Keys.RIGHT:
			funler.player.x -= 1;
			break;
		case Input.Keys.UP:
			funler.player.y -= 1;
			break;
		case Input.Keys.DOWN:
			funler.player.y += 1;
			break;
		case Input.Keys.M:
			funler.player.hide();
			funler.mapc.swapMini();
			break;
		case Input.Keys.R:
			funler.mapc.generateNew();
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
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