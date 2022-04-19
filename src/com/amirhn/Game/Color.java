package com.amirhn.Game;

public enum Color {
    BLACK(-1),
    WHITE(+1);

    public final int direction;

    Color(int direction) {
        this.direction = direction;
    }

    public Color opposite() {
        if (this == Color.BLACK) return Color.WHITE;
        if (this == Color.WHITE) return Color.BLACK;
        return null;
    }

    public static Color valueOfPieceChar(char c) {
        if (Character.isUpperCase(c)) return Color.WHITE;
        if (Character.isLowerCase(c)) return Color.BLACK;
        return null;
    }

    public static Color valueOf(char c) {
        if (Character.toUpperCase(c) == 'W') return Color.WHITE;
        if (Character.toUpperCase(c) == 'B') return Color.BLACK;
        return null;
    }

    public static Color fromString(String c) {
        if (c.length() == 1) return valueOf(c.charAt(0));
        if (c.equalsIgnoreCase("WHITE")) return Color.WHITE;
        if (c.equalsIgnoreCase("BLACK")) return Color.BLACK;
        return null;
    }

}
