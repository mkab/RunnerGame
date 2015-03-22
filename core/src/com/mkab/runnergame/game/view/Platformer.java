package com.mkab.runnergame.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.mkab.runnergame.game.GameStage;
import com.mkab.runnergame.game.PlatformerResourceManager;
import com.mkab.runnergame.game.RunnerGame;
import com.mkab.runnergame.game.model.Assets;
import com.mkab.runnergame.game.model.MenuStage;

public class Platformer implements Screen {

  private PlatformerResourceManager rm;

  private InputMultiplexer inputMultiplexer;

  private GameStage gameStage;
  private MenuStage menuStage;

  private boolean gameStarted = false;

  private Music menuMusic;

  public Platformer() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public void show() {

    if (!RunnerGame.menuMusic.isPlaying() && RunnerGame.soundOn)
      RunnerGame.menuMusic.play();

    rm = new PlatformerResourceManager();
    rm.initPlatformerResources();

    inputMultiplexer = new InputMultiplexer();
    gameStage = new GameStage(this, rm);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    Assets.manager.update();

    // if (gameStarted) {
    // gameStage.act();
    // gameStage.draw();
    // }
    gameStage.act();
    gameStage.draw();

    // menuStage.act();
    // menuStage.draw();
  }

  public void startGame() {

    menuStage = new MenuStage(rm);
    // menuStage.setGameStage(gameStage);
    inputMultiplexer.addProcessor(gameStage);
    inputMultiplexer.addProcessor(menuStage);

    Gdx.input.setInputProcessor(inputMultiplexer);
    gameStarted = true;
  }

  @Override
  public void pause() {
    // super.pause();
  }

  @Override
  public void resume() {
    // super.resume();
  }

  @Override
  public void resize(int width, int height) {
    // super.resize(width, height);
  }

  @Override
  public void hide() {
    dispose();
  }

  @Override
  public void dispose() {
    // super.dispose();
    rm.dispose();
    gameStage.dispose();
    // menuStage.dispose();
  }

  /**
   * @return the menuStage
   */
  public MenuStage getMenuStage() {
    return menuStage;
  }
}
