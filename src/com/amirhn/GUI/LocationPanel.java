package com.amirhn.GUI;

import com.amirhn.Game.Location;
import com.amirhn.Pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class LocationPanel extends JPanel {

    public static final Color LightLocationColor = new Color(255, 206, 155);
    public static final Color DarkLocationColor = new Color(209, 139, 71);

    private final BoardPanel boardPanel;
    private final Location location;
    public LocationPanel(BoardPanel boardPanel, Location location) {
        super(new GridBagLayout());
        this.boardPanel = boardPanel;
        this.location = location;
        this.setPreferredSize(new Dimension(100, 100));
        this.setBackground(this.location.isLight() ? LightLocationColor : DarkLocationColor);
        this.validate();
    }
    public void showPiece(Piece piece) {
        this.removeAll();
        this.add(new JLabel(new PieceImageIcon(piece)));
    }
}
