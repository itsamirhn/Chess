package com.amirhn.Players;

import com.amirhn.Game.Color;
import com.amirhn.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private final Color color;
    public List<Piece> activePieces = new ArrayList<>();

    public Player(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
