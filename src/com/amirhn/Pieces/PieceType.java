package com.amirhn.Pieces;

public enum PieceType {
    KING('K'),
    QUEEN('Q'),
    KNIGHT('N'),
    ROOK('R'),
    BISHOP('B'),
    PAWN('P');

    public final char letter;

    PieceType(char letter) {
        this.letter = letter;
    }
}