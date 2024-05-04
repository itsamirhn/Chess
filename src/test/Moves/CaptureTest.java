package test.Moves;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.amirhn.Game.Board;
import com.amirhn.Game.Color;
import com.amirhn.Game.Location;
import com.amirhn.Moves.Capture;
import com.amirhn.Pieces.Pawn;
import com.amirhn.Pieces.Piece;
import com.amirhn.Pieces.Queen;
import org.junit.jupiter.api.Test;

class CaptureTest {

  @Test
  void applyOnBoard() {
    Board board = new Board(8, 8);
    Piece piece = new Queen(Color.WHITE, new Location(1, 1));
    Piece capturePiece = new Queen(Color.BLACK, new Location(1, 2));
    board.setPiece(piece);
    board.setPiece(capturePiece);
    Capture capture = new Capture(piece, capturePiece);
    capture.applyOnBoard(board);
    assertEquals(piece, board.getPiece(new Location(1, 2)));
    assertFalse(board.isValidPiece(capturePiece));
  }

  @Test
  void undoOnBoard() {
    Board board = new Board(8, 8);
    Piece piece = new Queen(Color.WHITE, new Location(1, 1));
    Piece capturePiece = new Queen(Color.BLACK, new Location(1, 2));
    Piece futurePiece = new Pawn(Color.WHITE, new Location(1, 2));
    board.setPiece(futurePiece);
    Capture capture = new Capture(piece, capturePiece);
    capture.undoOnBoard(board);
    assertEquals(piece, board.getPiece(new Location(1, 1)));
    assertEquals(capturePiece, board.getPiece(new Location(1, 2)));
  }
}
