package com.amirhn.GUI;

import com.amirhn.Pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class PiecePanel extends JPanel {

    public final Piece piece;
    public JLabel label;

    public PiecePanel(Piece piece) {
        super(new BorderLayout());
        this.setOpaque(false);
        this.piece = piece;
        this.label = new JLabel(new PieceImageIcon(piece));
        this.add(this.label, BorderLayout.CENTER);
        this.validate();
    }
}
