package com.amirhn.Pieces;

import com.amirhn.Game.Board;
import com.amirhn.Game.Color;
import com.amirhn.Game.Location;
import com.amirhn.Moves.Capture;
import com.amirhn.Moves.Move;
import com.amirhn.Moves.PawnPromotion;
import com.amirhn.Moves.Walk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece {

    public final int direction;

    public Pawn(Color color, Location location) {
        super(PieceType.PAWN, color, location);
        direction = this.color == Color.WHITE ? +1 : -1;
    }

    private boolean isBeforeLastRank(Board board) {
        if (direction > 0) return location.row == board.rows - 2;
        return location.row == 1;
    }

    private List<Move> makeMoves(Board board, Location location) {
        if (!board.isValidLocation(location)) return Collections.emptyList();
        Move move = null;
        if (board.isOccupied(location)) {
            Piece capturingPiece = board.getPiece(location);
            if (capturingPiece.canBeCapturedBy(this)) move = new Capture(this, capturingPiece);
        } else move = new Walk(this, location);
        if (move == null) return Collections.emptyList();
        if (!isBeforeLastRank(board)) return Collections.singletonList(move);
        List<Move> promotions = new ArrayList<>();
        promotions.add(new PawnPromotion(move, PieceType.QUEEN));
        promotions.add(new PawnPromotion(move, PieceType.ROOK));
        promotions.add(new PawnPromotion(move, PieceType.BISHOP));
        promotions.add(new PawnPromotion(move, PieceType.KNIGHT));
        return promotions;
    }

    @Override
    public List<Move> getNaturalMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        for (Location location : this.getThreatenedLocations(board)) {
            if (!board.isOccupied(location)) continue;
            moves.addAll(makeMoves(board, location));
        }
        Location location = getLocation().byOffset(direction, 0);
        moves.addAll(makeMoves(board, location));
        if (!hasMoved()) {
            location = getLocation().byOffset(direction * 2, 0);
            moves.addAll(makeMoves(board, location));
        }
        return moves;
    }

    @Override
    public List<Location> getThreatenedLocations(Board board) {
        List<Location> threatenedLocations = new ArrayList<>();
        int sign = this.color == Color.WHITE ? +1 : -1;
        int[] dx = {sign, sign};
        int[] dy = {+1, -1};
        for (int i = 0; i < 2; i++) {
            Location location = this.getLocation().byOffset(dx[i], dy[i]);
            if (!board.isValidLocation(location)) continue;
            threatenedLocations.add(location);
        }
        return threatenedLocations;
    }
}
