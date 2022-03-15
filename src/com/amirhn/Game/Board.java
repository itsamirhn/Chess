package com.amirhn.Game;

import com.amirhn.Pieces.Piece;

import java.util.HashMap;
import java.util.Map;

public class Board {
    public int appliedMovesCount = 0;
    public final int rows, columns;
    public final Map<Location, Piece> pieceByLocation = new HashMap<>();
    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public boolean isValidLocation(Location location) {
        if (location == null) return false;
        return 0 <= location.row && location.row < this.rows && 0 <= location.column && location.column < this.columns;
    }
    public boolean isOccupied(Location location) {
        if (!this.isValidLocation(location)) return false;
        return getPiece(location) != null;
    }

    public Piece getPiece(Location location) {
        return this.pieceByLocation.get(location);
    }
    public void setPiece(Piece piece) {
        this.pieceByLocation.put(piece.getLocation(), piece);
    }
    public void removePiece(Piece piece) {
        this.pieceByLocation.remove(piece.getLocation());
    }

    public boolean isValidPiece(Piece piece) {
        return this.getPiece(piece.getLocation()) == piece;
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        for (int i = 0; i < this.rows; i++) {
            board.append(i + 1).append('\t');
            for (int j = 0; j < this.columns; j++) {
                if (this.isOccupied(Location.valueOf(i, j))) board.append(this.getPiece(Location.valueOf(i, j)).type);
                else board.append('_');
                board.append(' ');
            }
            board.append('\n');
        }
        board.append("\n \t");
        for (int j = 0; j < this.columns; j++) {
            board.append((char) ('a' + j)).append(' ');
        }
        board.append('\n');
        return board.toString();
    }
}
