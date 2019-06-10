package com.ksimeonova.shuffle.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ksimeonova.shuffle.Shuffle;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int)Shuffle.WIDTH / 4;
		config.height = (int)Shuffle.HEIGHT / 4;
		new LwjglApplication(new Shuffle(), config);
	}
}
