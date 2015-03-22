package com.mkab.runnergame.game.model;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mkab.runnergame.game.GameStage;
import com.mkab.runnergame.game.RunnerGame;
import com.mkab.runnergame.game.view.HelpScreenScript;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.script.IScript;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;

public class MenuScreenScript implements IScript {
  private final String HELP_SCENE = "HelpScene";

  private GameStage gameStage;
  private CompositeItem menu;
  private CompositeItem playButton;
  private CompositeItem settingsButton;
  private CompositeItem helpButton;

  private SimpleButtonScript playButtonScript;
  private SimpleButtonScript settingsButtonScript;
  private SimpleButtonScript helpButtonScript;

  public MenuScreenScript(GameStage gameStage) {
    this.gameStage = gameStage;
  }

  @Override
  public void init(CompositeItem item) {
    menu = item;

    playButton = menu.getCompositeById("playButton");
    playButtonScript = new SimpleButtonScript();
    playButtonScript.init(playButton);

    settingsButton = menu.getCompositeById("settingsButton");
    settingsButtonScript = new SimpleButtonScript();
    settingsButtonScript.init(settingsButton);

    helpButton = menu.getCompositeById("helpButton");
    helpButtonScript = new SimpleButtonScript();
    helpButtonScript.init(helpButton);

    // menuMusic = Assets.manager.get(Assets.menuMusic);
    // gameMusic = Assets.manager.get(Assets.gameMusic);
    RunnerGame.gameMusic = Assets.manager.get(Assets.gameMusic);

    RunnerGame.rain = Assets.manager.get(Assets.rain);

    if (Assets.manager.isLoaded("music/rain.mp3") && Assets.manager.isLoaded("music/gameMusic.mp3")) {

      settingsButtonScript.addListener(new ClickListener() {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
          return super.touchDown(event, x, y, pointer, button);
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
          if (RunnerGame.menuMusic.isPlaying())
            RunnerGame.menuMusic.stop();
          else
            RunnerGame.menuMusic.play();

          RunnerGame.soundOn ^= true;

          System.out.println("sound on = " + RunnerGame.soundOn);
          if (RunnerGame.soundOn) {
            settingsButton.getLabelById("sound").setText("SOUND: ON");
            System.out.println("if");
          } else {
            settingsButton.getLabelById("sound").setText("SOUND: OFF");
            System.out.println("else");
          }

          super.touchUp(event, x, y, pointer, button);
        }

      });

      playButtonScript.addListener(new ClickListener() {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
          return super.touchDown(event, x, y, pointer, button);
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
          // gameStage.getMenuMusic().setVolume(0.5f);
          // gameStage.getRainMusic().play();
          RunnerGame.menuMusic.stop(); // Stop menu music
          // gameMusic.setVolume(0.8f);
          gameStage.startGame(); // start game
          RunnerGame.gameMusic.play(); // start playing game music
          RunnerGame.rain.setLooping(true); // let rain sound loop forever
          RunnerGame.rain.play(); // play rain sound
          super.touchUp(event, x, y, pointer, button);
        }

      });

      // help menu
      helpButtonScript.addListener(new ClickListener() {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
          return super.touchDown(event, x, y, pointer, button);
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
          gameStage.clear();
          gameStage.sceneLoader.loadScene(HELP_SCENE);
          gameStage.sceneLoader.getRoot().addScript(new HelpScreenScript(gameStage));
          gameStage.addActor(gameStage.sceneLoader.getRoot());

          super.touchUp(event, x, y, pointer, button);
        }

      });

    }

  }

  @Override
  public void act(float delta) {
    Assets.manager.update();

    settingsButton.act(delta);
    playButton.act(delta);
    helpButton.act(delta);

    playButtonScript.act(delta);
    settingsButtonScript.act(delta);
    helpButtonScript.act(delta);

    /**
     * if (Assets.manager.isLoaded("music/rain.mp3") &&
     * Assets.manager.isLoaded("music/gameMusic.mp3")) { // if the user clicks on the play button,
     * start the game if (playButtonScript.isDown()) { // gameStage.getMenuMusic().setVolume(0.5f);
     * // gameStage.getRainMusic().play(); // menuMusic.stop(); // gameMusic.setVolume(0.8f); //
     * gameStage.startGame(); // gameMusic.play(); // rain.setLooping(true); // rain.play(); }
     * 
     * if (settingsButtonScript.isDown()) { // if (menuMusic.isPlaying()) // menuMusic.stop(); //
     * else // menuMusic.play(); } }
     **/
  }

  @Override
  public void dispose() {
    playButton.dispose();
    playButtonScript.dispose();

    settingsButton.dispose();
    settingsButtonScript.dispose();

    menu.dispose();
    gameStage.dispose();
  }
}
