package com.mkab.runnergame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mkab.runnergame.game.controller.PlayerController;
import com.mkab.runnergame.game.controller.WindTurbineScript;
import com.mkab.runnergame.game.controller.WindmillScript;
import com.mkab.runnergame.game.model.MenuScreenScript;
import com.mkab.runnergame.game.model.MovingPlatform;
import com.mkab.runnergame.game.model.MovingX;
import com.mkab.runnergame.game.view.Platformer;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.IBaseItem;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;

public class GameStage extends Overlap2DStage {

  private final String MENU_SCENE = "MenuScene";
  private final String MAIN_SCENE = "MainScene";
  private final String HELP_SCENE = "HelpScene";

  private SimpleButtonScript backToMenuButtonScript;

  // private UIStage uiStage;

  PlatformerResourceManager rm;

  private PlayerController player;
  private Platformer testPlatformer;

  private boolean playerLoaded = false;

  // private Music menuMusic, rain;

  public GameStage(Platformer testPlatformer, PlatformerResourceManager rm) {
    super(new StretchViewport(rm.stageWidth, rm.currentResolution.height));

    this.testPlatformer = testPlatformer;
    this.rm = rm;
    System.out.println("new game stage");

    // menuMusic = Gdx.audio.newMusic(Gdx.files.internal("music/menumusic.mp3"));
    // menuMusic.setLooping(true);
    // menuMusic.play();
    //
    // rain = Gdx.audio.newMusic(Gdx.files.internal("music/rain.mp3"));
    // rain.setLooping(true);

    // This will create SceneLoader instance and configure all things like default resource manager,
    // physics e.t.c
    initSceneLoader(rm);

    sceneLoader.setResolution(rm.currentResolution.name);

    // initialise the menu screen
    initMenu();

    Gdx.input.setInputProcessor(this);
  }

  public void initMenu() {
    clear();
    sceneLoader.loadScene(MENU_SCENE);
    // sceneLoader.loadScene(HELP_SCENE);

    MenuScreenScript menuScript = new MenuScreenScript(this);
    // HelpScreenScript helpScript = new HelpScreenScript();

    // add menu script to the root of scene loader
    sceneLoader.getRoot().addScript(menuScript);

    // sceneLoader.getRoot().addScript(helpScript);
    addActor(sceneLoader.getRoot());
  }

  public void startGame() {
    clear();

    System.out.println("entered game stage start game");
    // This will load MainScene data from json and make Actors from it
    sceneLoader.loadScene(MAIN_SCENE);

    // Get the root actor and add it to stage
    addActor(sceneLoader.getRoot());

    // intialise backToMenuButtonScript
    backToMenuButtonScript =
        SimpleButtonScript.selfInit(sceneLoader.getRoot().getCompositeById("menuBtn"));
    backToMenuButtonScript.addListener(new ClickListener() {

      /*
       * (non-Javadoc)
       * 
       * @see
       * com.badlogic.gdx.scenes.scene2d.utils.ClickListener#touchDown(com.badlogic.gdx.scenes.scene2d
       * .InputEvent, float, float, int, int)
       */
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        // TODO Auto-generated method stub
        return super.touchDown(event, x, y, pointer, button);
      }

      /*
       * (non-Javadoc)
       * 
       * @see
       * com.badlogic.gdx.scenes.scene2d.utils.ClickListener#touchUp(com.badlogic.gdx.scenes.scene2d
       * .InputEvent, float, float, int, int)
       */
      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        // TODO Auto-generated method stub
        // sceneLoader.loadScene(MENU_SCENE);
      }

    });
    // add player script to scene loader root
    player = new PlayerController(this);
    sceneLoader.getRoot().getCompositeById("ninja").addScript(player);

    // uiStage = new UIStage(rm, this);

    // // add windmill script to scene loader root
    // windmillScript = new WindmillScript();
    // sceneLoader.getRoot().getCompositeById("windmill").addScript(windmillScript);
    //
    // // add wind turbine script to scene loader root
    // windTurbineScript = new WindTurbineScript();
    // sceneLoader.getRoot().getCompositeById("windTurbine").addScript(windTurbineScript);

    // add windmill script to windmills in scene loader root
    for (IBaseItem item : sceneLoader.getRoot().getItems()) {
      if (item.getCustomVariables().getFloatVariable("windmillRotation") != null
          && item.isComposite()) {
        ((CompositeItem) item).addScript(new WindmillScript());
      }
    }

    // add wind turbine script to wind turbines in scene loader root
    for (IBaseItem item : sceneLoader.getRoot().getItems()) {
      if (item.getCustomVariables().getFloatVariable("turbineRotation") != null
          && item.isComposite()) {
        ((CompositeItem) item).addScript(new WindTurbineScript());
      }
    }

    // add moving platform script to platforms in scene loader root
    for (IBaseItem item : sceneLoader.getRoot().getItems()) {
      if (item.getCustomVariables().getFloatVariable("platformSpeed") != null && item.isComposite()) {
        ((CompositeItem) item).addScript(new MovingPlatform());
      }
    }

    // add moving car script to all cars in scene loader root
    for (IBaseItem item : sceneLoader.getRoot().getItems()) {
      if (item.getCustomVariables().getFloatVariable("moveSpeed") != null && item.isComposite()) {
        ((CompositeItem) item).addScript(new MovingX());
      }
    }
    playerLoaded = true;
    testPlatformer.startGame();
  }

  public void act(float delta) {
    super.act(delta);

    if (playerLoaded) {
      // uiStage.act();
      // uiStage.draw();
      ((OrthographicCamera) getCamera()).position.x = player.getActor().getX();
      // backToMenuButtonScript.getItem().setX(player.getActor().getX());
      // backToMenuButtonScript.act(delta);
      testPlatformer.getMenuStage().draw();
      testPlatformer.getMenuStage().act();
    }
  }

  public void restart() {
    player.reset();
  }

  /**
   * @return the testPlatformer
   */
  public Platformer getTestPlatformer() {
    return testPlatformer;
  }
}
