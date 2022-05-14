package com.amirhn.GUI.Components;

import com.amirhn.Pieces.Piece;

import javax.swing.*;

public class PieceImageIcon extends ImageIcon {
    private static final String DefaultPath = "png/";
    public PieceImageIcon(Piece piece) {
        super(DefaultPath+ piece.color.toString().toLowerCase().charAt(0) + piece.type.letter + ".png");
    }
}
