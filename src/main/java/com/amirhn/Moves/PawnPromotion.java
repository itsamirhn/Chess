package com.amirhn.Moves;

import com.amirhn.Game.Board;
import com.amirhn.Game.Chess;
import com.amirhn.Game.Color;
import com.amirhn.Pieces.PieceType;

/**
 * The type Pawn promotion.
 */
public class PawnPromotion extends Promotion {

  /**
   * Instantiates a new Pawn promotion.
   *
   * @param move the move
   * @param promotedPieceType the promoted piece type
   */
public PawnPromotion(Move move, PieceType promotedPieceType) {
    super(move, promotedPieceType);
  }

  /**
   * Is valid apply on board boolean.
   *
   * @param board the board
   * @return the boolean
   */
@Override
  public boolean isValidApplyOnBoard(Board board) {
    return piece.type == PieceType.PAWN && super.isValidApplyOnBoard(board);
  }

  /**
   * Is allowed boolean.
   *
   * @param chess the chess
   * @return the boolean
   */
@Override
  public boolean isAllowed(Chess chess) {
    boolean isLastRank = false;
    if (piece.color == Color.WHITE)
      isLastRank = move.getEndpointLocation().row == chess.getBoard().rows - 1;
    if (piece.color == Color.BLACK) isLastRank = move.getEndpointLocation().row == 0;
    return super.isAllowed(chess) && isLastRank;
  }
}
