package com.amirhn.Moves;

import com.amirhn.Game.Location;
import com.amirhn.Pieces.King;
import com.amirhn.Pieces.Rook;

public class LongCastling extends Castling {

  public LongCastling(King king, Rook rook) {
    super(
        new Walk(king, Location.valueOf(king.getLocation().row, 2)),
        new Walk(rook, Location.valueOf(rook.getLocation().row, 3)));
  }

  @Override
  public String toString() {
    return "O-O-O";
  }
}
