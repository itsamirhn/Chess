package com.amirhn.Moves;

import com.amirhn.Game.Location;
import com.amirhn.Pieces.King;
import com.amirhn.Pieces.Rook;

/** The type Long castling. */
public class LongCastling extends Castling {

  /**
   * Instantiates a new Long castling.
   *
   * @param king the king
   * @param rook the rook
   */
  public LongCastling(King king, Rook rook) {
    super(
        new Walk(king, Location.valueOf(king.getLocation().row, 2)),
        new Walk(rook, Location.valueOf(rook.getLocation().row, 3)));
  }

  /**
   * To string string.
   *
   * @return the string
   */
  @Override
  public String toString() {
    return "O-O-O";
  }
}
