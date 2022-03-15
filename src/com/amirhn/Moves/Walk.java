package com.amirhn.Moves;

import com.amirhn.Game.Board;
import com.amirhn.Game.Location;
import com.amirhn.Pieces.Piece;

public class Walk extends Move {
    public Location destination;

    public Walk(Piece source, Location destination) {
        super(MoveType.WALK, source);
        this.destination = destination;
    }

    @Override
    public boolean applyOnBoard(Board board) {
        if (!this.isValidOnBoard(board)) return false;
        board.removePiece(source);
        this.source.setLocation(destination);
        board.setPiece(this.source);
        return true;
    }

    @Override
    public boolean isValidOnBoard(Board board) {
        return board.isValidPiece(this.source) && board.isValidLocation(this.destination) && !board.isOccupied(this.destination);
    }

    @Override
    public String toString() {
        return "" + this.source.type + this.source.getLocation() + this.destination;
    }
}
