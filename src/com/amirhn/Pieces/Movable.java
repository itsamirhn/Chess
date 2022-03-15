package com.amirhn.Pieces;

import com.amirhn.Game.Board;
import com.amirhn.Game.Location;

import java.util.List;

public interface Movable {
    public List<Location> naturalMoves(Board board);
}
