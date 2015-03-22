package com.mkab.runnergame.game.model;

import com.badlogic.gdx.math.Vector2;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.physics.PhysicsBodyLoader;
import com.uwsoft.editor.renderer.script.IScript;

public class CopyOfMovingX implements IScript {

  private CompositeItem object;

  private float originalPosX;

  private int direction = 1;

  private float moveSpeed;
  private float margin;

  private Vector2 currentPosition = new Vector2();

  @Override
  public void init(CompositeItem item) {
    this.object = item;

    // set the car's origin to its center
    object.setOriginX(object.getWidth() / 2);
    moveSpeed = 1150f * object.mulX;
    margin = 100f * object.mulX;

    originalPosX = object.getX();

    if (object.getCustomVariables().getFloatVariable("moveSpeed") != null)
      moveSpeed = object.getCustomVariables().getFloatVariable("moveSpeed") * object.mulX;
    if (object.getCustomVariables().getFloatVariable("moveMargin") != null)
      margin = object.getCustomVariables().getFloatVariable("moveMargin") * object.mulX;
  }

  // @Override
  // public void act(float delta) {
  // setX(getX() + direction * delta * moveSpeed);
  // if (getX() > originalPosX + margin || getX() < originalPosX - margin) {
  // direction *= -1; // change the orientation
  // object.setScaleX(direction);
  // }
  //
  // object.setX(getX());
  // // Gdx.app.log("Moving Car", "entered here " + moveSpeed + "  " + margin + "  "
  // // + (getX() + direction * delta * moveSpeed));
  //
  // }

  @Override
  public void act(float delta) {
    if (object.getX() > originalPosX + margin || object.getX() < originalPosX - margin) {
      direction *= -1; // change the orientation
      object.setScaleX(direction);
    }

    object.setX(object.getX() + direction * delta * moveSpeed);
    // Gdx.app.log("Moving Car", "entered here " + moveSpeed + "  " + margin + "  "
    // + (getX() + direction * delta * moveSpeed));

  }

  public float getX() {
    // currentPosition = object.getBody().getPosition();
    currentPosition.x = object.getX();
    currentPosition.y = object.getY();
    return currentPosition.x / PhysicsBodyLoader.SCALE;
  }

  public void setX(float x) {
    // currentPosition = object.getBody().getPosition();
    currentPosition.x = object.getX();
    currentPosition.y = object.getY();

    object.setX(x);
    // object.getBody().setTransform(x * PhysicsBodyLoader.SCALE, currentPosition.y,
    // object.getBody().getAngle());

  }

  @Override
  public void dispose() {
    object.dispose();
  }
}
