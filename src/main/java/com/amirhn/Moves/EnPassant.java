package com.amirhn.Moves;

import com.amirhn.Game.Board;
import com.amirhn.Game.Chess;
import com.amirhn.Game.Location;
import com.amirhn.Pieces.Pawn;

/** The type En passant. */
public class EnPassant extends Capture {

  /** The Second source. */
  public Location secondSource;

  /** The Second destination. */
  public Location secondDestination;

  /** The Push pawn move. */
  public Walk pushPawnMove;

  /**
   * Instantiates a new En passant.
   *
   * @param pawn the pawn
   * @param capturePawn the capture pawn
   */
  public EnPassant(Pawn pawn, Pawn capturePawn) {
    super(pawn, capturePawn);
    this.secondSource = capturePawn.getLocation();
    this.secondDestination = capturePawn.getLocation().byOffset(-1 * capturePawn.direction, 0);
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
    super.applyOnBoard(board);
    pushPawnMove = new Walk(piece, secondDestination);
    return pushPawnMove.applyOnBoard(board);
  }

  /**
   * Undo on board.
   *
   * @param board the board
   */
  @Override
  public void undoOnBoard(Board board) {
    pushPawnMove.undoOnBoard(board);
    super.undoOnBoard(board);
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
        && source.row == destination.row
        && Math.abs(source.column - destination.column) == 1
        && board.isValidLocation(secondDestination)
        && !board.isOccupied(secondDestination);
  }

  /**
   * Is allowed boolean.
   *
   * @param chess the chess
   * @return the boolean
   */
  @Override
  public boolean isAllowed(Chess chess) {
    if (chess.moves.isEmpty()) return false;
    Move lastMove = chess.moves.getLast();
    if (!(lastMove instanceof Walk)) return false;
    if (!lastMove.piece.equals(capturePiece)) return false;
    return super.isAllowed(chess);
  }

  /**
   * To string string.
   *
   * @return the string
   */
  @Override
  public String toString() {
    return "" + piece.type.letter + source + "x" + secondDestination + " " + "e.p.";
  }

  /**
   * Gets endpoint location.
   *
   * @return the endpoint location
   */
  @Override
  public Location getEndpointLocation() {
    return secondDestination;
  }
}
