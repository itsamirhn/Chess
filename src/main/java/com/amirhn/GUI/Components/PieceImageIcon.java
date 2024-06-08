package com.amirhn.GUI.Components;

import com.amirhn.GUI.Constants;
import javax.swing.*;
import java.util.Objects;

/** The type Piece image icon. */
public class PieceImageIcon extends ImageIcon {
  /**
   * Instantiates a new Piece image icon.
   *
   * @param color the color
   * @param typeLetter the type letter
   */
  public PieceImageIcon(String color, char typeLetter) {
    super(Objects.requireNonNull(PieceImageIcon.class.getResource(Constants.PieceImageDirectory + "/" + color.toLowerCase().charAt(0) + typeLetter + ".png")));
  }
}
