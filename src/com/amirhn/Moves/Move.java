package com.amirhn.Moves;

import com.amirhn.Game.Board;
import com.amirhn.Pieces.Piece;

public abstract class Move {
    public final MoveType type;
    public Piece piece;

    public Move(MoveType type, Piece piece) {
        this.type = type;
        this.piece = piece;
    }

    public abstract boolean applyOnBoard(Board board);
    public abstract boolean undoOnBoard(Board board);
    public abstract boolean isValidApplyOnBoard(Board board);
    public abstract boolean isValidUndoOnBoard(Board board);
}
