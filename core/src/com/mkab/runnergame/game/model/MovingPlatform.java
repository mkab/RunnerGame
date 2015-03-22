package com.mkab.runnergame.game.model;

import com.badlogic.gdx.math.Vector2;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.physics.PhysicsBodyLoader;
import com.uwsoft.editor.renderer.script.IScript;

/**
 * Created by CyberJoe on 10/18/2014.
 */
public class MovingPlatform implements IScript {

  private CompositeItem platform;

  private float originalPosY;

  private int direction = 1;

  private float moveSpeed;
  private float margin;

  private Vector2 currentPosition = new Vector2();

  @Override
  public void init(CompositeItem item) {
    this.platform = item;

    moveSpeed = 1150f * item.mulY;
    margin = 100f * item.mulY;

    originalPosY = item.getY();

    if (item.getCustomVariables().getFloatVariable("platformSpeed") != null)
      moveSpeed = item.getCustomVariables().getFloatVariable("platformSpeed") * item.mulY;
    if (item.getCustomVariables().getFloatVariable("platformMargin") != null)
      margin = item.getCustomVariables().getFloatVariable("platformMargin") * item.mulY;
  }

  @Override
  public void act(float delta) {
    setY(getY() + direction * delta * moveSpeed);
    if (getY() > originalPosY + margin || getY() < originalPosY - margin)
      direction *= -1;
    platform.setY(getY());
  }

  public float getY() {
    currentPosition = platform.getBody().getPosition();
    return currentPosition.y / PhysicsBodyLoader.SCALE;
  }

  public void setY(float y) {
    currentPosition = platform.getBody().getPosition();
    platform.getBody().setTransform(currentPosition.x, y * PhysicsBodyLoader.SCALE,
        platform.getBody().getAngle());
  }

  @Override
  public void dispose() {
    platform.dispose();
  }
}
