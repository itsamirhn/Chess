package com.amirhn.Pieces;

import com.amirhn.Game.Board;
import com.amirhn.Game.Chess;
import com.amirhn.Game.Color;
import com.amirhn.Game.Location;
import com.amirhn.Moves.Capture;
import com.amirhn.Moves.Move;
import com.amirhn.Moves.Walk;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Piece {

    public final PieceType type;
    public final Color color;
    protected Location location;
    protected int moves = 0;

    public Piece(PieceType type, Color color, Location location) {
        this.type = type;
        this.color = color;
        this.location = location;
    }

    public static Piece generate(PieceType pieceType, Color color, Location location) {
        return switch (pieceType) {
            case KING -> new King(color, location);
            case PAWN -> new Pawn(color, location);
            case ROOK -> new Rook(color, location);
            case QUEEN -> new Queen(color, location);
            case BISHOP -> new Bishop(color, location);
            case KNIGHT -> new Knight(color, location);
        };
    }

    public static Piece generate(char fen, Location location) {
        return generate(Objects.requireNonNull(PieceType.valueOf(fen)), Color.valueOfPieceChar(fen), location);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        if (this.location != null) moves++;
        this.location = location;
    }

    public void setLocationBack(Location location) {
        if (this.location != null) moves--;
        this.location = location;
    }

    public void removeLocation() {
        this.location = null;
    }

    public boolean hasMoved() {
        return moves > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return type.equals(piece.type) && color.equals(piece.color) && location.equals(piece.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, color, location);
    }

    @Override
    public String toString() {
        return this.type + "{" +
                "color=" + color +
                ", location=" + location +
                '}';
    }

    public boolean isAllowedToMove(Chess chess) {
        return chess.turn.equals(this.color);
    }

    public List<Move> getAllowedMoves(Chess chess) {
        return this.getNaturalMoves(chess.getBoard()).stream().filter(move -> move.isAllowed(chess)).collect(Collectors.toList());
    }

    public boolean canBeCapturedBy(Piece piece) {
        if (piece == null) return false;
        if (type == PieceType.KING) return false;
        return this.color != piece.color;
    }

    public char getSymbol() {
        return this.type.getSymbol(this.color);
    }

    public abstract List<Location> getThreatenedLocations(Board board);

    public List<Move> getNaturalMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        for (Location location : this.getThreatenedLocations(board)) {
            if (board.isOccupied(location)) {
                Piece capturingPiece = board.getPiece(location);
                if (capturingPiece.canBeCapturedBy(this)) moves.add(new Capture(this, capturingPiece));
            } else moves.add(new Walk(this, location));
        }
        return moves;
    }

    public Piece copy() {
        return generate(this.type, this.color, this.location);
    }
}
