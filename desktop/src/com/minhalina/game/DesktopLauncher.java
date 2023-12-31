package com.minhalina.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setResizable(false);
        config.setTitle("Asteroids");
        config.setWindowedMode(1024, 768);
        config.setWindowIcon("icon.png");
        new Lwjgl3Application(new AsteroidsGame(), config);
    }
}
