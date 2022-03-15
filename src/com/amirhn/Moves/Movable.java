package com.amirhn.Moves;

import com.amirhn.Game.Board;

import java.util.List;

public interface Movable {
    List<Move> getNaturalMoves(Board board);
}
