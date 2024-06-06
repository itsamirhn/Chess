package com.amirhn.Pieces;

import com.amirhn.Game.Board;
import com.amirhn.Game.Color;
import com.amirhn.Game.Location;
import java.util.List;
import java.util.stream.Stream;

public class Queen extends Piece {

  private final Rook rook;
  private final Bishop bishop;

  public Queen(Color color, Location location) {
    super(PieceType.QUEEN, color, location);
    this.rook = new Rook(color, location);
    this.bishop = new Bishop(color, location);
  }

  @Override
  public void setLocation(Location location) {
    super.setLocation(location);
    this.rook.setLocation(location);
    this.bishop.setLocation(location);
  }

  @Override
  public void setLocationBack(Location location) {
    super.setLocationBack(location);
    this.rook.setLocationBack(location);
    this.bishop.setLocationBack(location);
  }

  @Override
  public void removeLocation() {
    super.removeLocation();
  }

  @Override
  public List<Location> getThreatenedLocations(Board board) {
    List<Location> rookThreatenedLocation = this.rook.getThreatenedLocations(board);
    List<Location> bishopThreatenedLocation = this.bishop.getThreatenedLocations(board);
    return Stream.concat(rookThreatenedLocation.stream(), bishopThreatenedLocation.stream())
        .toList();
  }
}
