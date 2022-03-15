package com.amirhn.Pieces;

import com.amirhn.Game.Board;
import com.amirhn.Game.Color;
import com.amirhn.Game.Location;

import java.util.List;
import java.util.stream.Collectors;

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
        if (color == Color.WHITE) return this.type.toString();
        else return this.type.toString().toLowerCase();
    }

    public boolean canBeCapturedBy(Piece piece) {
        if (piece == null) return false;
        if (this.color == piece.color) return false;
        return true;
    }
}
