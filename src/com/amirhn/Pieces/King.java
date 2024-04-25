package com.amirhn.Pieces;

import com.amirhn.Game.Board;
import com.amirhn.Game.Color;
import com.amirhn.Game.Location;
import com.amirhn.Moves.Castling;
import com.amirhn.Moves.Move;

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
    public List<Location> getThreatenedLocations(Board board) {
        List<Location> threatenedLocations = new ArrayList<>();
        int[] dx = {+1, +1, +1, -1, -1, -1, 0, 0};
        int[] dy = {+1, -1, 0, +1, -1, 0, +1, -1};
        for (int i = 0; i < 8; i++) {
            Location location = this.getLocation().byOffset(dx[i], dy[i]);
            if (!board.isValidLocation(location)) continue;
            threatenedLocations.add(location);
        }
        return threatenedLocations;
    }

    @Override
    public List<Move> getNaturalMoves(Board board) {
        List<Move> moves = super.getNaturalMoves(board);
        int[] dy = {+1, -1};
        for (int i = 0; i < 2; i++) {
            Location location = this.getLocation().byOffset(0, dy[i]);
            while (board.isValidLocation(location) && !board.isOccupied(location)) location = location.byOffset(0, dy[i]);
            if (!board.isValidLocation(location) || !board.isOccupied(location)) continue;
            Piece piece = board.getPiece(location);
            if (piece.type.equals(PieceType.ROOK) && piece.color.equals(this.color))
                moves.add(Castling.generate(this, (Rook) piece));
        }
        return moves;
    }
}
