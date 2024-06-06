package com.amirhn.Pieces;

import com.amirhn.Game.Color;

/**
 * The enum Piece type.
 */
public enum PieceType {
  /**
   *King piece type.
   */
KING('K', "♔♚"),
  /**
   *Queen piece type.
   */
QUEEN('Q', "♕♛"),
  /**
   *Knight piece type.
   */
KNIGHT('N', "♘♞"),
  /**
   *Rook piece type.
   */
ROOK('R', "♖♜"),
  /**
   *Bishop piece type.
   */
BISHOP('B', "♗♝"),
  /**
   *Pawn piece type.
   */
PAWN('P', "♙♟");

  /**
   * The Letter.
   */
public final char letter;
  /**
   * The Symbols.
   */
public final String symbols;

  PieceType(char letter, String symbols) {
    this.letter = letter;
    this.symbols = symbols;
  }

  /**
   * Value of piece type.
   *
   * @param letter the letter
   * @return the piece type
   */
public static PieceType valueOf(char letter) {
    for (PieceType pieceType : PieceType.values()) {
      if (pieceType.letter == Character.toUpperCase(letter)) return pieceType;
    }
    return null;
  }

  /**
   * Gets symbol.
   *
   * @param color the color
   * @return the symbol
   */
public char getSymbol(Color color) {
    if (color == Color.WHITE) return this.symbols.charAt(0);
    if (color == Color.BLACK) return this.symbols.charAt(1);
    return '?';
  }
}
