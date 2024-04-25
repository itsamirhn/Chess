package com.amirhn.Moves;

import com.amirhn.Game.Board;
import com.amirhn.Game.Chess;

import java.util.List;
import java.util.stream.Collectors;

public interface Movable {
    boolean isAllowedToMove(Chess chess);
    List<Move> getNaturalMoves(Board board);
    default List<Move> getAllowedMoves(Chess chess) {
        return this.getNaturalMoves(chess.getBoard()).stream().filter(move -> move.isAllowed(chess)).collect(Collectors.toList());
    }
}
