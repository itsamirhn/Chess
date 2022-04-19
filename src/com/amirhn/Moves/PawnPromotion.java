package com.amirhn.Moves;

import com.amirhn.Game.Board;
import com.amirhn.Game.Chess;
import com.amirhn.Game.Color;
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
        if (piece.color == Color.WHITE) isLastRank = move.getEndpointLocation().row == chess.getBoard().rows - 1;
        if (piece.color == Color.BLACK) isLastRank = move.getEndpointLocation().row == 0;
        return super.isAllowed(chess) && isLastRank;
    }
}
