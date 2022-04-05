package com.amirhn.GUI;

import com.amirhn.Game.Board;
import com.amirhn.Game.Chess;
import com.amirhn.Moves.Move;

import javax.swing.*;

public class ChessFrame extends JFrame {
    public final Chess chess;
    public final BoardPanel boardPanel;

    public ChessFrame() {
        this(new Chess());
    }

    public ChessFrame(Chess chess) {
        this.chess = chess;

        this.setTitle("Chess");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.boardPanel = new BoardPanel(chess.getBoard());
        this.add(boardPanel);

        this.setResizable(false);
        this.setVisible(true);
        this.pack();
    }

    public void update() {
        boardPanel.update();
        validate();
        repaint();
    }

    public void applyMove(Move move) {
        this.chess.applyMove(move);
        this.update();
    }

    public void applyRandomMove() {
        this.chess.applyRandomMove();
        this.update();
    }
}
