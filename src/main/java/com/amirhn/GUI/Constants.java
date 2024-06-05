package com.amirhn.GUI;

import java.awt.*;

public class Constants {
  public static final Dimension PieceSize = new Dimension(100, 100);
  public static final Dimension TileSize = new Dimension(100, 100);

  public static final Color LightTileColor = new Color(240, 217, 181);
  public static final Color DarkTileColor = new Color(181, 136, 99);

  public static final Color YellowGreenColor = new Color(155, 199, 0, 90);
  public static final Color GreenColor = new Color(20, 85, 30, 100);
  public static final Color TransparentColor = new Color(0, 0, 0, 0);

  public static final String GameTitle = "Chess";
  public static final String PieceImageDirectory = "png";

  public static final String SoundDirectory = "sound";
  public static final String MoveSoundPath = Constants.SoundDirectory + "/" + "move.wav";
  public static final String CaptureSoundPath = Constants.SoundDirectory + "/" + "capture.wav";
}
