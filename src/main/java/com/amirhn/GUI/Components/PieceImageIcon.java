package com.amirhn.GUI.Components;

import com.amirhn.GUI.Constants;
import javax.swing.*;

/** The type Piece image icon. */
public class PieceImageIcon extends ImageIcon {
  private static final String DefaultPath = Constants.PieceImageDirectory;

  /**
   * Instantiates a new Piece image icon.
   *
   * @param color the color
   * @param typeLetter the type letter
   */
  public PieceImageIcon(String color, char typeLetter) {
    super(DefaultPath + "/" + color.toLowerCase().charAt(0) + typeLetter + ".png");
  }
}
