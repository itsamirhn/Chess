package com.amirhn.Moves;

import com.amirhn.Game.Board;
import com.amirhn.Game.Location;
import com.amirhn.Pieces.Piece;

public class Walk extends Move {
    public Location destination;

    public Walk(Piece source, Location destination) {
        super(source);
        this.destination = destination;
    }

    @Override
    public boolean apply(Board board) {
        if (!board.isValidLocation(this.destination)) return false;
        if (board.isOccupied(this.destination)) return false;
        board.removePiece(source);
        this.source.setLocation(destination);
        board.setPiece(this.source);
        return true;
    }

    @Override
    public String toString() {
        return "" + this.source.type + this.source.getLocation() + this.destination;
    }
}
