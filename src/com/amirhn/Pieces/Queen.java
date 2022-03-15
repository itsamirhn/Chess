package com.amirhn.Pieces;

import com.amirhn.Game.Board;
import com.amirhn.Game.Color;
import com.amirhn.Game.Location;
import com.amirhn.Moves.Move;

import java.util.List;
import java.util.stream.Stream;

public class Queen extends Piece {

    private final Rook rook;
    private final Bishop bishop;

    public Queen(Color color, Location location) {
        super(PieceType.QUEEN, color, location);
        this.rook = new Rook(color, location);
        this.bishop = new Bishop(color, location);
    }

    @Override
    public void setLocation(Location location) {
        super.setLocation(location);
        this.rook.setLocation(location);
        this.bishop.setLocation(location);
    }

    @Override
    public List<Move> getNaturalMoves(Board board) {
        List<Move> rookMoves = this.rook.getNaturalMoves(board).stream().peek(move -> move.source = this).toList();
        List<Move> bishopWalks = this.bishop.getNaturalMoves(board).stream().peek(move -> move.source = this).toList();
        return Stream.concat(rookMoves.stream(), bishopWalks.stream()).toList();
    }
}
