package com.mkab.runnergame.game.model;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

  public static final AssetManager manager = new AssetManager();

  public static final AssetDescriptor<Music> menuMusic = new AssetDescriptor<Music>(
      "music/menuMusic.mp3", Music.class);

  public static final AssetDescriptor<Music> gameMusic = new AssetDescriptor<Music>(
      "music/gameMusic.mp3", Music.class);

  public static final AssetDescriptor<Music> rain = new AssetDescriptor<Music>("music/rain.mp3",
      Music.class);

  public static final AssetDescriptor<Texture> ligdxTexture = new AssetDescriptor<Texture>(
      "splash/libgdx-logo.png", Texture.class);

  public static final AssetDescriptor<Texture> overlapTexture = new AssetDescriptor<Texture>(
      "splash/overlap.png", Texture.class);

  public static final AssetDescriptor<Texture> overlapTexture2 = new AssetDescriptor<Texture>(
      "splash/overlap2.png", Texture.class);

  public static final AssetDescriptor<Texture> overlapTexture3 = new AssetDescriptor<Texture>(
      "splash/overlap3.png", Texture.class);

  public static final AssetDescriptor<Texture> zombie1 = new AssetDescriptor<Texture>(
      "splash/zombie1.png", Texture.class);

  public static final AssetDescriptor<Texture> ninja = new AssetDescriptor<Texture>(
      "splash/ninja.png", Texture.class);

  public static void loadMenuMusic() {
    manager.load(menuMusic);
  }

  public static void loadGameMusic() {
    manager.load(gameMusic);
    manager.load(rain);
  }

  public static void loadLibgdxSplash() {
    manager.load(ligdxTexture);
  }

  public static void loadOverlapSplash() {
    manager.load(overlapTexture);
    manager.load(overlapTexture2);
    manager.load(overlapTexture3);
    manager.load(zombie1);
    manager.load(ninja);
  }

  /**
   * Disposes of all the contents the manager contains
   */
  public static void dispose() {
    manager.dispose();
  }
}
