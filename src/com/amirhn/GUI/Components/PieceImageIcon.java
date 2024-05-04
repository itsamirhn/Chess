package com.amirhn.GUI.Components;

import com.amirhn.GUI.Constants;
import javax.swing.*;

public class PieceImageIcon extends ImageIcon {
  private static final String DefaultPath = Constants.PieceImageDirectory;

  public PieceImageIcon(String color, char typeLetter) {
    super(DefaultPath + "/" + color.toLowerCase().charAt(0) + typeLetter + ".png");
  }
}
