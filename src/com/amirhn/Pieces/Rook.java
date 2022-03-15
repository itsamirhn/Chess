package com.amirhn.Pieces;

import com.amirhn.Game.Board;
import com.amirhn.Game.Color;
import com.amirhn.Game.Location;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(Color color, Location location) {
        super(PieceType.ROOK, color, location);
    }

    @Override
    public List<Location> naturalMoves(Board board) {
        List<Location> moves = new ArrayList<>();
        int[] dx = {+1, -1, 0, 0};
        int[] dy = {0, 0, +1, -1};
        for (int i = 0; i < 4; i++) {
            System.out.println("Run");
            Location location = this.getLocation().byOffset(dx[i], dy[i]);
            while (board.isValidLocation(location)) {
                if (board.isOccupied(location)) {
                    if (board.getPiece(location).canBeCapturedBy(this)) moves.add(location);
                    break;
                }
                moves.add(location);
                location = location.byOffset(dx[i], dy[i]);
            }
        }
        return moves;
    }
}
