package com.amirhn.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessMenuBar extends JMenuBar implements ActionListener {

    private final ChessMenuController controller;

    private final JMenuItem newGame;
    private final JMenuItem loadFEN;

    public ChessMenuBar(ChessMenuController controller) {
        this.controller = controller;

        JMenu gameMenu = new JMenu("Game");

        newGame = new JMenuItem("New Game");
        newGame.addActionListener(this);

        loadFEN = new JMenuItem("Load FEN");
        loadFEN.addActionListener(this);

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
        }
    }
}
