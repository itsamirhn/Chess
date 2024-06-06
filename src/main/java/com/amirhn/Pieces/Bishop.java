package com.amirhn.Pieces;

import com.amirhn.Game.Board;
import com.amirhn.Game.Color;
import com.amirhn.Game.Location;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Bishop.
 */
public class Bishop extends Piece {

  /**
   * Instantiates a new Bishop.
   *
   * @param color the color
   * @param location the location
   */
public Bishop(Color color, Location location) {
    super(PieceType.BISHOP, color, location);
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
    int[] dx = {+1, +1, -1, -1};
    int[] dy = {+1, -1, +1, -1};
    for (int i = 0; i < 4; i++) {
      Location location = this.getLocation().byOffset(dx[i], dy[i]);
      while (board.isValidLocation(location)) {
        threatenedLocations.add(location);
        if (board.isOccupied(location)) break;
        location = location.byOffset(dx[i], dy[i]);
      }
    }
    return threatenedLocations;
  }
}
