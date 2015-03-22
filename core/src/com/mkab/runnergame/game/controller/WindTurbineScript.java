package com.mkab.runnergame.game.controller;

import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ImageItem;
import com.uwsoft.editor.renderer.script.IScript;

public class WindTurbineScript implements IScript {

  private CompositeItem windTurbine;
  private ImageItem windTurbineWheel;

  private float rotationSpeed = 0.0f;

  @Override
  public void init(CompositeItem item) {
    this.windTurbine = item;
    windTurbineWheel = windTurbine.getImageById("windTurbineWheel");
    windTurbineWheel.setOrigin(windTurbineWheel.getWidth() / 2, windTurbineWheel.getHeight() / 2);

    if (windTurbine.getCustomVariables().getFloatVariable("turbineRotation") != null)
      rotationSpeed = windTurbine.getCustomVariables().getFloatVariable("turbineRotation");
  }

  @Override
  public void dispose() {
    windTurbine.dispose();
    windTurbineWheel.dispose();
  }

  @Override
  public void act(float delta) {
    windTurbineWheel.setRotation(windTurbineWheel.getRotation() - rotationSpeed * delta);
  }
}
