package com.amirhn.Players;

import com.amirhn.Game.Board;
import com.amirhn.Game.Chess;
import com.amirhn.Game.Color;
import com.amirhn.Game.Location;
import com.amirhn.Moves.Move;
import com.amirhn.Pieces.King;
import com.amirhn.Pieces.Piece;
import com.amirhn.Pieces.PieceType;
import com.amirhn.Pieces.Rook;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** The type Player. */
public class Player {
  private final Color color;

  /** The Captured pieces. */
  public List<Piece> capturedPieces = new ArrayList<>();

  /** The Had long castling. */
  public boolean hadLongCastling = false;

  /** The Had short castling. */
  public boolean hadShortCastling = false;

  /**
   * Instantiates a new Player.
   *
   * @param color the color
   */
  public Player(Color color) {
    this.color = color;
  }

  /**
   * Gets color.
   *
   * @return the color
   */
  public Color getColor() {
    return color;
  }

  /**
   * Gets active pieces.
   *
   * @param board the board
   * @return the active pieces
   */
  public List<Piece> getActivePieces(Board board) {
    return board.getAllPieces().stream()
        .filter(piece -> piece.color.equals(color))
        .collect(Collectors.toList());
  }

  /**
   * Gets natural moves.
   *
   * @param board the board
   * @return the natural moves
   */
  public List<Move> getNaturalMoves(Board board) {
    List<Move> moves = new ArrayList<>();
    for (Piece piece : this.getActivePieces(board)) moves.addAll(piece.getNaturalMoves(board));
    return moves;
  }

  /**
   * Gets allowed moves.
   *
   * @param chess the chess
   * @return the allowed moves
   */
  public List<Move> getAllowedMoves(Chess chess) {
    return getNaturalMoves(chess.getBoard()).stream()
        .filter(move -> move.isAllowed(chess))
        .collect(Collectors.toList());
  }

  /**
   * Gets threatened locations.
   *
   * @param board the board
   * @return the threatened locations
   */
  public List<Location> getThreatenedLocations(Board board) {
    List<Location> threatenedLocations = new ArrayList<>();
    for (Piece piece : getActivePieces(board))
      threatenedLocations.addAll(piece.getThreatenedLocations(board));
    return threatenedLocations;
  }

  /**
   * Gets pieces.
   *
   * @param board the board
   * @param pieceType the piece type
   * @return the pieces
   */
  public List<Piece> getPieces(Board board, PieceType pieceType) {
    List<Piece> pieces = new ArrayList<>();
    for (Piece piece : getActivePieces(board)) if (piece.type == pieceType) pieces.add(piece);
    return pieces;
  }

  /**
   * Gets king.
   *
   * @param board the board
   * @return the king
   */
  public King getKing(Board board) {
    return (King) getPieces(board, PieceType.KING).getFirst();
  }

  /**
   * Gets rooks.
   *
   * @param board the board
   * @return the rooks
   */
  public List<Rook> getRooks(Board board) {
    return getPieces(board, PieceType.ROOK).stream().map(piece -> (Rook) piece).toList();
  }

  /**
   * Gets king side rook.
   *
   * @param board the board
   * @return the king side rook
   */
  public Rook getKingSideRook(Board board) {
    return getRooks(board).stream()
        .filter(rook -> rook.getLocation().column == 7)
        .findFirst()
        .orElse(null);
  }

  /**
   * Gets queen side rook.
   *
   * @param board the board
   * @return the queen side rook
   */
  public Rook getQueenSideRook(Board board) {
    return getRooks(board).stream()
        .filter(rook -> rook.getLocation().column == 0)
        .findFirst()
        .orElse(null);
  }

  /**
   * Is threatening boolean.
   *
   * @param board the board
   * @param location the location
   * @return the boolean
   */
  public boolean isThreatening(Board board, Location location) {
    for (Location threat : getThreatenedLocations(board)) if (threat.equals(location)) return true;
    return false;
  }
}
