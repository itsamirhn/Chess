package com.amirhn.Moves;

import com.amirhn.Game.Location;
import com.amirhn.Pieces.Piece;

import java.util.List;

public interface MoveController {
    boolean isAllowedToMove(Piece piece);
    List<Move> getAllowedMoves(Piece piece);
    Move makeMove(Piece piece, Location endpoint);
    boolean applyMove(Move move);
}
