package com.amirhn.Moves;

import com.amirhn.Game.Board;
import com.amirhn.Pieces.Piece;

public abstract class Move {
    public final MoveType type;
    public Piece source;

    public Move(MoveType type, Piece source) {
        this.type = type;
        this.source = source;
    }

    public abstract boolean applyOnBoard(Board board);
    public abstract boolean isValidOnBoard(Board board);
}
