package com.amirhn.Moves;

import com.amirhn.Game.Board;
import com.amirhn.Game.Chess;
import com.amirhn.Game.Location;
import com.amirhn.Pieces.Pawn;
import com.amirhn.Pieces.PieceType;

public class EnPassant extends Capture {

    public Location secondSource;
    public Location secondDestination;
    public Walk pushPawnMove;

    public EnPassant(Pawn pawn, Pawn capturePawn) {
        super(pawn, capturePawn);
        this.secondSource = capturePawn.getLocation();
        this.secondDestination = capturePawn.getLocation().byOffset(-1 * capturePawn.direction, 0);
    }

    @Override
    public boolean applyOnBoard(Board board) {
        if (!isValidApplyOnBoard(board)) return false;
        super.applyOnBoard(board);
        pushPawnMove = new Walk(piece, secondDestination);
        return pushPawnMove.applyOnBoard(board);
    }

    @Override
     public void undoOnBoard(Board board) {
        pushPawnMove.undoOnBoard(board);
        super.undoOnBoard(board);
    }

    @Override
    public boolean isValidApplyOnBoard(Board board) {
        return super.isValidApplyOnBoard(board) &&
                source.row == destination.row &&
                Math.abs(source.column - destination.column) == 1 &&
                board.isValidLocation(secondDestination) &&
                !board.isOccupied(secondDestination);
    }

    @Override
    public boolean isAllowed(Chess chess) {
        if (chess.moves.isEmpty()) return false;
        Move lastMove = chess.moves.getLast();
        if (!(lastMove instanceof Walk)) return false;
        if (!lastMove.piece.equals(capturePiece)) return false;
        return super.isAllowed(chess);
    }

    @Override
    public String toString() {
        return "" + piece.type.letter + source + "x" + secondDestination + " " + "e.p.";
    }

    @Override
    public Location getEndpointLocation() {
        return secondDestination;
    }

}
