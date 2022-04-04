package com.amirhn.Pieces;

import com.amirhn.Game.Color;

public enum PieceType {
    KING('K', "♔♚"),
    QUEEN('Q', "♕♛"),
    KNIGHT('N', "♘♞"),
    ROOK('R', "♖♜"),
    BISHOP('B', "♗♝"),
    PAWN('P', "♙♟");

    public final char letter;
    public final String symbols;

    PieceType(char letter, String symbols) {
        this.letter = letter;
        this.symbols = symbols;
    }

    public static PieceType valueOf(char letter) {
        for (PieceType pieceType : PieceType.values()) {
            if (pieceType.letter == Character.toUpperCase(letter)) return pieceType;
        }
        return null;
    }

    public char getSymbol(Color color) {
        if (color == Color.WHITE) return this.symbols.charAt(0);
        if (color == Color.BLACK) return this.symbols.charAt(1);
        return '?';
    }
}