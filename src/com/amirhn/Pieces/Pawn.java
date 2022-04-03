package com.amirhn.Pieces;

import com.amirhn.Game.Board;
import com.amirhn.Game.Color;
import com.amirhn.Game.Location;
import com.amirhn.Moves.Capture;
import com.amirhn.Moves.Move;
import com.amirhn.Moves.Walk;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Color color, Location location) {
        super(PieceType.PAWN, color, location);
    }

    @Override
    public List<Move> getNaturalMoves(Board board) {
        int sign = this.color == Color.WHITE ? +1 : -1;
        List<Move> moves = new ArrayList<>();
        for (Location location : this.getThreatenedLocations(board)) {
            if (!board.isOccupied(location)) continue;
            Piece capturingPiece = board.getPiece(location);
            if (capturingPiece.canBeCapturedBy(this)) moves.add(new Capture(this, capturingPiece));
        }
        Location location = getLocation().byOffset(sign, 0);
        if (board.isValidLocation(location) && !board.isOccupied(location)) moves.add(new Walk(this, location));
        if (!hasMoved()) {
            location = getLocation().byOffset(sign * 2, 0);
            if (board.isValidLocation(location) && !board.isOccupied(location)) moves.add(new Walk(this, location));
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
