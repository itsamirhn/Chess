package com.amirhn.Moves;

import com.amirhn.Game.Location;
import com.amirhn.Pieces.King;
import com.amirhn.Pieces.Rook;

/**
 * The type Short castling.
 */
public class ShortCastling extends Castling {

  /**
   * Instantiates a new Short castling.
   *
   * @param king the king
   * @param rook the rook
   */
public ShortCastling(King king, Rook rook) {
    super(
        new Walk(king, Location.valueOf(king.getLocation().row, 6)),
        new Walk(rook, Location.valueOf(rook.getLocation().row, 5)));
  }

  /**
   * To string string.
   *
   * @return the string
   */
@Override
  public String toString() {
    return "O-O";
  }
}
