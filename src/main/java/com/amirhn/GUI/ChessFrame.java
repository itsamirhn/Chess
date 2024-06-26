package com.amirhn.GUI;

import com.amirhn.GUI.Components.BoardPanel;
import com.amirhn.Game.Chess;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

/** The type Chess frame. */
public class ChessFrame extends JFrame {

  /** Instantiates a new Chess frame. */
  public ChessFrame() {
    super();

    Chess chess = new Chess();
    BoardPanel boardPanel = new BoardPanel(chess.getBoard().rows, chess.getBoard().columns);
    ChessController chessController = new ChessController(chess, boardPanel);
    this.setContentPane(boardPanel);

    this.setTitle(Constants.GameTitle);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setJMenuBar(new ChessMenuBar(chessController));
    this.setResizable(false);
    this.pack();
    this.setLocationRelativeTo(null);
    this.setVisible(true);

    this.addKeyListener(
        new KeyAdapter() {
          @Override
          public void keyTyped(KeyEvent e) {
            switch (e.getKeyChar()) {
              case 'n' -> chessController.newGame();
              case 'l' -> chessController.loadFEN();
              case 'r' -> chessController.applyRandomMove();
              case 'z' -> chessController.undoMove();
            }
          }
        });
  }
}
