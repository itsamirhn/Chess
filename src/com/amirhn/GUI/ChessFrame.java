package com.amirhn.GUI;

import com.amirhn.Game.Chess;

import javax.swing.*;

public class ChessFrame extends JFrame {
    private final Chess chess;
    public ChessFrame() {
        this.chess = new Chess();
        this.setTitle("Chess");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(new BoardPanel(this.chess.getBoard()));


        this.setResizable(false);
        this.setVisible(true);
        this.pack();
    }
}
