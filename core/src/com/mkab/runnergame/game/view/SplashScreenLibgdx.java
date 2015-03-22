package com.mkab.runnergame.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mkab.runnergame.game.model.Assets;

/**
 * Splash screen class for the Libgdx logo
 */
public class SplashScreenLibgdx implements Screen {

  // private Texture texture = new Texture(Gdx.files.internal("splash/libgdx-logo.png"));
  private Texture texture;
  private Image splashLibgdx;
  private Stage stage;

  final float GAME_WORLD_WIDTH = 100;
  final float GAME_WORLD_HEIGHT = 50;

  OrthographicCamera camera;

  Viewport viewport;

  public SplashScreenLibgdx() {

  }

  @Override
  public void show() {
    // float scaleRatio = splashLibgdx.getWidth() / Gdx.graphics.getWidth();
    //
    // splashLibgdx.setSize(splashLibgdx.getWidth() / scaleRatio, splashLibgdx.getHeight()
    // / scaleRatio);

    if (Assets.manager.isLoaded("splash/libgdx-logo.png")) {
      texture = Assets.manager.get(Assets.ligdxTexture);
      splashLibgdx = new Image(texture);

      stage = new Stage();

      camera = new OrthographicCamera();
      viewport = new ScreenViewport(camera);

      viewport.apply();
      camera.position.set(GAME_WORLD_WIDTH / 2, GAME_WORLD_HEIGHT / 2, 0);

      float centerX = Gdx.graphics.getWidth() / 2 - (splashLibgdx.getWidth() / 2);
      float centerY = Gdx.graphics.getHeight() / 2 - (splashLibgdx.getHeight() / 2);

      splashLibgdx.setX(centerX);
      splashLibgdx.setY(centerY);

      stage.addActor(splashLibgdx);

      // splashLibgdx.addAction(Actions.sequence(Actions.alpha(0f), Actions.fadeIn(0.8f), Actions
      // .moveTo(0, 0, 0.5f), Actions.moveTo(1000, 750, 0.3f), Actions.moveTo(centerX, centerY,
      // 0.5f, Interpolation.exp10In), Actions.moveTo(0, 750, 0.1f),
      // Actions.moveTo(1000, 0, 0.5f), Actions.moveTo(centerX, centerY, 1.5f,
      // Interpolation.bounceOut), Actions.moveTo(-500, centerY, 2f, Interpolation.exp10Out),
      // Actions.delay(0.5f), Actions.fadeOut(1f), Actions.delay(0.1f), Actions
      // .run(new Runnable() {
      //
      // @Override
      // public void run() {
      //
      // // load the splash image for overlap splash
      // Assets.loadOverlapSplash();
      // Assets.manager.finishLoading();
      //
      // // change screen to the next splash screen
      // ((Game) Gdx.app.getApplicationListener()).setScreen(new SplashScreenOverlap());
      // }
      // })));

      splashLibgdx.addAction(Actions.sequence(Actions.alpha(0f), Actions.fadeIn(0.8f), Actions
          .moveTo(0, 0, 0.5f), Actions.moveTo(1000, 750, 0.3f), Actions.moveTo(centerX, centerY,
          0.5f, Interpolation.exp10In), Actions.moveTo(0, 750, 0.1f),
          Actions.moveTo(1000, 0, 0.5f), Actions.moveTo(centerX, centerY, 1.5f,
              Interpolation.bounceOut), Actions.delay(0.5f), Actions.parallel(Actions.moveTo(-250,
              centerY, 1f, Interpolation.exp10Out), Actions.fadeOut(0.3f)), Actions
              .run(new Runnable() {

                @Override
                public void run() {

                  // load the splash image for overlap splash
                  Assets.loadOverlapSplash();
                  Assets.manager.finishLoading();

                  // change screen to the next splash screen (overlap2D splash screen)
                  ((Game) Gdx.app.getApplicationListener()).setScreen(new SplashScreenOverlap());
                }
              })));

    }
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    Assets.manager.update();

    stage.act();
    stage.draw();
  }

  @Override
  public void resize(int width, int height) {
    viewport.update(width, height);
    camera.position.set(GAME_WORLD_WIDTH / 2, GAME_WORLD_HEIGHT / 2, 0);
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
    // Unload the ligdx logo for splash because it is no longer needed
    Assets.manager.unload("splash/libgdx-logo.png");
    Gdx.app.log("libgdx splash", "unloaded");

  }

}
