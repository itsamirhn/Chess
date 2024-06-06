package com.amirhn.Moves;

import com.amirhn.Game.Board;
import com.amirhn.Game.Location;
import com.amirhn.Pieces.Piece;

/** The type Capture. */
public class Capture extends Move {

  /** The Source. */
  public Location source;

  /** The Destination. */
  public Location destination;

  /** The Capture piece. */
  public Piece capturePiece;

  /**
   * Instantiates a new Capture.
   *
   * @param piece the piece
   * @param capturePiece the capture piece
   */
  public Capture(Piece piece, Piece capturePiece) {
    super(MoveType.CAPTURE, piece);
    this.capturePiece = capturePiece;
    this.source = piece.getLocation();
    this.destination = capturePiece.getLocation();
  }

  /**
   * Apply on board boolean.
   *
   * @param board the board
   * @return the boolean
   */
  @Override
  public boolean applyOnBoard(Board board) {
    if (!isValidApplyOnBoard(board)) return false;
    board.removePiece(capturePiece);
    board.removePiece(piece);
    piece.setLocation(destination);
    capturePiece.removeLocation();
    board.setPiece(piece);
    return true;
  }

  /**
   * Undo on board.
   *
   * @param board the board
   */
  @Override
  public void undoOnBoard(Board board) {
    board.removePiece(capturePiece);
    board.removePiece(piece);
    piece.setLocationBack(source);
    capturePiece.setLocationBack(destination);
    board.setPiece(piece);
    board.setPiece(capturePiece);
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
        && board.isValidPiece(capturePiece)
        && capturePiece.canBeCapturedBy(piece);
  }

  /**
   * To string string.
   *
   * @return the string
   */
  @Override
  public String toString() {
    return "" + piece.type.letter + source + "x" + destination;
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
   * Gets startpoint location.
   *
   * @return the startpoint location
   */
  @Override
  public Location getStartpointLocation() {
    return source;
  }
}
