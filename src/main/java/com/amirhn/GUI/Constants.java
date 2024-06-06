package com.amirhn.GUI;

import java.awt.*;

/**
 * The type Constants.
 */
public class Constants {
  /**
   * The constant PieceSize.
   */
public static final Dimension PieceSize = new Dimension(100, 100);
  /**
   * The constant TileSize.
   */
public static final Dimension TileSize = new Dimension(100, 100);

  /**
   * The constant LightTileColor.
   */
public static final Color LightTileColor = new Color(240, 217, 181);
  /**
   * The constant DarkTileColor.
   */
public static final Color DarkTileColor = new Color(181, 136, 99);

  /**
   * The constant YellowGreenColor.
   */
public static final Color YellowGreenColor = new Color(155, 199, 0, 90);
  /**
   * The constant GreenColor.
   */
public static final Color GreenColor = new Color(20, 85, 30, 100);
  /**
   * The constant TransparentColor.
   */
public static final Color TransparentColor = new Color(0, 0, 0, 0);

  /**
   * The constant GameTitle.
   */
public static final String GameTitle = "Chess";
  /**
   * The constant PieceImageDirectory.
   */
public static final String PieceImageDirectory = "png";

  /**
   * The constant SoundDirectory.
   */
public static final String SoundDirectory = "sound";
  /**
   * The constant MoveSoundPath.
   */
public static final String MoveSoundPath = Constants.SoundDirectory + "/" + "move.wav";
  /**
   * The constant CaptureSoundPath.
   */
public static final String CaptureSoundPath = Constants.SoundDirectory + "/" + "capture.wav";
}
