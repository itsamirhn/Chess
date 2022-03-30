package com.amirhn.Pieces;

import com.amirhn.Game.Color;
import com.amirhn.Game.Location;
import com.amirhn.Moves.Movable;

import java.util.Objects;

public abstract class Piece implements Movable {

    public final PieceType type;
    public final Color color;
    private Location location;

    public Piece(PieceType type, Color color, Location location) {
        this.type = type;
        this.color = color;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return type == piece.type && color == piece.color && Objects.equals(location, piece.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, color, location);
    }

    @Override
    public String toString() {
        return this.type + "{" +
                "color=" + color +
                ", location=" + location +
                '}';
    }

    public boolean canBeCapturedBy(Piece piece) {
        if (piece == null) return false;
        return this.color != piece.color;
    }
}
