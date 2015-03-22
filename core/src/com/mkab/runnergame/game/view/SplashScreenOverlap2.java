package com.mkab.runnergame.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mkab.runnergame.game.model.Assets;

public class SplashScreenOverlap2 implements Screen {

  private Texture texture;
  private Image splashOverlap;
  private Stage stage;

  public SplashScreenOverlap2() {
  }

  @Override
  public void show() {

    if (Assets.manager.isLoaded("splash/overlap.png")) {
      texture = Assets.manager.get(Assets.overlapTexture);

      splashOverlap = new Image(texture);
      splashOverlap.setRotation(180f);
      stage = new Stage();

      splashOverlap.setX(Gdx.graphics.getWidth() / 2 - (splashOverlap.getWidth() / 2));
      splashOverlap.setY(Gdx.graphics.getHeight() / 2);
      // splashOverlap.scaleBy(1f);
      splashOverlap.setOriginX(splashOverlap.getWidth() / 2);
      stage.addActor(splashOverlap);

      splashOverlap.addAction(Actions.sequence(Actions.alpha(0f), Actions.fadeIn(1f), Actions
          .rotateBy(540f, 2f), Actions.delay(1f), Actions.fadeOut(1f), Actions.delay(1f), Actions
          .run(new Runnable() {

            @Override
            public void run() {
              ((Game) Gdx.app.getApplicationListener()).setScreen(new Platformer());
            }
          })));
    }
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    Assets.manager.update();

    stage.act();
    stage.draw();

  }

  @Override
  public void resize(int width, int height) {

  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void hide() {
    dispose();
  }

  @Override
  public void dispose() {
    texture.dispose();
    stage.dispose();

    Assets.manager.unload("splash/overlap.png");
    Gdx.app.log("overlap splash", "unloaded");
  }

}
