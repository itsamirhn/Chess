package com.amirhn.GUI;

import com.amirhn.Game.Location;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends JPanel {

    private final int rows, columns;
    private final LocationPanel[][] locationPanels;

    public TablePanel(int rows, int columns, LocationListener locationListener) {
        super(new GridLayout(rows, columns));
        this.rows = rows;
        this.columns = columns;
        this.locationPanels = new LocationPanel[rows][columns];
        for (int i = rows - 1; i >= 0; i--) for (int j = 0; j < columns; j++) {
            Location location = Location.valueOf(i, j);
            this.locationPanels[i][j] = new LocationPanel(location, locationListener);
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

    public void setState(Location location, LocationPanel.State state) {
        this.getLocationPanel(location).setState(state);
    }

    public void setState(int i, int j, LocationPanel.State state) {
        this.getLocationPanel(i, j).setState(state);
    }

    public void resetStates(LocationPanel.State state) {
        for (int i = 0; i < rows; i++) for (int j = 0; j < columns; j++) {
            LocationPanel locationPanel = this.getLocationPanel(i, j);
            if (locationPanel.getState() == state) locationPanel.setState(LocationPanel.State.NORMAL);
        }
    }

    public void resetStates() {
        for (int i = 0; i < rows; i++) for (int j = 0; j < columns; j++) this.getLocationPanel(i, j).setState(LocationPanel.State.NORMAL);
    }

    public Point pointOf(Location location) {
        return new Point(location.column * LocationPanel.Size.width, (rows - location.row - 1) * LocationPanel.Size.height);
    }

    public Location locationOf(Point point) {
        return Location.valueOf(rows - 1 - point.y / LocationPanel.Size.height, point.x / LocationPanel.Size.width);
    }
}
