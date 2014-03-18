
import java.awt.Dimension;
import java.awt.Toolkit;

import Funler_pack.Funler;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "funler_gdx";
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		
		cfg.width = (int) screenSize.getWidth();
		cfg.height = (int) screenSize.getHeight();
		
		new LwjglApplication(new Funler(), cfg);
	}
}
