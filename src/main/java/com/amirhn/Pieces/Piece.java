package com.amirhn.Pieces;

import com.amirhn.Game.Board;
import com.amirhn.Game.Chess;
import com.amirhn.Game.Color;
import com.amirhn.Game.Location;
import com.amirhn.Moves.Capture;
import com.amirhn.Moves.Move;
import com.amirhn.Moves.Walk;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/** The type Piece. */
public abstract class Piece {

  /** The Type. */
  public final PieceType type;

  /** The Color. */
  public final Color color;

  /** The Location. */
  protected Location location;

  /** The Moves. */
  protected int moves = 0;

  /**
   * Instantiates a new Piece.
   *
   * @param type the type
   * @param color the color
   * @param location the location
   */
  public Piece(PieceType type, Color color, Location location) {
    this.type = type;
    this.color = color;
    this.location = location;
  }

  /**
   * Generate piece.
   *
   * @param pieceType the piece type
   * @param color the color
   * @param location the location
   * @return the piece
   */
  public static Piece generate(PieceType pieceType, Color color, Location location) {
    return switch (pieceType) {
      case KING -> new King(color, location);
      case PAWN -> new Pawn(color, location);
      case ROOK -> new Rook(color, location);
      case QUEEN -> new Queen(color, location);
      case BISHOP -> new Bishop(color, location);
      case KNIGHT -> new Knight(color, location);
    };
  }

  /**
   * Generate piece.
   *
   * @param fen the fen
   * @param location the location
   * @return the piece
   */
  public static Piece generate(char fen, Location location) {
    return generate(
        Objects.requireNonNull(PieceType.valueOf(fen)), Color.valueOfPieceChar(fen), location);
  }

  /**
   * Gets location.
   *
   * @return the location
   */
  public Location getLocation() {
    return location;
  }

  /**
   * Sets location.
   *
   * @param location the location
   */
  public void setLocation(Location location) {
    if (this.location != null) moves++;
    this.location = location;
  }

  /**
   * Sets location back.
   *
   * @param location the location
   */
  public void setLocationBack(Location location) {
    if (this.location != null) moves--;
    this.location = location;
  }

  /** Remove location. */
  public void removeLocation() {
    this.location = null;
  }

  /**
   * Has moved boolean.
   *
   * @return the boolean
   */
  public boolean hasMoved() {
    return moves > 0;
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
    Piece piece = (Piece) o;
    return type.equals(piece.type) && color.equals(piece.color) && location.equals(piece.location);
  }

  /**
   * Hash code int.
   *
   * @return the int
   */
  @Override
  public int hashCode() {
    return Objects.hash(type, color, location);
  }

  /**
   * To string string.
   *
   * @return the string
   */
  @Override
  public String toString() {
    return this.type + "{" + "color=" + color + ", location=" + location + '}';
  }

  /**
   * Is allowed to move boolean.
   *
   * @param chess the chess
   * @return the boolean
   */
  public boolean isAllowedToMove(Chess chess) {
    return chess.turn.equals(this.color);
  }

  /**
   * Gets allowed moves.
   *
   * @param chess the chess
   * @return the allowed moves
   */
  public List<Move> getAllowedMoves(Chess chess) {
    return this.getNaturalMoves(chess.getBoard()).stream()
        .filter(move -> move.isAllowed(chess))
        .collect(Collectors.toList());
  }

  /**
   * Can be captured by boolean.
   *
   * @param piece the piece
   * @return the boolean
   */
  public boolean canBeCapturedBy(Piece piece) {
    if (piece == null) return false;
    if (type == PieceType.KING) return false;
    return this.color != piece.color;
  }

  /**
   * Gets symbol.
   *
   * @return the symbol
   */
  public char getSymbol() {
    return this.type.getSymbol(this.color);
  }

  /**
   * Gets threatened locations.
   *
   * @param board the board
   * @return the threatened locations
   */
  public abstract List<Location> getThreatenedLocations(Board board);

  /**
   * Gets natural moves.
   *
   * @param board the board
   * @return the natural moves
   */
  public List<Move> getNaturalMoves(Board board) {
    List<Move> moves = new ArrayList<>();
    for (Location location : this.getThreatenedLocations(board)) {
      if (board.isOccupied(location)) {
        Piece capturingPiece = board.getPiece(location);
        if (capturingPiece.canBeCapturedBy(this)) moves.add(new Capture(this, capturingPiece));
      } else moves.add(new Walk(this, location));
    }
    return moves;
  }

  /**
   * Copy piece.
   *
   * @return the piece
   */
  public Piece copy() {
    return generate(this.type, this.color, this.location);
  }
}
