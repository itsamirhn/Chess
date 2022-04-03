package com.amirhn.Moves;

import com.amirhn.Game.Board;
import com.amirhn.Game.Chess;
import com.amirhn.Game.Location;
import com.amirhn.Pieces.King;
import com.amirhn.Pieces.Rook;


public abstract class Castling extends Move {
    public Walk kingMove, rookMove;
    public King king;
    public Rook rook;

    public Castling(Walk kingMove, Walk rookMove) {
        super(MoveType.CASTLING, kingMove.piece);
        this.kingMove = kingMove;
        this.rookMove = rookMove;
        this.king = (King) kingMove.piece;
        this.rook = (Rook) rookMove.piece;
    }

    @Override
    public boolean isAllowed(Chess chess) {
        if (!super.isAllowed(chess)) return false;
        if (king.hasMoved() || rook.hasMoved()) return false;
        int from = Math.min(king.getLocation().column, rook.getLocation().column) + 1;
        int to = Math.max(king.getLocation().column, rook.getLocation().column) - 1;
        for (int i = from; i <= to; i++) if (chess.getBoard().isOccupied(Location.valueOf(king.getLocation().row, i))) return false;
        from = Math.min(kingMove.source.column, kingMove.destination.column) + 1;
        to = Math.max(kingMove.source.column, kingMove.destination.column);
        for (int i = from; i <= to; i++) if (chess.getTurnPlayer().isThreatening(chess.getBoard(), Location.valueOf(king.getLocation().row, i))) return false;
        return true;
    }

    @Override
    public boolean applyOnBoard(Board board) {
        if (!isValidApplyOnBoard(board)) return false;
        kingMove.applyOnBoard(board);
        rookMove.applyOnBoard(board);
        return true;
    }

    @Override
    public boolean undoOnBoard(Board board) {
        if (!isValidUndoOnBoard(board)) return false;
        rookMove.undoOnBoard(board);
        kingMove.undoOnBoard(board);
        return true;
    }

    @Override
    public boolean isValidApplyOnBoard(Board board) {
        return super.isValidApplyOnBoard(board) &&
                king.color.equals(rook.color) &&
                king.getLocation().row == rook.getLocation().row &&
                kingMove.isValidApplyOnBoard(board) && rookMove.isValidApplyOnBoard(board);
    }

    @Override
    public boolean isValidUndoOnBoard(Board board) {
        return super.isValidUndoOnBoard(board) && kingMove.isValidUndoOnBoard(board) && rookMove.isValidUndoOnBoard(board);
    }
}
