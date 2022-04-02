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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Player {
    private final Color color;
    public List<Piece> capturedPieces = new ArrayList<>();

    public Player(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public List<Piece> getActivePieces(Board board) {
        return board.getAllPieces().stream().filter(piece -> piece.color.equals(color)).collect(Collectors.toList());
    }

    public List<Move> getNaturalMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        for (Piece piece: this.getActivePieces(board)) moves.addAll(piece.getNaturalMoves(board));
        return moves;
    }

    public List<Move> getAllowedMoves(Chess chess) {
        List<Move> moves = new ArrayList<>();
        for (Piece piece: this.getActivePieces(chess.getBoard())) moves.addAll(piece.getAllowedMoves(chess));
        return moves;
    }

    public List<Location> getThreatenedLocations(Board board) {
        List<Location> threatenedLocations = new ArrayList<>();
        for (Piece piece: this.getActivePieces(board)) threatenedLocations.addAll(piece.getThreatenedLocations(board));
        return threatenedLocations;
    }

    public King getKing(Board board) {
        for (Piece piece : this.getActivePieces(board)) if (piece.type == PieceType.KING) return (King) piece;
        return null;
    }

    public boolean isThreatening(Board board, Location location) {
        for (Location threat: this.getThreatenedLocations(board)) if (threat.equals(location)) return true;
        return false;
    }
}
