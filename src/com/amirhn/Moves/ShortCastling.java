package com.amirhn.Moves;

import com.amirhn.Game.Location;
import com.amirhn.Pieces.King;
import com.amirhn.Pieces.Rook;

public class ShortCastling extends Castling {

    public ShortCastling(King king, Rook rook) {
        super(new Walk(king, Location.valueOf(king.getLocation().row, 6)), new Walk(rook, Location.valueOf(rook.getLocation().row, 5)));
    }

    @Override
    public String toString() {
        return "O-O";
    }
}
