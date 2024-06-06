package com.amirhn.Game;

/** The type Scene. */
public class Scene {
  private final Board board;
  private final Color turn;

  /**
   * Instantiates a new Scene.
   *
   * @param board the board
   * @param turn the turn
   */
  public Scene(Board board, Color turn) {
    this.board = board;
    this.turn = turn;
  }

  /**
   * Gets board.
   *
   * @return the board
   */
  public Board getBoard() {
    return board;
  }

  /**
   * Gets turn.
   *
   * @return the turn
   */
  public Color getTurn() {
    return turn;
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
    Scene scene = (Scene) o;
    return board.equals(scene.board) && turn.equals(scene.turn);
  }
}
