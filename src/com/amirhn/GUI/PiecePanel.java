package com.amirhn.GUI;

import com.amirhn.Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PiecePanel extends JPanel implements MouseMotionListener, MouseListener {

    public static final Dimension Size = new Dimension(100, 100);
    public final Piece piece;
    public JLabel label;

    private boolean isDragging = false;

    private final PieceListener pieceListener;

    public PiecePanel(Piece piece, PieceListener pieceListener) {
        super(new BorderLayout());
        this.setOpaque(false);
        this.piece = piece;
        this.pieceListener = pieceListener;
        this.label = new JLabel(new PieceImageIcon(piece));
        this.setPreferredSize(Size);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.add(this.label, BorderLayout.CENTER);
        this.validate();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        e.translatePoint(e.getComponent().getX() - e.getComponent().getWidth() / 2, e.getComponent().getY() - e.getComponent().getHeight() / 2);
        this.setLocation(e.getPoint());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.isDragging = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        e.translatePoint(e.getComponent().getX(), e.getComponent().getY());
        this.isDragging = true;
        this.pieceListener.pieceGrabbed(this.piece, e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        e.translatePoint(e.getComponent().getX(), e.getComponent().getY());
        this.isDragging = false;
        this.pieceListener.pieceDropped(this.piece, e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
