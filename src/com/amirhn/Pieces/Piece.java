package com.amirhn.Pieces;

import com.amirhn.Game.Color;
import com.amirhn.Game.Location;
import com.amirhn.Moves.Movable;

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
    public String toString() {
        return "Piece{" +
                "type=" + type +
                ", color=" + color +
                ", location=" + location +
                '}';
    }

    public boolean canBeCapturedBy(Piece piece) {
        if (piece == null) return false;
        return this.color != piece.color;
    }
}
