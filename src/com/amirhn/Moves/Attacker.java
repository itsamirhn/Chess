package com.amirhn.Moves;

import com.amirhn.Game.Board;
import com.amirhn.Game.Location;

import java.util.List;

public interface Attacker {
    List<Location> getThreatenedLocations(Board board);
}
