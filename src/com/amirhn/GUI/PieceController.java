package com.amirhn.GUI;

import com.amirhn.Pieces.Piece;

import java.awt.event.MouseEvent;

public interface PieceController extends LocationListener {
    boolean pieceCanBeDragged(Piece piece);
    void pieceGrabbed(Piece piece, MouseEvent e);
    void pieceDropped(Piece piece, MouseEvent e);
}
