package com.amirhn.Moves;

import com.amirhn.Game.Board;
import com.amirhn.Game.Location;
import com.amirhn.Pieces.Piece;

public class Capture extends Move {

    public Location source;
    public Location destination;
    public Piece capturePiece;

    public Capture(Piece piece, Piece capturePiece) {
        super(MoveType.CAPTURE, piece);
        this.capturePiece = capturePiece;
        this.source = piece.getLocation();
        this.destination = capturePiece.getLocation();
    }

    @Override
    public boolean applyOnBoard(Board board) {
        if (!this.isValidApplyOnBoard(board)) return false;
        board.removePiece(this.capturePiece);
        board.removePiece(this.piece);
        this.piece.setLocation(this.destination);
        board.setPiece(this.piece);
        return true;
    }

    @Override
    public boolean undoOnBoard(Board board) {
        if (!this.isValidUndoOnBoard(board)) return false;
        board.removePiece(this.capturePiece);
        board.removePiece(this.piece);
        this.piece.setLocation(this.source);
        this.capturePiece.setLocation(this.destination);
        board.setPiece(this.piece);
        board.setPiece(this.capturePiece);
        return true;
    }

    @Override
    public boolean isValidApplyOnBoard(Board board) {
        return board.isValidPiece(this.piece) && board.isValidPiece(this.capturePiece);
    }

    @Override
    public boolean isValidUndoOnBoard(Board board) {
        return board.isValidLocation(this.source) && !board.isOccupied(this.source) && board.isValidPiece(this.piece);
    }

    @Override
    public String toString() {
        return "" + this.piece.type.letter + this.source + "x" + this.destination;
    }
}
