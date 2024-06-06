package com.amirhn.Moves;

import com.amirhn.Game.Board;
import com.amirhn.Game.Location;
import com.amirhn.Pieces.Piece;

/** The type Walk. */
public class Walk extends Move {
  /** The Source. */
  public Location source;

  /** The Destination. */
  public Location destination;

  /**
   * Instantiates a new Walk.
   *
   * @param sourcePiece the source piece
   * @param destination the destination
   */
  public Walk(Piece sourcePiece, Location destination) {
    super(MoveType.WALK, sourcePiece);
    this.source = sourcePiece.getLocation();
    this.destination = destination;
  }

  /**
   * Apply on board boolean.
   *
   * @param board the board
   * @return the boolean
   */
  @Override
  public boolean applyOnBoard(Board board) {
    if (!this.isValidApplyOnBoard(board)) return false;
    board.removePiece(this.piece);
    this.piece.setLocation(this.destination);
    board.setPiece(this.piece);
    return true;
  }

  /**
   * Undo on board.
   *
   * @param board the board
   */
  @Override
  public void undoOnBoard(Board board) {
    board.removePiece(this.piece);
    this.piece.setLocationBack(this.source);
    board.setPiece(this.piece);
  }

  /**
   * Is valid apply on board boolean.
   *
   * @param board the board
   * @return the boolean
   */
  @Override
  public boolean isValidApplyOnBoard(Board board) {
    return super.isValidApplyOnBoard(board)
        && board.isValidLocation(this.destination)
        && !board.isOccupied(this.destination);
  }

  /**
   * Gets startpoint location.
   *
   * @return the startpoint location
   */
  @Override
  public Location getStartpointLocation() {
    return source;
  }

  /**
   * Gets endpoint location.
   *
   * @return the endpoint location
   */
  @Override
  public Location getEndpointLocation() {
    return destination;
  }

  /**
   * To string string.
   *
   * @return the string
   */
  @Override
  public String toString() {
    return "" + this.piece.type.letter + this.source + "-" + this.destination;
  }
}
