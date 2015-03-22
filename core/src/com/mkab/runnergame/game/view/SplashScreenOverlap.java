package com.mkab.runnergame.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mkab.runnergame.game.model.Assets;

/**
 * Splash screen class for the Overlap2D logo
 */
public class SplashScreenOverlap implements Screen {

  private Texture texture, texture2, texture3, texture4, texture5;
  private Image splashOverlap, splashOverlap2, splashOverlap3, zombie1, ninja;
  private Stage stage;

  public SplashScreenOverlap() {
  }

  @Override
  public void show() {

    if (Assets.manager.isLoaded("splash/overlap.png")
        && Assets.manager.isLoaded("splash/overlap2.png")
        && Assets.manager.isLoaded("splash/overlap3.png")) {
      texture = Assets.manager.get(Assets.overlapTexture);
      texture2 = Assets.manager.get(Assets.overlapTexture2);
      texture3 = Assets.manager.get(Assets.overlapTexture3);
      texture4 = Assets.manager.get(Assets.zombie1);
      texture5 = Assets.manager.get(Assets.ninja);

      splashOverlap = new Image(texture);
      splashOverlap2 = new Image(texture2);
      splashOverlap3 = new Image(texture3);
      zombie1 = new Image(texture4);
      ninja = new Image(texture5);

      splashOverlap.setRotation(180f);
      stage = new Stage();

      zombie1.setX(900);
      ninja.setX(100);
      splashOverlap.setX(Gdx.graphics.getWidth() / 2 - (splashOverlap.getWidth() / 2));
      splashOverlap.setY(Gdx.graphics.getHeight() / 2);
      // splashOverlap.scaleBy(1f);
      splashOverlap.setOriginX(splashOverlap.getWidth() / 2);

      // splashOverlap2.setY(150f);
      splashOverlap2.setX(Gdx.graphics.getWidth() / 2 - (splashOverlap2.getWidth() / 2) + 30);
      splashOverlap2.setY(310);
      // splashOverlap2.scaleBy(0f, 1f);

      splashOverlap3.setX(Gdx.graphics.getWidth() / 2 - (splashOverlap3.getWidth() / 2) + 120);
      splashOverlap3.setY(Gdx.graphics.getHeight() / 2 - 35);

      stage.addActor(splashOverlap);
      stage.addActor(splashOverlap2);
      stage.addActor(splashOverlap3);
      stage.addActor(zombie1);
      stage.addActor(ninja);

      splashOverlap2.addAction(Actions.sequence(Actions.alpha(0f), Actions.delay(4.5f), Actions
          .fadeIn(1f), Actions.delay(1f), Actions.fadeOut(1f)));

      splashOverlap3.addAction(Actions.sequence(Actions.alpha(0f), Actions.delay(3.5f), Actions
          .fadeIn(1f), Actions.delay(2f), Actions.fadeOut(1f)));

      zombie1.addAction(Actions.sequence(Actions.alpha(0f), Actions.delay(2f), Actions.fadeIn(2f),
          Actions.delay(1f), Actions.fadeOut(1f)));

      ninja.addAction(Actions.sequence(Actions.fadeIn(1.5f), Actions.moveTo(900, 0, 5f), Actions
          .fadeOut(2f)));
      splashOverlap.addAction(Actions.sequence(Actions.alpha(0f), Actions.fadeIn(1f), Actions
          .rotateBy(585, 3f, Interpolation.bounceOut), Actions.delay(3.5f), Actions.parallel(
          Actions.rotateBy(-1080f, 2f, Interpolation.bounceIn), Actions.fadeOut(2f)), Actions
          .run(new Runnable() {

            @Override
            public void run() {
              // switch screen the the platformer screen, which loads the main menu among other
              // things
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
    texture2.dispose();
    texture3.dispose();
    stage.dispose();
    texture4.dispose();

    // Unload the overlap2d logos for splash because they are no longer needed
    Assets.manager.unload("splash/overlap.png");
    Assets.manager.unload("splash/overlap2.png");
    Assets.manager.unload("splash/overlap3.png");
    Gdx.app.log("overlap splash", "unloaded");
  }

}
