package com.amirhn.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ChessMenuBar extends JMenuBar implements ActionListener {

  private final ChessMenuController controller;

  private final JMenuItem newGame;
  private final JMenuItem loadFEN;
  private final JMenuItem applyRandomMove;

  public ChessMenuBar(ChessMenuController controller) {
    this.controller = controller;

    JMenu gameMenu = new JMenu("Game");

    newGame = new JMenuItem("New Game (n)");
    newGame.addActionListener(this);

    loadFEN = new JMenuItem("Load FEN (l)");
    loadFEN.addActionListener(this);

    applyRandomMove = new JMenuItem("Apply Random Move (r)");
    applyRandomMove.addActionListener(this);

    gameMenu.add(newGame);
    gameMenu.add(loadFEN);
    add(gameMenu);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == newGame) {
      controller.newGame();
    } else if (e.getSource() == loadFEN) {
      controller.loadFEN();
    } else if (e.getSource() == applyRandomMove) {
      controller.applyRandomMove();
    }
  }
}
