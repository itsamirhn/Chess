package com.amirhn.GUI.Components;

import javax.swing.*;

public class PieceImageIcon extends ImageIcon {
    private static final String DefaultPath = "png/";
    public PieceImageIcon(String color, char typeLetter) {
        super(DefaultPath+ color.toLowerCase().charAt(0) + typeLetter + ".png");
    }
}
