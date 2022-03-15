package com.amirhn.Pieces;

import com.amirhn.Game.Board;
import com.amirhn.Game.Color;
import com.amirhn.Game.Location;
import com.amirhn.Moves.Capture;
import com.amirhn.Moves.Move;
import com.amirhn.Moves.Walk;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(Color color, Location location) {
        super(PieceType.KNIGHT, color, location);
    }

    @Override
    public List<Move> getNaturalMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        int[] dx = {+1, +1, -1, -1, +2, +2, -2, -2};
        int[] dy = {+2, -2, +2, -2, +1, -1, +1, -1};
        for (int i = 0; i < 8; i++) {
            Location location = this.getLocation().byOffset(dx[i], dy[i]);
            if (!board.isValidLocation(location)) continue;
            if (board.isOccupied(location)) {
                Piece capturingPiece = board.getPiece(location);
                if (capturingPiece.canBeCapturedBy(this)) moves.add(new Capture(this, capturingPiece));
            } else moves.add(new Walk(this, location));
        }
        return moves;
    }
}
