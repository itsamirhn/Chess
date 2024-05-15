package com.amirhn.Game;

import com.amirhn.Moves.*;
import com.amirhn.Pieces.King;
import com.amirhn.Pieces.Piece;
import com.amirhn.Pieces.PieceType;
import com.amirhn.Players.Player;
import java.util.ArrayList;
import java.util.List;

public class Chess {

  public static final String OriginalFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq -";
  public Player whitePlayer = new Player(Color.WHITE);
  public Player blackPlayer = new Player(Color.BLACK);
  public List<Move> moves = new ArrayList<>();
  public Color turn = Color.WHITE;
  public List<Scene> history = new ArrayList<>();
  private Board board;

  public Chess() {
    this(OriginalFen);
  }

  public Chess(String fen) {
    this.setupFEN(fen);
    history.add(new Scene(board.copy(), turn));
  }

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

  public void setPiece(Piece piece) {
    this.board.setPiece(piece);
  }

  public Board getBoard() {
    return board;
  }

  public Player getTurnPlayer() {
    return getPlayer(turn);
  }

  public Player getOpponentPlayer() {
    return getPlayer(turn.opposite());
  }

  public Player getPlayer(Color color) {
    if (color == Color.WHITE) return whitePlayer;
    return blackPlayer;
  }

  public List<Move> getAllowedMoves() {
    return getTurnPlayer().getAllowedMoves(this);
  }

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

  public boolean applyMove(Move move) {
    if (move == null) return false;
    return getAllowedMoves().stream().filter(m -> m.toString().equals(move.toString())).count() == 1
        && applyLegalMove(move);
  }

  public boolean isInCheck() {
    King king = getTurnPlayer().getKing(board);
    return getOpponentPlayer().isThreatening(board, king.getLocation());
  }

  public boolean isCheckmate() {
    if (!isInCheck()) return false;
    return getAllowedMoves().isEmpty();
  }

  public boolean isStalemate() {
    if (isInCheck()) return false;
    return getTurnPlayer().getAllowedMoves(this).isEmpty();
  }

  public boolean isThreefoldRepetition() {
    Scene currentScene = history.getLast();
    int count = 0;
    for (Scene scene : history) {
      if (scene.equals(currentScene)) count++;
    }
    return count >= 3;
  }

  public boolean is50MoveRule() {
    if (moves.size() < 50) return false;
    return moves.subList(moves.size() - 50, moves.size()).stream()
        .noneMatch(move -> move.type == MoveType.CAPTURE || move.piece.type == PieceType.PAWN);
  }

  public boolean isDraw() {
    return is50MoveRule() || isThreefoldRepetition();
  }

  public Status getStatus() {
    if (isCheckmate()) return Status.CHECKMATE;
    if (isStalemate()) return Status.STALEMATE;
    if (isDraw()) return Status.DRAW;
    return Status.ONGOING;
  }

  public void undoMove() {
    if (history.size() < 2) return;
    history.removeLast();
    Scene scene = history.getLast();
    board = scene.getBoard().copy();
    turn = scene.getTurn();
    moves.removeLast();
  }

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

  @Override
  public String toString() {
    return "Turn: " + turn + "\n" + board;
  }
}
