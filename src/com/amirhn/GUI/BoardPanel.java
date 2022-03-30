package com.amirhn.GUI;

import com.amirhn.Game.Board;
import com.amirhn.Game.Location;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    private final Board board;
    public final LocationPanel[][] locationPanels;

    public BoardPanel(Board board) {
        super(new GridLayout(board.rows, board.columns));
        this.locationPanels = new LocationPanel[board.rows][board.columns];
        for (int i = 0; i < board.rows; i++) for (int j = 0; j < board.columns; j++) {
            Location location = Location.valueOf(i, j);
            this.locationPanels[i][j] = new LocationPanel(this, location);
            if (board.isOccupied(location)) this.locationPanels[i][j].showPiece(board.getPiece(location));
            this.add(locationPanels[i][j]);
        }
        this.board = board;
        this.validate();
    }
}
