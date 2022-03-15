package com.amirhn.Players;

import com.amirhn.Game.Board;
import com.amirhn.Game.Color;
import com.amirhn.Moves.Move;
import com.amirhn.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private final Color color;
    public List<Piece> activePieces = new ArrayList<>();

    public Player(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public List<Move> getNaturalMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        for (Piece piece: this.activePieces) moves.addAll(piece.getNaturalMoves(board));
        return moves;
    }
}
