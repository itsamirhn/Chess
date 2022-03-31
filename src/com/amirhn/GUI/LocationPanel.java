package com.amirhn.GUI;

import com.amirhn.Game.Location;
import com.amirhn.Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LocationPanel extends JPanel implements MouseListener {

    public static final Dimension Size = new Dimension(100, 100);
    public static final Color SelectedLocationColor = new Color(135, 151, 106);
    public static final Color LightLocationColor = new Color(240, 217, 181);
    public static final Color DarkLocationColor = new Color(181, 136, 99);

    public final BoardPanel boardPanel;
    public final Location location;
    public PiecePanel piecePanel;

    public LocationPanel(BoardPanel boardPanel, Location location) {
        super(new GridBagLayout());
        this.boardPanel = boardPanel;
        this.location = location;
        this.setPreferredSize(Size);
        this.addMouseListener(this);
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
        this.removeAll();
        this.piecePanel = new PiecePanel(this, piece);
        this.add(this.piecePanel);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.boardPanel.clicked(this.location);
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
}
