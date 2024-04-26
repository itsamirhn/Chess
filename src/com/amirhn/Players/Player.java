package com.amirhn.Players;

import com.amirhn.Game.Board;
import com.amirhn.Game.Chess;
import com.amirhn.Game.Color;
import com.amirhn.Game.Location;
import com.amirhn.Moves.Move;
import com.amirhn.Pieces.King;
import com.amirhn.Pieces.Piece;
import com.amirhn.Pieces.PieceType;
import com.amirhn.Pieces.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private final Color color;
    public List<Piece> capturedPieces = new ArrayList<>();

    public boolean hadLongCastling = false;
    public boolean hadShortCastling = false;

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
        return getNaturalMoves(chess.getBoard()).stream().filter(move -> move.isAllowed(chess)).collect(Collectors.toList());
    }

    public List<Location> getThreatenedLocations(Board board) {
        List<Location> threatenedLocations = new ArrayList<>();
        for (Piece piece: getActivePieces(board)) threatenedLocations.addAll(piece.getThreatenedLocations(board));
        return threatenedLocations;
    }

    public List<Piece> getPieces(Board board, PieceType pieceType) {
        List<Piece> pieces = new ArrayList<>();
        for (Piece piece : getActivePieces(board)) if (piece.type == pieceType) pieces.add(piece);
        return pieces;
    }

    public King getKing(Board board) {
        return (King) getPieces(board, PieceType.KING).getFirst();
    }

    public List<Rook> getRooks(Board board) {
        return getPieces(board, PieceType.ROOK).stream().map(piece -> (Rook) piece).toList();
    }

    public Rook getKingSideRook(Board board) {
        return getRooks(board).stream().filter(rook -> rook.getLocation().column == 7).findFirst().orElse(null);
    }

    public Rook getQueenSideRook(Board board) {
        return getRooks(board).stream().filter(rook -> rook.getLocation().column == 0).findFirst().orElse(null);
    }

    public boolean isThreatening(Board board, Location location) {
        for (Location threat: getThreatenedLocations(board)) if (threat.equals(location)) return true;
        return false;
    }

}
