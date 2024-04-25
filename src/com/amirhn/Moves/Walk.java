package com.amirhn.Moves;

import com.amirhn.Game.Board;
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
    public void undoOnBoard(Board board) {
        board.removePiece(this.piece);
        this.piece.setLocationBack(this.source);
        board.setPiece(this.piece);
    }

    @Override
    public boolean isValidApplyOnBoard(Board board) {
        return super.isValidApplyOnBoard(board) && board.isValidLocation(this.destination) && !board.isOccupied(this.destination);
    }

    @Override
    public Location getStartpointLocation() {
        return source;
    }

    @Override
    public Location getEndpointLocation() {
        return destination;
    }

    @Override
    public String toString() {
        return "" + this.piece.type.letter + this.source + "-" + this.destination;
    }
}
