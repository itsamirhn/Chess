package com.amirhn.Moves;

import com.amirhn.Game.Board;
import com.amirhn.Game.Location;
import com.amirhn.Pieces.Piece;

public class Capture extends Move {

    public Piece destination;

    public Capture(Piece source, Piece destination) {
        super(source);
        this.destination = destination;
    }

    @Override
    public boolean apply(Board board) {
        if (!board.isValidLocation(this.destination.getLocation())) return false;
        if (!board.isOccupied(this.destination.getLocation())) return false;
        board.removePiece(destination);
        board.removePiece(source);
        this.source.setLocation(destination.getLocation());
        board.setPiece(this.source);
        return true;
    }

    @Override
    public String toString() {
        return "" + this.source.type + this.source.getLocation() + "x" + this.destination;
    }
}
