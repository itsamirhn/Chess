package com.amirhn.Pieces;

import com.amirhn.GameTest.Board;
import com.amirhn.GameTest.Color;
import com.amirhn.GameTest.Location;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

  public Rook(Color color, Location location) {
    super(PieceType.ROOK, color, location);
  }

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
