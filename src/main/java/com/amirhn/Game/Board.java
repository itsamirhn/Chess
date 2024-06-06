package com.amirhn.Game;

import com.amirhn.Pieces.Piece;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** The type Board. */
public class Board {
  /** The Rows. */
  public final int rows,
      /** The Columns. */
      columns;

  /** The Piece by location. */
  public final Map<Location, Piece> pieceByLocation = new HashMap<>();

  /**
   * Instantiates a new Board.
   *
   * @param rows the rows
   * @param columns the columns
   */
  public Board(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
  }

  /**
   * From fen board.
   *
   * @param fen the fen
   * @return the board
   */
  public static Board fromFEN(String fen) {
    int rows = fen.split("/").length;
    Board board = new Board(rows, rows);
    board.setupFEN(fen);
    return board;
  }

  /**
   * Sets fen.
   *
   * @param fen the fen
   */
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

  /**
   * Is valid location boolean.
   *
   * @param location the location
   * @return the boolean
   */
  public boolean isValidLocation(Location location) {
    if (location == null) return false;
    return 0 <= location.row
        && location.row < this.rows
        && 0 <= location.column
        && location.column < this.columns;
  }

  /**
   * Is occupied boolean.
   *
   * @param location the location
   * @return the boolean
   */
  public boolean isOccupied(Location location) {
    if (!this.isValidLocation(location)) return false;
    return getPiece(location) != null;
  }

  /**
   * Gets piece.
   *
   * @param location the location
   * @return the piece
   */
  public Piece getPiece(Location location) {
    return this.pieceByLocation.get(location);
  }

  /**
   * Sets piece.
   *
   * @param piece the piece
   */
  public void setPiece(Piece piece) {
    this.pieceByLocation.put(piece.getLocation(), piece);
  }

  /**
   * Remove piece.
   *
   * @param piece the piece
   */
  public void removePiece(Piece piece) {
    this.pieceByLocation.remove(piece.getLocation());
  }

  /**
   * Gets all pieces.
   *
   * @return the all pieces
   */
  public List<Piece> getAllPieces() {
    return pieceByLocation.values().stream().toList();
  }

  /**
   * Is valid piece boolean.
   *
   * @param piece the piece
   * @return the boolean
   */
  public boolean isValidPiece(Piece piece) {
    return this.getPiece(piece.getLocation()) == piece;
  }

  /**
   * To string string.
   *
   * @return the string
   */
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

  /**
   * Copy board.
   *
   * @return the board
   */
  public Board copy() {
    Board board = new Board(this.rows, this.columns);
    for (Piece piece : this.getAllPieces()) board.setPiece(piece.copy());
    return board;
  }

  /**
   * Equals boolean.
   *
   * @param o the o
   * @return the boolean
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Board board = (Board) o;
    return this.toString().equals(board.toString());
  }
}
