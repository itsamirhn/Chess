package com.amirhn.Game;

import static org.junit.jupiter.api.Assertions.*;

import com.amirhn.Pieces.Piece;
import org.junit.jupiter.api.Test;

/** The type Board test. */
class BoardTest {

  /** From fen creates correct board. */
  @Test
  void fromFEN_createsCorrectBoard() {
    String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
    Board board = Board.fromFEN(fen);
    assertEquals(8, board.rows);
    assertEquals(8, board.columns);
    assertTrue(board.isOccupied(Location.valueOf(0, 0)));
  }

  /** Is valid location returns false for invalid location. */
  @Test
  void isValidLocation_returnsFalseForInvalidLocation() {
    Board board = new Board(8, 8);
    assertFalse(board.isValidLocation(Location.valueOf(9, 9)));
  }

  /** Is occupied returns false for empty location. */
  @Test
  void isOccupied_returnsFalseForEmptyLocation() {
    Board board = new Board(8, 8);
    assertFalse(board.isOccupied(Location.valueOf(0, 0)));
  }

  /** Gets piece returns null for empty location. */
  @Test
  void getPiece_returnsNullForEmptyLocation() {
    Board board = new Board(8, 8);
    assertNull(board.getPiece(Location.valueOf(0, 0)));
  }

  /** Sets piece sets piece correctly. */
  @Test
  void setPiece_setsPieceCorrectly() {
    Board board = new Board(8, 8);
    Piece piece = Piece.generate('p', Location.valueOf(0, 0));
    board.setPiece(piece);
    assertEquals(piece, board.getPiece(Location.valueOf(0, 0)));
  }

  /** Remove piece removes piece correctly. */
  @Test
  void removePiece_removesPieceCorrectly() {
    Board board = new Board(8, 8);
    Piece piece = Piece.generate('p', Location.valueOf(0, 0));
    board.setPiece(piece);
    board.removePiece(piece);
    assertNull(board.getPiece(Location.valueOf(0, 0)));
  }

  /** Copy creates exact copy. */
  @Test
  void copy_createsExactCopy() {
    Board board = new Board(8, 8);
    Piece piece = Piece.generate('p', Location.valueOf(0, 0));
    board.setPiece(piece);
    Board copy = board.copy();
    assertEquals(board, copy);
  }

  /** Equals returns true for identical boards. */
  @Test
  void equals_returnsTrueForIdenticalBoards() {
    Board board1 = new Board(8, 8);
    Board board2 = new Board(8, 8);
    assertEquals(board1, board2);
  }

  /** Equals returns false for different boards. */
  @Test
  void equals_returnsFalseForDifferentBoards() {
    Board board1 = new Board(8, 8);
    Board board2 = new Board(8, 8);
    Piece piece = Piece.generate('p', Location.valueOf(0, 0));
    board2.setPiece(piece);
    assertNotEquals(board1, board2);
  }
}
