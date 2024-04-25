package com.amirhn.Moves;

import com.amirhn.Game.Board;
import com.amirhn.Game.Location;
import com.amirhn.Pieces.Piece;
import com.amirhn.Pieces.PieceType;


public abstract class Promotion extends Move {
    public Move move;
    public Piece promotedPiece;

    public Promotion(Move move, PieceType promotedPieceType) {
        super(MoveType.PROMOTION, move.piece);
        this.move = move;
        this.promotedPiece = Piece.generate(promotedPieceType, move.piece.color, null);
    }

    @Override
    public boolean applyOnBoard(Board board) {
        if (!isValidApplyOnBoard(board)) return false;
        move.applyOnBoard(board);
        promotedPiece.setLocation(move.getEndpointLocation());
        board.setPiece(promotedPiece);
        return true;
    }

    @Override
    public void undoOnBoard(Board board) {
        board.removePiece(promotedPiece);
        promotedPiece.removeLocation();
        move.undoOnBoard(board);
    }

    @Override
    public boolean isValidApplyOnBoard(Board board) {
        return super.isValidApplyOnBoard(board) && move.isValidApplyOnBoard(board);
    }

    @Override
    public Location getEndpointLocation() {
        return move.getEndpointLocation();
    }

    @Override
    public Location getStartpointLocation() {
        return move.getStartpointLocation();
    }

    @Override
    public String toString() {
        return move.toString() + "=" + promotedPiece.type.letter;
    }
}
