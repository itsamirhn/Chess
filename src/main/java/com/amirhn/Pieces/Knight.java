package com.amirhn.Pieces;

import com.amirhn.GameTest.Board;
import com.amirhn.GameTest.Color;
import com.amirhn.GameTest.Location;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

  public Knight(Color color, Location location) {
    super(PieceType.KNIGHT, color, location);
  }

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
