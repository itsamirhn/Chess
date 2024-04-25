package com.amirhn.Game;

import com.amirhn.Pieces.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    public final int rows, columns;
    public final Map<Location, Piece> pieceByLocation = new HashMap<>();
    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public static Board fromFEN(String fen) {
        int rows = fen.split("/").length;
        Board board = new Board(rows, rows);
        board.setupFEN(fen);
        return board;
    }

    public void setupFEN(String fen) {
        pieceByLocation.clear();
        String[] rows = fen.split("/");
        int i = rows.length - 1;
        for (String row : rows) {
            int j = 0;
            for (char c : row.toCharArray()) {
                if (Character.isDigit(c)) {
                    j += c - '0';
                } else {
                    setPiece(Piece.generate(c, Location.valueOf(i, j)));
                    j++;
                }
            }
            i--;
        }
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

    public List<Piece> getAllPieces() {
        return pieceByLocation.values().stream().toList();
    }
    
    public boolean isValidPiece(Piece piece) {
        return this.getPiece(piece.getLocation()) == piece;
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        board.append(" \t");
        for (int j = 0; j < this.columns; j++) board.append((char) ('a' + j)).append(' ');
        for (int i = 0; i < this.rows; i++) {
            board.append('\n').append(i + 1).append('\t');
            for (int j = 0; j < this.columns; j++) {
                if (!this.isOccupied(Location.valueOf(i, j))) board.append('_');
                else board.append(this.getPiece(Location.valueOf(i, j)).getSymbol());
                board.append(' ');
            }
        }
        return board.toString();
    }

    public Board copy() {
        Board board = new Board(this.rows, this.columns);
        for (Piece piece : this.getAllPieces()) board.setPiece(piece.copy());
        return board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return this.toString().equals(board.toString());
    }
}
