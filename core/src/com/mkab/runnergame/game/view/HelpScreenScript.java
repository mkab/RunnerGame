package com.mkab.runnergame.game.view;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mkab.runnergame.game.GameStage;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.script.IScript;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;

public class HelpScreenScript implements IScript {

  private CompositeItem help;
  private CompositeItem backButton;

  private SimpleButtonScript backButtonScript;
  private GameStage gameStage;

  public HelpScreenScript(GameStage gameStage) {
    this.gameStage = gameStage;
  }

  @Override
  public void init(CompositeItem item) {
    help = item;

    backButton = help.getCompositeById("backToMenu");
    backButtonScript = new SimpleButtonScript();
    backButton.addScript(backButtonScript);

    backButtonScript.addListener(new ClickListener() {

      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return super.touchDown(event, x, y, pointer, button);
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        gameStage.clear();
        gameStage.sceneLoader.getRoot().removeScript(backButtonScript);
        // gameStage.loadScene("MenuScene");
        // MenuScreenScript menuScript = new MenuScreenScript(gameStage);
        //
        // // add menu script to the root of scene loader
        // gameStage.sceneLoader.getRoot().addScript(menuScript);
        //
        // // sceneLoader.getRoot().addScript(helpScript);
        // gameStage.addActor(gameStage.sceneLoader.getRoot());
        gameStage.initMenu();

        super.touchUp(event, x, y, pointer, button);
      }

    });
  }

  @Override
  public void act(float delta) {
    backButton.act(delta);
    backButtonScript.act(delta);

  }

  @Override
  public void dispose() {
    help.dispose();
    backButton.dispose();
    backButtonScript.dispose();
  }

}