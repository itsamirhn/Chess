package com.amirhn.Pieces;

import com.amirhn.Game.Board;
import com.amirhn.Game.Color;
import com.amirhn.Game.Location;
import com.amirhn.Moves.Capture;
import com.amirhn.Moves.Move;
import com.amirhn.Moves.Walk;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(Color color, Location location) {
        super(PieceType.ROOK, color, location);
    }

    @Override
    public List<Move> getNaturalMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        int[] dx = {+1, -1, 0, 0};
        int[] dy = {0, 0, +1, -1};
        for (int i = 0; i < 4; i++) {
            Location location = this.getLocation().byOffset(dx[i], dy[i]);
            while (board.isValidLocation(location) ) {
                if (board.isOccupied(location)) {
                    Piece capturingPiece = board.getPiece(location);
                    if (capturingPiece.canBeCapturedBy(this)) moves.add(new Capture(this, capturingPiece));
                    break;
                } else moves.add(new Walk(this, location));
                location = location.byOffset(dx[i], dy[i]);
            }
        }
        return moves;
    }
}
