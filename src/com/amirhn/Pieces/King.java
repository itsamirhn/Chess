package com.amirhn.Pieces;

import com.amirhn.Game.Board;
import com.amirhn.Game.Color;
import com.amirhn.Game.Location;
import com.amirhn.Moves.Capture;
import com.amirhn.Moves.Move;
import com.amirhn.Moves.Walk;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(Color color, Location location) {
        super(PieceType.KING, color, location);
    }

    @Override
    public boolean canBeCapturedBy(Piece piece) {
        return false;
    }

    @Override
    public List<Move> getNaturalMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        int[] dx = {+1, +1, +1, -1, -1, -1, 0, 0};
        int[] dy = {+1, -1, 0, +1, -1, 0, +1, -1};
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
