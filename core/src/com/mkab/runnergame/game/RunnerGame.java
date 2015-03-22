package com.mkab.runnergame.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.mkab.runnergame.game.model.Assets;
import com.mkab.runnergame.game.view.SplashScreenLibgdx;

/**
 * 
 * Main entry point for the game.
 * 
 */
public class RunnerGame extends Game {

  public static Music menuMusic;
  public static Music gameMusic;

  public static Music rain;

  public static boolean soundOn = true;

  @Override
  public void create() {
    Assets.loadMenuMusic(); // load music for the menu
    Assets.loadLibgdxSplash(); // load libdgx splash screen textures
    // wait for the menu music and libgdx splash to finish loading before starting the game
    Assets.manager.finishLoading();
    menuMusic = Assets.manager.get(Assets.menuMusic);
    menuMusic.setVolume(0.4f); // set volume

    // load libgdx splash screen
    setScreen(new SplashScreenLibgdx());
    menuMusic.play();

    // load images, sounds and music for game
    Assets.loadGameMusic();
  }

  @Override
  public void dispose() {
    super.dispose();

    menuMusic.dispose();
    gameMusic.dispose();
    rain.dispose();

    Assets.dispose();
  }

  @Override
  public void pause() {
    super.pause();
  }

  @Override
  public void resume() {
    super.resume();
  }

  @Override
  public void render() {
    super.render();
    Assets.manager.update();
  }
}
