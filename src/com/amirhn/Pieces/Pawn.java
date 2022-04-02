package com.amirhn.Pieces;

import com.amirhn.Game.Board;
import com.amirhn.Game.Color;
import com.amirhn.Game.Location;
import com.amirhn.Moves.Move;
import com.amirhn.Moves.Walk;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private boolean moved = false;
    public Pawn(Color color, Location location) {
        super(PieceType.PAWN, color, location);
    }

    @Override
    public void setLocation(Location location) {
        super.setLocation(location);
        this.moved = true;
    }

    @Override
    public List<Move> getNaturalMoves(Board board) {
        int sign = this.color == Color.WHITE ? +1 : -1;
        List<Move> moves = super.getNaturalMoves(board);
        Location location = this.getLocation().byOffset(sign, 0);
        if (board.isValidLocation(location) && !board.isOccupied(location)) moves.add(new Walk(this, location));
        if (!this.moved) {
            location = this.getLocation().byOffset(sign * 2, 0);
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
