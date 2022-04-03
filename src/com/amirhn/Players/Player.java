package com.amirhn.Players;

import com.amirhn.Game.Board;
import com.amirhn.Game.Chess;
import com.amirhn.Game.Color;
import com.amirhn.Game.Location;
import com.amirhn.Moves.Castling;
import com.amirhn.Moves.Move;
import com.amirhn.Pieces.King;
import com.amirhn.Pieces.Piece;
import com.amirhn.Pieces.PieceType;
import com.amirhn.Pieces.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Player {
    private final Color color;
    public List<Piece> capturedPieces = new ArrayList<>();

    public Castling castling = null;

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
        if (castling == null) {
            King king = getKing(board);
            if (king.hasMoved()) return moves;
            List<Rook> rooks = getRooks(board);
            for (Rook rook : rooks) if (!rook.hasMoved() && rook.getLocation().row == king.getLocation().row)
                moves.add(Castling.generate(king, rook));
        }
        return moves;
    }

    public List<Move> getAllowedMoves(Chess chess) {
        return getNaturalMoves(chess.getBoard()).stream().filter(move -> move.isAllowed(chess)).collect(Collectors.toList());
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

    public List<Rook> getRooks(Board board) {
        List<Rook> rooks = new ArrayList<>();
        for (Piece piece : this.getActivePieces(board)) if (piece.type == PieceType.ROOK) rooks.add((Rook) piece);
        return rooks;
    }

    public boolean isThreatening(Board board, Location location) {
        for (Location threat: this.getThreatenedLocations(board)) if (threat.equals(location)) return true;
        return false;
    }
}
