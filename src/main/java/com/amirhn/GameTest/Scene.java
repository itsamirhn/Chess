package com.amirhn.GameTest;

public class Scene {
  private final Board board;
  private final Color turn;

  public Scene(Board board, Color turn) {
    this.board = board;
    this.turn = turn;
  }

  public Board getBoard() {
    return board;
  }

  public Color getTurn() {
    return turn;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Scene scene = (Scene) o;
    return board.equals(scene.board) && turn.equals(scene.turn);
  }
}
