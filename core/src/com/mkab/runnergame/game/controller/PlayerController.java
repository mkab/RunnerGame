package com.mkab.runnergame.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.SpriteAnimation;
import com.uwsoft.editor.renderer.physics.PhysicsBodyLoader;
import com.uwsoft.editor.renderer.script.IScript;

public class PlayerController implements IScript {

  // Keep GameStage to access world later
  private Overlap2DStage stage;

  // Player
  private CompositeItem ninja;

  // Player Animation
  private SpriteAnimation animation;

  private boolean isWalking = false;
  private boolean isGrounded = false;

  private float moveSpeed;
  private float gravity;
  private float jumpSpeed;

  private float verticalSpeed = 0;
  private int jumpCounter = 0;

  private Vector2 initialCoordinates;

  public PlayerController(Overlap2DStage stage) {
    this.stage = stage;
  }

  @Override
  public void init(CompositeItem item) {
    this.ninja = item;

    moveSpeed = 200f * item.mulX;
    gravity = -1500f * item.mulY;
    jumpSpeed = 700f * item.mulY;

    animation = item.getSpriteAnimationById("ninja_animation");

    animation.pause();

    // Setting item origin at the center
    item.setOrigin(item.getWidth() / 2, 0);

    // setting initial position for later reset
    initialCoordinates = new Vector2(item.getX(), item.getY());
  }

  @Override
  public void act(float delta) {
    // Check for control events
    moveSpeed = 200f * ninja.mulX;

    boolean wasWalking = isWalking;

    isWalking = false;

    if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)
        && isWalking && isGrounded) {
      // increase the speed of the player
      moveSpeed = 390f * ninja.mulX;
    }

    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      // Go right
      ninja.setX(ninja.getX() + delta * moveSpeed);
      ninja.setScaleX(1f);
      isWalking = true;
    }

    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      // Go left
      ninja.setX(ninja.getX() - delta * moveSpeed);
      ninja.setScaleX(-1f);
      isWalking = true;
    }

    if (wasWalking == true && isWalking == false) {
      // Not walking anymore stop the animation
      animation.pause();
    }
    if (wasWalking == false && isWalking == true) {
      // just started to walk, start animation
      animation.start();
    }

    if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
      // Jump and double jump
      if (isGrounded || jumpCounter < 2) {
        verticalSpeed = jumpSpeed;
        isGrounded = false;
        jumpCounter++;
      }

    }

    // Gravity
    verticalSpeed += gravity * delta;

    // ray-casting for collision detection
    checkCollisions();

    // set the position
    ninja.setY(ninja.getY() + verticalSpeed * delta);

    if (ninja.getY() < -30)
      reset();

    // Gdx.app.log("player", moveSpeed + " ");
  }

  public void reset() {
    ninja.setX(initialCoordinates.x);
    ninja.setY(initialCoordinates.y);
    ninja.setScale(1f);
    verticalSpeed = 0;
  }

  /*
   * Ray cast down, and if collision is happening stop player and reposition to closest point of
   * collision
   */
  private void checkCollisions() {

    float rayGap = ninja.getHeight() / 2;

    // Ray size is the exact size of the deltaY change we plan for this frame
    float raySize = -(verticalSpeed + Gdx.graphics.getDeltaTime()) * Gdx.graphics.getDeltaTime();

    if (raySize < 5f)
      raySize = 5f;

    // only check for collisions when moving down
    if (verticalSpeed > 0)
      return;

    // Vectors of ray from middle middle
    Vector2 rayFrom =
        new Vector2((ninja.getX() + ninja.getWidth() / 2) * PhysicsBodyLoader.SCALE,
            (ninja.getY() + rayGap) * PhysicsBodyLoader.SCALE);
    Vector2 rayTo =
        new Vector2((ninja.getX() + ninja.getWidth() / 2) * PhysicsBodyLoader.SCALE,
            (ninja.getY() - raySize) * PhysicsBodyLoader.SCALE);

    // Cast the ray
    stage.getWorld().rayCast(new RayCastCallback() {

      @Override
      public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
        // Stop the player
        verticalSpeed = 0;

        // reposition player slightly upper the collision point
        ninja.setY(point.y / PhysicsBodyLoader.SCALE + 0.1f);

        // System.out.println("friction =  " + fixture.getFriction() + " fixture shape = "
        // + fixture.getShape() + " fixture name = " + fixture.getUserData());

        // make sure it is grounded, to allow jumping again
        isGrounded = true;
        jumpCounter = 0;

        return 0;
      }
    }, rayFrom, rayTo);
  }

  public CompositeItem getActor() {
    return ninja;
  }

  @Override
  public void dispose() {
    ninja.dispose();
    animation.dispose();
    stage.dispose();
  }
}
