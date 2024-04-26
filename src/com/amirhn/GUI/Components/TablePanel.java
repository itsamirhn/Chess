package com.amirhn.GUI.Components;

import com.amirhn.Game.Location;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends JPanel {

    private final int rows, columns;
    private final LocationPanel[][] locationPanels;

    public TablePanel(int rows, int columns) {
        super(new GridLayout(rows, columns));
        this.rows = rows;
        this.columns = columns;
        this.locationPanels = new LocationPanel[rows][columns];
        for (int i = rows - 1; i >= 0; i--) for (int j = 0; j < columns; j++) {
            this.locationPanels[i][j] = new LocationPanel(Location.valueOf(i, j).isLight());
            this.add(locationPanels[i][j]);
        }
        this.validate();
    }

    public LocationPanel getLocationPanel(Location location) {
        return this.getLocationPanel(location.row, location.column);
    }

    public LocationPanel getLocationPanel(int i, int j) {
        return this.locationPanels[i][j];
    }

    public void resetLocationStates() {
        for (int i = 0; i < rows; i++) for (int j = 0; j < columns; j++) {
            LocationPanel locationPanel = this.getLocationPanel(i, j);
            locationPanel.setState(LocationState.NORMAL);
        }
    }

    public Point pointOf(Location location) {
        return new Point(location.column * LocationPanel.Size.width, (rows - location.row - 1) * LocationPanel.Size.height);
    }

    public Location locationOf(Point point) {
        return Location.valueOf(rows - 1 - point.y / LocationPanel.Size.height, point.x / LocationPanel.Size.width);
    }
}
