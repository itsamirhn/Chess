package com.amirhn.Moves;

import com.amirhn.Game.Board;
import com.amirhn.Pieces.Piece;

public class Capture extends Move {

    public Piece destination;

    public Capture(Piece source, Piece destination) {
        super(MoveType.CAPTURE, source);
        this.destination = destination;
    }

    @Override
    public boolean applyOnBoard(Board board) {
        if (!this.isValidOnBoard(board)) return false;
        board.removePiece(destination);
        board.removePiece(source);
        this.source.setLocation(destination.getLocation());
        board.setPiece(this.source);
        return true;
    }

    @Override
    public boolean isValidOnBoard(Board board) {
        return board.isValidPiece(this.destination) && board.isValidPiece(this.source);
    }

    @Override
    public String toString() {
        return "" + this.source.type.letter + "x" + this.destination.getLocation();
    }
}
