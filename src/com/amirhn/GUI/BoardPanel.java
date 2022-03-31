package com.amirhn.GUI;

import com.amirhn.Game.Board;
import com.amirhn.Game.Location;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    public final Board board;
    public final LocationPanel[][] locationPanels;
    private Location selectedLocation;

    public Location getSelectedLocation() {
        return selectedLocation;
    }

    public void setSelectedLocation(Location selectedLocation) {
        if (this.selectedLocation != null) this.getLocationPanel(this.selectedLocation).setDefaultBackground();
        this.selectedLocation = selectedLocation;
        this.getLocationPanel(this.selectedLocation).setSelectedBackground();
    }

    public LocationPanel getLocationPanel(Location location) {
        return this.locationPanels[location.row][location.column];
    }

    public BoardPanel(Board board) {
        super(new GridLayout(board.rows, board.columns));
        this.locationPanels = new LocationPanel[board.rows][board.columns];
        for (int i = board.rows - 1; i >= 0; i--) for (int j = 0; j < board.columns; j++) {
            Location location = Location.valueOf(i, j);
            this.locationPanels[i][j] = new LocationPanel(this, location);
            if (board.isOccupied(location)) this.locationPanels[i][j].setPiece(board.getPiece(location));
            this.add(locationPanels[i][j]);
        }
        this.board = board;
        this.validate();
    }

    public void clicked(Location location) {
        this.setSelectedLocation(location);
    }
}
