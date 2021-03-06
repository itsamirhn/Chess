package com.amirhn.Moves;

import com.amirhn.Game.Board;
import com.amirhn.Game.Chess;
import com.amirhn.Game.Location;
import com.amirhn.Pieces.Piece;

public abstract class Move {
    public final MoveType type;
    public Piece piece;

    public Move(MoveType type, Piece piece) {
        this.type = type;
        this.piece = piece;
    }

    public boolean isAllowed(Chess chess) {
        Board board = chess.getBoard();
        if (!piece.isAllowedToMove(chess)) return false;
        if (!applyOnBoard(board)) return false;
        boolean isInCheck = chess.isInCheck();
        undoOnBoard(board);
        return !isInCheck;
    }
    public abstract boolean applyOnBoard(Board board);
    protected abstract void undoOnBoard(Board board);
    public boolean isValidApplyOnBoard(Board board) {
        return board.isValidPiece(piece);
    }
    public abstract Location getStartpointLocation();
    public abstract Location getEndpointLocation();
}
