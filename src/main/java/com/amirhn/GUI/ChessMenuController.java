package com.amirhn.GUI;

/**
 * The interface Chess menu controller.
 */
public interface ChessMenuController {
  /**
   * New game.
   */
void newGame();

  /**
   * Load fen.
   */
void loadFEN();

  /**
   * Undo move.
   */
void undoMove();

  /**
   * Apply random move.
   */
void applyRandomMove();
}
