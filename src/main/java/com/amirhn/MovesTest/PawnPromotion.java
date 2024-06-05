package com.amirhn.MovesTest;

import com.amirhn.GameTest.Board;
import com.amirhn.GameTest.Chess;
import com.amirhn.GameTest.Color;
import com.amirhn.Pieces.PieceType;

public class PawnPromotion extends Promotion {

  public PawnPromotion(Move move, PieceType promotedPieceType) {
    super(move, promotedPieceType);
  }

  @Override
  public boolean isValidApplyOnBoard(Board board) {
    return piece.type == PieceType.PAWN && super.isValidApplyOnBoard(board);
  }

  @Override
  public boolean isAllowed(Chess chess) {
    boolean isLastRank = false;
    if (piece.color == Color.WHITE)
      isLastRank = move.getEndpointLocation().row == chess.getBoard().rows - 1;
    if (piece.color == Color.BLACK) isLastRank = move.getEndpointLocation().row == 0;
    return super.isAllowed(chess) && isLastRank;
  }
}
