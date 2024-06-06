package com.amirhn.Moves;

import com.amirhn.Game.Board;
import com.amirhn.Game.Chess;
import com.amirhn.Game.Location;
import com.amirhn.Pieces.Piece;

/**
 * The type Move.
 */
public abstract class Move {
  /**
   * The Type.
   */
public final MoveType type;
  /**
   * The Piece.
   */
public Piece piece;

  /**
   * Instantiates a new Move.
   *
   * @param type the type
   * @param piece the piece
   */
public Move(MoveType type, Piece piece) {
    this.type = type;
    this.piece = piece;
  }

  /**
   * Is allowed boolean.
   *
   * @param chess the chess
   * @return the boolean
   */
public boolean isAllowed(Chess chess) {
    Board board = chess.getBoard();
    if (!piece.isAllowedToMove(chess)) return false;
    if (!applyOnBoard(board)) return false;
    boolean isInCheck = chess.isInCheck();
    undoOnBoard(board);
    return !isInCheck;
  }

  /**
   * Apply on board boolean.
   *
   * @param board the board
   * @return the boolean
   */
public abstract boolean applyOnBoard(Board board);

  /**
   * Undo on board.
   *
   * @param board the board
   */
protected abstract void undoOnBoard(Board board);

  /**
   * Is valid apply on board boolean.
   *
   * @param board the board
   * @return the boolean
   */
public boolean isValidApplyOnBoard(Board board) {
    return board.isValidPiece(piece);
  }

  /**
   * Gets startpoint location.
   *
   * @return the startpoint location
   */
public abstract Location getStartpointLocation();

  /**
   * Gets endpoint location.
   *
   * @return the endpoint location
   */
public abstract Location getEndpointLocation();
}
