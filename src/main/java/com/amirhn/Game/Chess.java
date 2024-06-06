package com.amirhn.Game;

import com.amirhn.Moves.*;
import com.amirhn.Pieces.King;
import com.amirhn.Pieces.Piece;
import com.amirhn.Pieces.PieceType;
import com.amirhn.Players.Player;
import java.util.ArrayList;
import java.util.List;

/** The type Chess. */
public class Chess {

  /** The constant OriginalFen. */
  public static final String OriginalFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq -";

  /** The White player. */
  public Player whitePlayer = new Player(Color.WHITE);

  /** The Black player. */
  public Player blackPlayer = new Player(Color.BLACK);

  /** The Moves. */
  public List<Move> moves = new ArrayList<>();

  /** The Turn. */
  public Color turn = Color.WHITE;

  /** The History. */
  public List<Scene> history = new ArrayList<>();

  private Board board;

  /** Instantiates a new Chess. */
  public Chess() {
    this(OriginalFen);
  }

  /**
   * Instantiates a new Chess.
   *
   * @param fen the fen
   */
  public Chess(String fen) {
    this.setupFEN(fen);
    history.add(new Scene(board.copy(), turn));
  }

  /**
   * Sets fen.
   *
   * @param fen the fen
   */
  public void setupFEN(String fen) {
    String[] parts = fen.split(" ");
    board = Board.fromFEN(parts[0]);
    turn = Color.fromString(parts[1]);
    whitePlayer.hadShortCastling = true;
    whitePlayer.hadLongCastling = true;
    blackPlayer.hadShortCastling = true;
    blackPlayer.hadLongCastling = true;
    if (!parts[2].equals("-")) {
      for (char c : parts[2].toCharArray()) {
        if (Character.toLowerCase(c) == 'k')
          getPlayer(Color.valueOfPieceChar(c)).hadShortCastling = false;
        if (Character.toLowerCase(c) == 'q')
          getPlayer(Color.valueOfPieceChar(c)).hadLongCastling = false;
      }
    }

    // TODO: en passant
    // TODO: move count
  }

  /**
   * Sets piece.
   *
   * @param piece the piece
   */
  public void setPiece(Piece piece) {
    this.board.setPiece(piece);
  }

  /**
   * Gets board.
   *
   * @return the board
   */
  public Board getBoard() {
    return board;
  }

  /**
   * Gets turn player.
   *
   * @return the turn player
   */
  public Player getTurnPlayer() {
    return getPlayer(turn);
  }

  /**
   * Gets opponent player.
   *
   * @return the opponent player
   */
  public Player getOpponentPlayer() {
    return getPlayer(turn.opposite());
  }

  /**
   * Gets player.
   *
   * @param color the color
   * @return the player
   */
  public Player getPlayer(Color color) {
    if (color == Color.WHITE) return whitePlayer;
    return blackPlayer;
  }

  /**
   * Gets allowed moves.
   *
   * @return the allowed moves
   */
  public List<Move> getAllowedMoves() {
    return getTurnPlayer().getAllowedMoves(this);
  }

  /**
   * Gets random move.
   *
   * @return the random move
   */
  public Move getRandomMove() {
    List<Move> moves = getAllowedMoves();
    if (moves.isEmpty()) return null;
    return moves.get((int) (Math.random() * moves.size()));
  }

  private boolean isAllowed(Move move) {
    return move.isAllowed(this);
  }

  private boolean applyLegalMove(Move move) {
    if (!isAllowed(move)) return false;
    if (!move.applyOnBoard(board)) return false;
    if (move.type == MoveType.CAPTURE && move instanceof Capture) {
      Piece capturedPiece = ((Capture) move).capturePiece;
      getTurnPlayer().capturedPieces.add(capturedPiece);
    }
    if (move.type == MoveType.CASTLING && move instanceof Castling) {
      if (move instanceof ShortCastling) getTurnPlayer().hadShortCastling = true;
      if (move instanceof LongCastling) getTurnPlayer().hadLongCastling = true;
    }
    turn = turn.opposite();
    moves.add(move);
    history.add(new Scene(board.copy(), turn));
    return true;
  }

  /**
   * Apply move boolean.
   *
   * @param move the move
   * @return the boolean
   */
  public boolean applyMove(Move move) {
    if (move == null) return false;
    return getAllowedMoves().stream().filter(m -> m.toString().equals(move.toString())).count() == 1
        && applyLegalMove(move);
  }

  /**
   * Is in check boolean.
   *
   * @return the boolean
   */
  public boolean isInCheck() {
    King king = getTurnPlayer().getKing(board);
    return getOpponentPlayer().isThreatening(board, king.getLocation());
  }

  /**
   * Is checkmate boolean.
   *
   * @return the boolean
   */
  public boolean isCheckmate() {
    if (!isInCheck()) return false;
    return getAllowedMoves().isEmpty();
  }

  /**
   * Is stalemate boolean.
   *
   * @return the boolean
   */
  public boolean isStalemate() {
    if (isInCheck()) return false;
    return getTurnPlayer().getAllowedMoves(this).isEmpty();
  }

  /**
   * Is threefold repetition boolean.
   *
   * @return the boolean
   */
  public boolean isThreefoldRepetition() {
    Scene currentScene = history.getLast();
    int count = 0;
    for (Scene scene : history) {
      if (scene.equals(currentScene)) count++;
    }
    return count >= 3;
  }

  /**
   * Is 50 move rule boolean.
   *
   * @return the boolean
   */
  public boolean is50MoveRule() {
    if (moves.size() < 50) return false;
    return moves.subList(moves.size() - 50, moves.size()).stream()
        .noneMatch(move -> move.type == MoveType.CAPTURE || move.piece.type == PieceType.PAWN);
  }

  /**
   * Is draw boolean.
   *
   * @return the boolean
   */
  public boolean isDraw() {
    return is50MoveRule() || isThreefoldRepetition();
  }

  /**
   * Gets status.
   *
   * @return the status
   */
  public Status getStatus() {
    if (isCheckmate()) return Status.CHECKMATE;
    if (isStalemate()) return Status.STALEMATE;
    if (isDraw()) return Status.DRAW;
    return Status.ONGOING;
  }

  /** Undo move. */
  public void undoMove() {
    if (history.size() < 2) return;
    history.removeLast();
    Scene scene = history.getLast();
    board = scene.getBoard().copy();
    turn = scene.getTurn();
    moves.removeLast();
  }

  /**
   * Move from string move.
   *
   * @param moveString the move string
   * @return the move
   */
  public Move moveFromString(String moveString) {
    if (moveString.equals("O-O"))
      return new ShortCastling(
          getTurnPlayer().getKing(board), getTurnPlayer().getKingSideRook(board));
    if (moveString.equals("O-O-O"))
      return new LongCastling(
          getTurnPlayer().getKing(board), getTurnPlayer().getQueenSideRook(board));
    List<Piece> sourcePieces = getTurnPlayer().getActivePieces(board);
    char sourceChar = moveString.charAt(0);
    sourcePieces = sourcePieces.stream().filter(piece -> piece.type.letter == sourceChar).toList();
    if (sourcePieces.isEmpty()) return null;
    Location dest = Location.valueOf(moveString.substring(moveString.length() - 2));
    List<Move> moves =
        sourcePieces.stream()
            .map(piece -> piece.getAllowedMoves(this))
            .flatMap(List::stream)
            .filter(move -> move.getEndpointLocation().equals(dest))
            .toList();
    if (moves.isEmpty()) return null;
    if (moves.size() == 1) return moves.getFirst();
    if (Character.isAlphabetic(moveString.charAt(1))) {
      char sourceColumn = moveString.charAt(1);
      moves =
          moves.stream()
              .filter(move -> move.getStartpointLocation().column == sourceColumn - 'a')
              .toList();
      if (moves.size() == 1) return moves.getFirst();
    }
    if (Character.isDigit(moveString.charAt(1))) {
      char sourceRow = moveString.charAt(1);
      moves =
          moves.stream()
              .filter(move -> move.getStartpointLocation().row == sourceRow - '1')
              .toList();
      if (moves.size() == 1) return moves.getFirst();
    }
    Location source = Location.valueOf(moveString.substring(1, 3));
    return moves.stream()
        .filter(move -> move.getStartpointLocation().equals(source))
        .findFirst()
        .orElse(null);
  }

  /**
   * To string string.
   *
   * @return the string
   */
  @Override
  public String toString() {
    return "Turn: " + turn + "\n" + board;
  }
}
