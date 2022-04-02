package com.amirhn.Game;

public enum Color {
    BLACK,
    WHITE;

    public Color opposite() {
        if (this == Color.BLACK) return Color.WHITE;
        if (this == Color.WHITE) return Color.BLACK;
        return null;
    }
}
