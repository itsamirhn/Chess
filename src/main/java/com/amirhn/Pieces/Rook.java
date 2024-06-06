package com.amirhn.Pieces;

import com.amirhn.Game.Board;
import com.amirhn.Game.Color;
import com.amirhn.Game.Location;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Rook.
 */
public class Rook extends Piece {

  /**
   * Instantiates a new Rook.
   *
   * @param color the color
   * @param location the location
   */
public Rook(Color color, Location location) {
    super(PieceType.ROOK, color, location);
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
    int[] dx = {+1, -1, 0, 0};
    int[] dy = {0, 0, +1, -1};
    for (int i = 0; i < 4; i++) {
      Location location = this.getLocation().byOffset(dx[i], dy[i]);
      while (board.isValidLocation(location)) {
        if (board.isOccupied(location)) {
          threatenedLocations.add(location);
          break;
        } else threatenedLocations.add(location);
        location = location.byOffset(dx[i], dy[i]);
      }
    }
    return threatenedLocations;
  }
}
