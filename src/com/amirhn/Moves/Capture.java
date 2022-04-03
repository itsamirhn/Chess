package com.amirhn.Moves;

import com.amirhn.Game.Board;
import com.amirhn.Game.Chess;
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
        if (!isValidApplyOnBoard(board)) return false;
        board.removePiece(capturePiece);
        board.removePiece(piece);
        piece.setLocation(destination);
        capturePiece.removeLocation();
        board.setPiece(piece);
        return true;
    }

    @Override
    public boolean undoOnBoard(Board board) {
        if (!isValidUndoOnBoard(board)) return false;
        board.removePiece(capturePiece);
        board.removePiece(piece);
        piece.setLocationBack(source);
        capturePiece.setLocationBack(destination);
        board.setPiece(piece);
        board.setPiece(capturePiece);
        return true;
    }

    @Override
    public boolean isValidApplyOnBoard(Board board) {
        return super.isValidApplyOnBoard(board) && board.isValidPiece(capturePiece) && capturePiece.canBeCapturedBy(piece);
    }

    @Override
    public boolean isValidUndoOnBoard(Board board) {
        return super.isValidUndoOnBoard(board) && board.isValidLocation(source) && !board.isOccupied(source);
    }

    @Override
    public String toString() {
        return "" + piece.type.letter + source + "x" + destination;
    }

    @Override
    public Location getEndpointLocation() {
        return destination;
    }
}
