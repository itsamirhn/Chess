package com.amirhn.Moves;

import com.amirhn.Game.Board;
import com.amirhn.Game.Chess;
import com.amirhn.Game.Location;
import com.amirhn.Pieces.King;
import com.amirhn.Pieces.Rook;

/** The type Castling. */
public abstract class Castling extends Move {
  /** The King move. */
  public Walk kingMove,
      /** The Rook move. */
      rookMove;

  /** The King. */
  public King king;

  /** The Rook. */
  public Rook rook;

  /**
   * Instantiates a new Castling.
   *
   * @param kingMove the king move
   * @param rookMove the rook move
   */
  public Castling(Walk kingMove, Walk rookMove) {
    super(MoveType.CASTLING, kingMove.piece);
    this.kingMove = kingMove;
    this.rookMove = rookMove;
    this.king = (King) kingMove.piece;
    this.rook = (Rook) rookMove.piece;
  }

  /**
   * Generate castling.
   *
   * @param king the king
   * @param rook the rook
   * @return the castling
   */
  public static Castling generate(King king, Rook rook) {
    if (king.getLocation().row != rook.getLocation().row) return null;
    if (king.getLocation().column < rook.getLocation().column) return new ShortCastling(king, rook);
    if (king.getLocation().column > rook.getLocation().column) return new LongCastling(king, rook);
    return null;
  }

  /**
   * Is allowed boolean.
   *
   * @param chess the chess
   * @return the boolean
   */
  @Override
  public boolean isAllowed(Chess chess) {
    if (!super.isAllowed(chess)) return false;
    if (king.hasMoved() || rook.hasMoved()) return false;
    if (chess.getTurnPlayer().hadShortCastling || chess.getTurnPlayer().hadLongCastling)
      return false;
    int from = Math.min(king.getLocation().column, rook.getLocation().column) + 1;
    int to = Math.max(king.getLocation().column, rook.getLocation().column) - 1;
    for (int i = from; i <= to; i++)
      if (chess.getBoard().isOccupied(Location.valueOf(king.getLocation().row, i))) return false;
    from = Math.min(kingMove.source.column, kingMove.destination.column) + 1;
    to = Math.max(kingMove.source.column, kingMove.destination.column);
    for (int i = from; i <= to; i++)
      if (chess
          .getOpponentPlayer()
          .isThreatening(chess.getBoard(), Location.valueOf(king.getLocation().row, i)))
        return false;
    return true;
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
    kingMove.applyOnBoard(board);
    rookMove.applyOnBoard(board);
    return true;
  }

  /**
   * Undo on board.
   *
   * @param board the board
   */
  @Override
  public void undoOnBoard(Board board) {
    rookMove.undoOnBoard(board);
    kingMove.undoOnBoard(board);
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
        && king.color.equals(rook.color)
        && king.getLocation().row == rook.getLocation().row
        && kingMove.isValidApplyOnBoard(board)
        && rookMove.isValidApplyOnBoard(board);
  }

  /**
   * Gets endpoint location.
   *
   * @return the endpoint location
   */
  @Override
  public Location getEndpointLocation() {
    return rookMove.getStartpointLocation();
  }

  /**
   * Gets startpoint location.
   *
   * @return the startpoint location
   */
  @Override
  public Location getStartpointLocation() {
    return kingMove.getStartpointLocation();
  }
}
