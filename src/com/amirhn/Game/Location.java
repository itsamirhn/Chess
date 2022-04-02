package com.amirhn.Game;

import java.util.Objects;

public class Location {
    public final int row, column;

    public Location(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Location valueOf(String loc) {
        return new Location(loc.charAt(1) - '1', loc.charAt(0) - 'a');
    }

    public static Location valueOf(int row, int column) {
        return new Location(row, column);
    }

    public Location byOffset(int dx, int dy) {
        return Location.valueOf(this.row + dx, this.column + dy);
    }

    public boolean isLight() {
        return ((this.row + this.column) % 2) == 1;
    }

    @Override
    public String toString() {
        return ((char) (this.column + 'a')) + String.valueOf(this.row + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return row == location.row && column == location.column && hashCode() == location.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
