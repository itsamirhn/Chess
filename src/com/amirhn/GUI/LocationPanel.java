package com.amirhn.GUI;

import com.amirhn.Game.Location;
import com.amirhn.Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LocationPanel extends JPanel {

    public static final Dimension Size = new Dimension(100, 100);
    public static final Color SelectedLocationColor = new Color(135, 151, 106);
    public static final Color LightLocationColor = new Color(240, 217, 181);
    public static final Color DarkLocationColor = new Color(181, 136, 99);

    public final Location location;
    public PiecePanel piecePanel;

    public LocationPanel(Location location, LocationListener locationListener) {
        super(new GridBagLayout());
        this.location = location;
        this.setPreferredSize(Size);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                locationListener.locationSelected(location);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.setDefaultBackground();
        this.validate();
    }
    public void setDefaultBackground() {
        this.setBackground(this.location.isLight() ? LightLocationColor : DarkLocationColor);
    }

    public void setSelectedBackground() {
        this.setBackground(SelectedLocationColor);
    }

    public void setPiece(Piece piece) {
        removeAll();
        if (piece == null) {
            this.piecePanel = null;
        } else {
            this.piecePanel = new PiecePanel(piece);
            add(this.piecePanel);
        }
        validate();
        repaint();
    }
}
