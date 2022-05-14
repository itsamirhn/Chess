package com.amirhn.GUI;

import com.amirhn.GUI.Components.BoardPanel;
import com.amirhn.Game.Chess;
import com.amirhn.Game.Location;
import com.amirhn.Moves.Move;
import com.amirhn.Moves.MoveController;
import com.amirhn.Moves.MoveType;
import com.amirhn.Pieces.Piece;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ChessFrame extends JFrame implements MoveController, ChessMenuController{

    private Chess chess;
    private BoardPanel boardPanel;

    public ChessFrame() {
        this(new Chess());
    }

    public ChessFrame(String fen) {
        this(new Chess(fen));
    }

    private void initChess(Chess chess) {
        this.chess = chess;
        if (boardPanel != null) this.remove(boardPanel);
        boardPanel = new BoardPanel(chess.getBoard(), this);
        this.add(boardPanel);
        this.update();
    }

    public ChessFrame(Chess chess) {

        this.initChess(chess);

        this.setTitle("Chess");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setJMenuBar(new ChessMenuBar(this));

        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void update() {
        boardPanel.update();
        validate();
        repaint();
    }

    @Override
    public boolean isAllowedToMove(Piece piece) {
        return chess.getBoard().isValidPiece(piece) && piece.isAllowedToMove(chess);
    }

    @Override
    public List<Move> getAllowedMoves(Piece piece) {
        return piece.getAllowedMoves(chess);
    }

    @Override
    public Move makeMove(Piece piece, Location endpoint) {
        if (!chess.getBoard().isValidPiece(piece)) return null;
        List<Move> moves = piece.getAllowedMoves(chess).stream().filter(move -> move.getEndpointLocation().equals(endpoint)).toList();
        if (moves.isEmpty()) return null;
        return moves.get(0);
    }

    @Override
    public boolean applyMove(Move move) {
        if (chess.applyMove(move)) {
            update();
            this.playMoveSound(move);
            return true;
        }
        return false;
    }

    private void playMoveSound(Move move) {
        String soundName = "sound/" + (move.type == MoveType.CAPTURE ? "capture" : "move") + ".wav";
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
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

    @Override
    public void newGame() {
        initChess(new Chess());
    }

    @Override
    public void loadFEN() {
        String fen = JOptionPane.showInputDialog(this, "Enter FEN");
        if (fen == null) return;
        initChess(new Chess(fen));
    }
}
