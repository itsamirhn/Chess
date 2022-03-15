package com.amirhn.Game;

import com.amirhn.Game.Location;

import java.util.ArrayList;

public class Path {
    private ArrayList<Location> locations;

    public Path() {
        this.locations = new ArrayList<Location>();
    }

    public void addLocation(Location location) {
        this.locations.add(location);
    }
    public ArrayList<Location> getLocations() {
        return this.locations;
    }
}
