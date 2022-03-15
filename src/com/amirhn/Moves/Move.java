package com.amirhn.Moves;

import com.amirhn.Game.Board;
import com.amirhn.Pieces.Piece;

public abstract class Move {
    public Piece source;

    public Move(Piece source) {
        this.source = source;
    }

    public abstract boolean apply(Board board);
}
