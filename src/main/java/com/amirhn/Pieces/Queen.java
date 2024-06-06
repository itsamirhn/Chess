package com.amirhn.Pieces;

import com.amirhn.Game.Board;
import com.amirhn.Game.Color;
import com.amirhn.Game.Location;
import java.util.List;
import java.util.stream.Stream;

/**
 * The type Queen.
 */
public class Queen extends Piece {

  private final Rook rook;
  private final Bishop bishop;

  /**
   * Instantiates a new Queen.
   *
   * @param color the color
   * @param location the location
   */
public Queen(Color color, Location location) {
    super(PieceType.QUEEN, color, location);
    this.rook = new Rook(color, location);
    this.bishop = new Bishop(color, location);
  }

  /**
   * Sets location.
   *
   * @param location the location
   */
@Override
  public void setLocation(Location location) {
    super.setLocation(location);
    this.rook.setLocation(location);
    this.bishop.setLocation(location);
  }

  /**
   * Sets location back.
   *
   * @param location the location
   */
@Override
  public void setLocationBack(Location location) {
    super.setLocationBack(location);
    this.rook.setLocationBack(location);
    this.bishop.setLocationBack(location);
  }

  /**
   * Remove location.
   */
@Override
  public void removeLocation() {
    super.removeLocation();
  }

  /**
   * Gets threatened locations.
   *
   * @param board the board
   * @return the threatened locations
   */
@Override
  public List<Location> getThreatenedLocations(Board board) {
    List<Location> rookThreatenedLocation = this.rook.getThreatenedLocations(board);
    List<Location> bishopThreatenedLocation = this.bishop.getThreatenedLocations(board);
    return Stream.concat(rookThreatenedLocation.stream(), bishopThreatenedLocation.stream())
        .toList();
  }
}
