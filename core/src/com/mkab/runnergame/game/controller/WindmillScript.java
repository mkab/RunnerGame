package com.mkab.runnergame.game.controller;

import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ImageItem;
import com.uwsoft.editor.renderer.script.IScript;

public class WindmillScript implements IScript {

  private CompositeItem windmill;
  private ImageItem wing;

  private float rotationSpeed = 0.0f;

  @Override
  public void init(CompositeItem item) {
    this.windmill = item;
    wing = windmill.getImageById("wing");
    wing.setOrigin(wing.getWidth() / 2, wing.getHeight() / 2);

    if (windmill.getCustomVariables().getFloatVariable("windmillRotation") != null)
      rotationSpeed = windmill.getCustomVariables().getFloatVariable("windmillRotation");
  }

  @Override
  public void dispose() {
    windmill.dispose();
    wing.dispose();
  }

  @Override
  public void act(float delta) {
    wing.setRotation(wing.getRotation() - rotationSpeed * delta);
  }

}
