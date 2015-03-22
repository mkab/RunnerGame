package com.mkab.runnergame.game.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mkab.runnergame.game.PlatformerResourceManager;
import com.mkab.runnergame.game.RunnerGame;
import com.mkab.runnergame.game.view.Platformer;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;

public class MenuStage extends Stage {

  public MenuStage(final PlatformerResourceManager rm) {
    super(new StretchViewport(rm.currentResolution.width, rm.currentResolution.height));

    // Creating a a scene loader and passing it a resource manager of game stage
    SceneLoader sl = new SceneLoader(rm);
    sl.setResolution(rm.currentResolution.name);

    // loading UI scene
    sl.loadScene("UIScene");

    // adding it's root composite item to the stage
    addActor(sl.getRoot());

    // Creating restart button, and adding a click listener to it
    SimpleButtonScript restartButton =
        SimpleButtonScript.selfInit(sl.getRoot().getCompositeById("menuBtn"));
    restartButton.addListener(new ClickListener() {

      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return super.touchDown(event, x, y, pointer, button);
      }

      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        // stop the game music
        RunnerGame.gameMusic.stop();

        // stop rain sound
        RunnerGame.rain.stop();

        // go to the main menu when clicked
        ((Game) Gdx.app.getApplicationListener()).setScreen(new Platformer());
      }
    });
  }

}
