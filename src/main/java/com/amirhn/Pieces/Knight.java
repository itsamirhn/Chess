package com.amirhn.Pieces;

import com.amirhn.Game.Board;
import com.amirhn.Game.Color;
import com.amirhn.Game.Location;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Knight.
 */
public class Knight extends Piece {

  /**
   * Instantiates a new Knight.
   *
   * @param color the color
   * @param location the location
   */
public Knight(Color color, Location location) {
    super(PieceType.KNIGHT, color, location);
  }

  /**
   * Gets threatened locations.
   *
   * @param board the board
   * @return the threatened locations
   */
@Override
  public List<Location> getThreatenedLocations(Board board) {
    List<Location> threatenedLocations = new ArrayList<>();
    int[] dx = {+1, +1, -1, -1, +2, +2, -2, -2};
    int[] dy = {+2, -2, +2, -2, +1, -1, +1, -1};
    for (int i = 0; i < 8; i++) {
      Location location = this.getLocation().byOffset(dx[i], dy[i]);
      if (!board.isValidLocation(location)) continue;
      threatenedLocations.add(location);
    }
    return threatenedLocations;
  }
}
