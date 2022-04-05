package com.amirhn.GUI;

import com.amirhn.Game.Board;
import com.amirhn.Game.Location;
import com.amirhn.Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoardPanel extends JPanel {

    public final Board board;
    public final LocationPanel[][] locationPanels;

    public LocationPanel getLocationPanel(Location location) {
        return this.locationPanels[location.row][location.column];
    }

    public BoardPanel(Board board) {
        super(new GridLayout(board.rows, board.columns));

        this.board = board;
        this.locationPanels = new LocationPanel[board.rows][board.columns];
        for (int i = board.rows - 1; i >= 0; i--) for (int j = 0; j < board.columns; j++) {
            Location location = Location.valueOf(i, j);
            this.locationPanels[i][j] = new LocationPanel(location);
            this.add(locationPanels[i][j]);
        }
        this.update();
        this.validate();
    }

    public void update() {
        for (int i = 0; i < board.rows; i++) for (int j = 0; j < board.columns; j++) {
            if (board.isOccupied(Location.valueOf(i, j))) getLocationPanel(Location.valueOf(i, j)).setPiece(board.getPiece(Location.valueOf(i, j)));
            else getLocationPanel(Location.valueOf(i, j)).setPiece(null);
        }
        validate();
    }

    public void locationSelected(Location location) {
        // ....
    }

    public class LocationPanel extends JPanel implements MouseListener {

        public static final Dimension Size = new Dimension(100, 100);
        public static final Color SelectedLocationColor = new Color(135, 151, 106);
        public static final Color LightLocationColor = new Color(240, 217, 181);
        public static final Color DarkLocationColor = new Color(181, 136, 99);

        public final Location location;
        public PiecePanel piecePanel;

        public LocationPanel(Location location) {
            super(new GridBagLayout());
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

        @Override
        public void mouseClicked(MouseEvent e) {
            locationSelected(this.location);
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
}
