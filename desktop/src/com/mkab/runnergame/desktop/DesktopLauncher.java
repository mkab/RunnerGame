package com.mkab.runnergame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mkab.runnergame.game.RunnerGame;

public class DesktopLauncher {
  public static void main(String[] arg) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.title = "Runner Game";

    config.width = 1280;
    config.height = 800;

    // config.width = 800;
    // config.height = 480;

    // config.width = 480;
    // config.height = 320;
    config.resizable = false;
    new LwjglApplication(new RunnerGame(), config);
  }
}
