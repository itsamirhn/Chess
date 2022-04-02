package com.amirhn.Players;

import com.amirhn.Game.Board;
import com.amirhn.Game.Chess;
import com.amirhn.Game.Color;
import com.amirhn.Game.Location;
import com.amirhn.Moves.Move;
import com.amirhn.Pieces.King;
import com.amirhn.Pieces.Piece;
import com.amirhn.Pieces.PieceType;

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

    public List<Move> getAllowedMoves(Chess chess) {
        List<Move> moves = new ArrayList<>();
        for (Piece piece: this.activePieces) moves.addAll(piece.getAllowedMoves(chess));
        return moves;
    }

    public List<Location> getThreatenedLocations(Board board) {
        List<Location> threatenedLocations = new ArrayList<>();
        for (Piece piece: this.activePieces) threatenedLocations.addAll(piece.getThreatenedLocations(board));
        return threatenedLocations;
    }

    public King getKing() {
        for (Piece piece : this.activePieces) if (piece.type == PieceType.KING) return (King) piece;
        return null;
    }

    public boolean isThreatening(Board board, Piece piece) {
        for (Location location: this.getThreatenedLocations(board)) if (location == piece.getLocation()) return true;
        return false;
    }
}
