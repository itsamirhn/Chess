package com.amirhn.Moves;

import com.amirhn.Game.Board;
import com.amirhn.Game.Chess;
import com.amirhn.Game.Location;
import com.amirhn.Pieces.Piece;

public class Walk extends Move {
    public Location source;
    public Location destination;

    public Walk(Piece sourcePiece, Location destination) {
        super(MoveType.WALK, sourcePiece);
        this.source = sourcePiece.getLocation();
        this.destination = destination;
    }

    @Override
    public boolean applyOnBoard(Board board) {
        if (!this.isValidApplyOnBoard(board)) return false;
        board.removePiece(this.piece);
        this.piece.setLocation(this.destination);
        board.setPiece(this.piece);
        return true;
    }

    @Override
    public boolean undoOnBoard(Board board) {
        if (!this.isValidUndoOnBoard(board)) return false;
        board.removePiece(this.piece);
        this.piece.setLocation(this.source);
        board.setPiece(this.piece);
        return true;
    }

    @Override
    public boolean isValidApplyOnBoard(Board board) {
        return board.isValidPiece(this.piece) && board.isValidLocation(this.destination) && !board.isOccupied(this.destination);
    }

    @Override
    public boolean isValidUndoOnBoard(Board board) {
        return board.isValidPiece(this.piece) && board.isValidLocation(this.source) && !board.isOccupied(this.source);
    }

    @Override
    public String toString() {
        return "" + this.piece.type.letter + this.source + "-" + this.destination;
    }
}
