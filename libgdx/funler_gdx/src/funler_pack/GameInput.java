package funler_pack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

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
			funler.mapc.mapDest.add(new Vector2(Funler.TILE_SIZE,0));
			break;
		case Input.Keys.RIGHT:
			funler.mapc.mapDest.add(new Vector2(- Funler.TILE_SIZE,0));
			break;
		case Input.Keys.UP:
			funler.mapc.mapDest.add(new Vector2(0,- Funler.TILE_SIZE));
			break;
		case Input.Keys.DOWN:
			funler.mapc.mapDest.add(new Vector2(0,Funler.TILE_SIZE));
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
