package com.amirhn.Pieces;

import com.amirhn.Game.Board;
import com.amirhn.Game.Color;
import com.amirhn.Game.Location;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(Color color, Location location) {
        super(PieceType.BISHOP, color, location);
    }

    @Override
    public List<Location> getThreatenedLocations(Board board) {
        List<Location> threatenedLocations = new ArrayList<>();
        int[] dx = {+1, +1, -1, -1};
        int[] dy = {+1, -1, +1, -1};
        for (int i = 0; i < 4; i++) {
            Location location = this.getLocation().byOffset(dx[i], dy[i]);
            while (board.isValidLocation(location) ) {
                threatenedLocations.add(location);
                if (board.isOccupied(location)) break;
                location = location.byOffset(dx[i], dy[i]);
            }
        }
        return threatenedLocations;
    }
}
