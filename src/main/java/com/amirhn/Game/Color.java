package com.amirhn.Game;

/** The enum Color. */
public enum Color {
  /** Black color. */
  BLACK(-1),
  /** White color. */
  WHITE(+1);

  /** The Direction. */
  public final int direction;

  Color(int direction) {
    this.direction = direction;
  }

  /**
   * Value of piece char color.
   *
   * @param c the c
   * @return the color
   */
  public static Color valueOfPieceChar(char c) {
    if (Character.isUpperCase(c)) return Color.WHITE;
    if (Character.isLowerCase(c)) return Color.BLACK;
    return null;
  }

  /**
   * Value of color.
   *
   * @param c the c
   * @return the color
   */
  public static Color valueOf(char c) {
    if (Character.toUpperCase(c) == 'W') return Color.WHITE;
    if (Character.toUpperCase(c) == 'B') return Color.BLACK;
    return null;
  }

  /**
   * From string color.
   *
   * @param c the c
   * @return the color
   */
  public static Color fromString(String c) {
    if (c.length() == 1) return valueOf(c.charAt(0));
    if (c.equalsIgnoreCase("WHITE")) return Color.WHITE;
    if (c.equalsIgnoreCase("BLACK")) return Color.BLACK;
    return null;
  }

  /**
   * Opposite color.
   *
   * @return the color
   */
  public Color opposite() {
    if (this == Color.BLACK) return Color.WHITE;
    if (this == Color.WHITE) return Color.BLACK;
    return null;
  }
}
