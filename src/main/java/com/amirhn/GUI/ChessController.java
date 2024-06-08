package com.amirhn.GUI;

import com.amirhn.GUI.Components.BoardPanel;
import com.amirhn.GUI.Components.LocationState;
import com.amirhn.GUI.Components.PieceImageIcon;
import com.amirhn.GUI.Components.PiecePanel;
import com.amirhn.GUI.Listeners.LocationListener;
import com.amirhn.Game.Chess;
import com.amirhn.Game.Location;
import com.amirhn.Moves.Move;
import com.amirhn.Moves.MoveType;
import com.amirhn.Pieces.Piece;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import javax.sound.sampled.*;
import javax.swing.*;

/** The type Chess controller. */
public class ChessController implements LocationListener, ChessMenuController {

  private final BoardPanel boardPanel;
  private boolean finished = false;
  private Chess chess;
  private Piece currentSelectedPiece;
  private Move lastMove;

  /**
   * Instantiates a new Chess controller.
   *
   * @param chess the chess
   * @param boardPanel the board panel
   */
  public ChessController(Chess chess, BoardPanel boardPanel) {
    this.boardPanel = boardPanel;
    this.boardPanel.setLocationListener(this);
    initialize(chess);
  }

  private void initialize(Chess chess) {
    this.chess = chess;
    this.currentSelectedPiece = null;
    this.lastMove = null;
    this.finished = false;
    draw();
  }

  private void addPiece(Piece piece) {
    PieceImageIcon icon = new PieceImageIcon(piece.color.toString(), piece.type.letter);
    PiecePanel piecePanel = new PiecePanel(icon);
    this.boardPanel.addPiecePanel(piecePanel, piece.getLocation());
  }

  /** Apply random move. */
  public void applyRandomMove() {
    if (finished) return;
    Move move = chess.getRandomMove();
    if (move != null && applyMove(move)) {
      currentSelectedPiece = null;
    }
    draw();
  }

  private Move makeMove(Piece piece, Location location) {
    if (!chess.getBoard().isValidPiece(piece)) return null;
    if (!chess.getBoard().isValidLocation(location)) return null;
    return chess.getAllowedMoves().stream()
        .filter(m -> m.piece.equals(piece) && m.getEndpointLocation().equals(location))
        .findFirst()
        .orElse(null);
  }

  private void playMoveSound(Move move) {
    String soundPath;
    if (move.type == MoveType.CAPTURE) {
      soundPath = Constants.CaptureSoundPath;
    } else {
      soundPath = Constants.MoveSoundPath;
    }
    URL soundURL = getClass().getResource(soundPath);
    AudioInputStream audioInputStream = null;
    try {
      audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(soundURL));
    } catch (UnsupportedAudioFileException | IOException e) {
      e.printStackTrace();
    }
    Clip clip = null;
    try {
      clip = AudioSystem.getClip();
    } catch (LineUnavailableException e) {
      e.printStackTrace();
    }
    try {
      assert clip != null;
      clip.open(audioInputStream);
    } catch (LineUnavailableException | IOException e) {
      e.printStackTrace();
    }
    clip.start();
  }

  /**
   * Apply move boolean.
   *
   * @param move the move
   * @return the boolean
   */
  public boolean applyMove(Move move) {
    if (!chess.applyMove(move)) return false;
    playMoveSound(move);
    lastMove = move;
    return true;
  }

  private void draw() {
    this.boardPanel.resetLocationStates();
    if (lastMove != null) {
      this.boardPanel.setLocationState(lastMove.getStartpointLocation(), LocationState.LASTMOVE);
      this.boardPanel.setLocationState(lastMove.getEndpointLocation(), LocationState.LASTMOVE);
    }
    if (currentSelectedPiece != null) {
      this.boardPanel.setLocationState(currentSelectedPiece.getLocation(), LocationState.SELECTED);
      this.chess.getAllowedMoves().stream()
          .filter(m -> m.piece.equals(currentSelectedPiece))
          .forEach(
              m -> {
                this.boardPanel.setLocationState(m.getEndpointLocation(), LocationState.SUGGESTED);
              });
    }
    this.boardPanel.removePiecePanels();
    for (Piece piece : this.chess.getBoard().getAllPieces()) {
      this.addPiece(piece);
    }
    this.boardPanel.validate();
    this.boardPanel.repaint();
    this.checkForFinish();
  }

  /**
   * Location selected.
   *
   * @param location the location
   */
  @Override
  public void locationSelected(Location location) {
    if (finished) return;
    if (currentSelectedPiece == null) {
      currentSelectedPiece = chess.getBoard().getPiece(location);
    } else {
      Move move = makeMove(currentSelectedPiece, location);
      if (move != null && applyMove(move)) {
        currentSelectedPiece = null;
      }
    }
    draw();
  }

  /**
   * Location grabbed.
   *
   * @param location the location
   */
  @Override
  public void locationGrabbed(Location location) {
    if (finished) return;
    currentSelectedPiece = chess.getBoard().getPiece(location);
    draw();
  }

  /**
   * Location dropped.
   *
   * @param location the location
   */
  @Override
  public void locationDropped(Location location) {
    if (finished) return;
    if (currentSelectedPiece == null) {
      draw();
      return;
    }
    Move move = makeMove(currentSelectedPiece, location);
    if (move != null && applyMove(move)) {
      currentSelectedPiece = null;
    }
    draw();
  }

  /**
   * Is location draggable boolean.
   *
   * @param location the location
   * @return the boolean
   */
  @Override
  public boolean isLocationDraggable(Location location) {
    return !finished
        && chess.getBoard().isOccupied(location)
        && chess.getBoard().getPiece(location).color.equals(chess.getTurnPlayer().getColor());
  }

  private void checkForFinish() {
    switch (chess.getStatus()) {
      case CHECKMATE ->
          JOptionPane.showMessageDialog(
              boardPanel, "Checkmate! " + chess.getTurnPlayer().getColor().opposite() + " wins!");
      case STALEMATE -> JOptionPane.showMessageDialog(boardPanel, "Stalemate!");
      case DRAW -> JOptionPane.showMessageDialog(boardPanel, "Draw!");
      case ONGOING -> {
        return;
      }
    }
    finished = true;
  }

  /** New game. */
  @Override
  public void newGame() {
    initialize(new Chess());
  }

  /** Load fen. */
  @Override
  public void loadFEN() {
    String fen = JOptionPane.showInputDialog(boardPanel, "Enter FEN:");
    if (fen == null) return;
    initialize(new Chess(fen));
  }

  /** Undo move. */
  @Override
  public void undoMove() {
    if (finished) return;
    chess.undoMove();
    currentSelectedPiece = null;
    if (!chess.moves.isEmpty()) {
      lastMove = chess.moves.getLast();
    } else {
      lastMove = null;
    }
    draw();
  }
}
