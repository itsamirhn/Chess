package com.amirhn.Game;

import java.util.Objects;

/** The type Location. */
public class Location {
  /** The Row. */
  public final int row,
      /** The Column. */
      column;

  /**
   * Instantiates a new Location.
   *
   * @param row the row
   * @param column the column
   */
  public Location(int row, int column) {
    this.row = row;
    this.column = column;
  }

  /**
   * Value of location.
   *
   * @param loc the loc
   * @return the location
   */
  public static Location valueOf(String loc) {
    return new Location(loc.charAt(1) - '1', loc.charAt(0) - 'a');
  }

  /**
   * Value of location.
   *
   * @param row the row
   * @param column the column
   * @return the location
   */
  public static Location valueOf(int row, int column) {
    return new Location(row, column);
  }

  /**
   * By offset location.
   *
   * @param dx the dx
   * @param dy the dy
   * @return the location
   */
  public Location byOffset(int dx, int dy) {
    return Location.valueOf(this.row + dx, this.column + dy);
  }

  /**
   * Is light boolean.
   *
   * @return the boolean
   */
  public boolean isLight() {
    return ((this.row + this.column) % 2) == 1;
  }

  /**
   * To string string.
   *
   * @return the string
   */
  @Override
  public String toString() {
    return ((char) (this.column + 'a')) + String.valueOf(this.row + 1);
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
    Location location = (Location) o;
    return row == location.row && column == location.column && hashCode() == location.hashCode();
  }

  /**
   * Hash code int.
   *
   * @return the int
   */
  @Override
  public int hashCode() {
    return Objects.hash(row, column);
  }
}
