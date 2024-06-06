package com.amirhn.Moves;

import com.amirhn.Game.Board;
import com.amirhn.Game.Location;
import com.amirhn.Pieces.Piece;
import com.amirhn.Pieces.PieceType;

/** The type Promotion. */
public abstract class Promotion extends Move {
  /** The Move. */
  public Move move;

  /** The Promoted piece. */
  public Piece promotedPiece;

  /**
   * Instantiates a new Promotion.
   *
   * @param move the move
   * @param promotedPieceType the promoted piece type
   */
  public Promotion(Move move, PieceType promotedPieceType) {
    super(MoveType.PROMOTION, move.piece);
    this.move = move;
    this.promotedPiece = Piece.generate(promotedPieceType, move.piece.color, null);
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
    move.applyOnBoard(board);
    promotedPiece.setLocation(move.getEndpointLocation());
    board.setPiece(promotedPiece);
    return true;
  }

  /**
   * Undo on board.
   *
   * @param board the board
   */
  @Override
  public void undoOnBoard(Board board) {
    board.removePiece(promotedPiece);
    promotedPiece.removeLocation();
    move.undoOnBoard(board);
  }

  /**
   * Is valid apply on board boolean.
   *
   * @param board the board
   * @return the boolean
   */
  @Override
  public boolean isValidApplyOnBoard(Board board) {
    return super.isValidApplyOnBoard(board) && move.isValidApplyOnBoard(board);
  }

  /**
   * Gets endpoint location.
   *
   * @return the endpoint location
   */
  @Override
  public Location getEndpointLocation() {
    return move.getEndpointLocation();
  }

  /**
   * Gets startpoint location.
   *
   * @return the startpoint location
   */
  @Override
  public Location getStartpointLocation() {
    return move.getStartpointLocation();
  }

  /**
   * To string string.
   *
   * @return the string
   */
  @Override
  public String toString() {
    return move.toString() + "=" + promotedPiece.type.letter;
  }
}
