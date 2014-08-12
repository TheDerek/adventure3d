package ml.derek.adventure3d.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ml.derek.adventure3d.Core;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		int mod  = 1;
		config.width = 800 * mod;
		config.height = 480 * mod;



		new LwjglApplication(new Core(), config);
	}
}
