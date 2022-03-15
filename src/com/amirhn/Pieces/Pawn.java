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
        List<Move> moves = new ArrayList<>();
        Location location = this.getLocation().byOffset(sign, 0);
        if (board.isValidLocation(location) && !board.isOccupied(location)) moves.add(new Walk(this, location));
        if (!this.moved) {
            location = this.getLocation().byOffset(sign * 2, 0);
            if (board.isValidLocation(location) && !board.isOccupied(location)) moves.add(new Walk(this, location));
        }
        int[] dx = {sign, sign};
        int[] dy = {+1, -1};
        for (int i = 0; i < 2; i++) {
            location = this.getLocation().byOffset(dx[i], dy[i]);
            if (!board.isValidLocation(location)) continue;
            if (!board.isOccupied(location)) continue;
            Piece capturingPiece = board.getPiece(location);
            if (capturingPiece.canBeCapturedBy(this)) moves.add(new Capture(this, capturingPiece));
        }
        return moves;
    }
}
