package com.amirhn.GUI.Components;

import com.amirhn.GUI.Listeners.LocationListener;
import com.amirhn.GUI.Listeners.PieceController;
import com.amirhn.Game.Board;
import com.amirhn.Game.Location;
import com.amirhn.Moves.Move;
import com.amirhn.Moves.MoveController;
import com.amirhn.Pieces.Piece;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class BoardPanel extends JLayeredPane implements PieceController, LocationListener {

    private final MoveController moveController;
    private Move lastMove;

    private final TablePanel tablePanel;

    private final Board board;

    private Piece currentSelectedPiece;

    public BoardPanel(Board board, MoveController moveController) {
        super();

        this.moveController = moveController;
        this.board = board;

        this.tablePanel = new TablePanel(board.rows, board.columns, this);
        this.tablePanel.setBounds(0, 0, this.tablePanel.getPreferredSize().width, this.tablePanel.getPreferredSize().height);
        this.setPreferredSize(this.tablePanel.getPreferredSize());
        this.add(tablePanel, Integer.valueOf(0));
        for (Piece piece : board.pieceByLocation.values()) setPiece(piece);

        this.validate();
    }

    private void setPiece(Piece piece) {
        Location location = piece.getLocation();
        if (location == null) return;
        PiecePanel piecePanel = new PiecePanel(piece, this);
        piecePanel.setBounds(this.tablePanel.pointOf(location).x, this.tablePanel.pointOf(location).y, piecePanel.getPreferredSize().width, piecePanel.getPreferredSize().height);
        this.add(piecePanel, Integer.valueOf(1));
    }

    private void setLastMove(Move lastMove) {
        this.tablePanel.resetStates(LocationPanel.State.LASTMOVE);
        this.lastMove = lastMove;
        showLastMove(lastMove);
    }

    private void setCurrentSelectedPiece(Piece currentSelectedPiece) {
        this.tablePanel.resetStates(LocationPanel.State.SELECTED);
        this.tablePanel.resetStates(LocationPanel.State.SUGGESTED);
        this.currentSelectedPiece = currentSelectedPiece;
        showLastMove(lastMove);
        showAllowedMoves();
    }

    private void updatePieces() {
        this.removeAll();
        this.add(this.tablePanel, Integer.valueOf(0));
        for (Piece piece : board.pieceByLocation.values()) setPiece(piece);
    }

    private void showAllowedMoves() {
        if (currentSelectedPiece == null) return;
        this.tablePanel.getLocationPanel(currentSelectedPiece.getLocation()).setState(LocationPanel.State.SELECTED);
        for (Move move : moveController.getAllowedMoves(currentSelectedPiece)) this.tablePanel.getLocationPanel(move.getEndpointLocation()).setState(LocationPanel.State.SUGGESTED);
    }

    private void showLastMove(Move move) {
        if (move == null) return;
        this.tablePanel.getLocationPanel(move.getStartpointLocation()).setState(LocationPanel.State.LASTMOVE);
        this.tablePanel.getLocationPanel(move.getEndpointLocation()).setState(LocationPanel.State.LASTMOVE);
    }

    public boolean applyMove(Move move) {
        if (move != null && moveController.applyMove(move)) {
            setLastMove(move);
            return true;
        }
        return false;
    }

    public void update() {
        this.tablePanel.resetStates();
        updatePieces();
        showLastMove(lastMove);
        showAllowedMoves();
        validate();
    }

    @Override
    public void locationSelected(Location location, MouseEvent e) {
        if (!board.isValidLocation(location)) return;
        if (currentSelectedPiece == null) {
            if (!board.isOccupied(location)) return;
            Piece piece = board.getPiece(location);
            if (moveController.isAllowedToMove(piece)) setCurrentSelectedPiece(piece);
        } else if (!currentSelectedPiece.getLocation().equals(location)) {
            Move move = moveController.makeMove(currentSelectedPiece, location);
            setCurrentSelectedPiece(null);
            if (applyMove(move)) updatePieces();
            else if (board.isOccupied(location)) locationSelected(location, e);
        } else setCurrentSelectedPiece(null);
    }


    @Override
    public boolean pieceCanBeDragged(Piece piece) {
        return moveController.isAllowedToMove(piece);
    }

    @Override
    public void pieceGrabbed(Piece piece, MouseEvent e) {
        setCurrentSelectedPiece(piece);
    }

    @Override
    public void pieceDropped(Piece piece, MouseEvent e) {
        Location location = this.tablePanel.locationOf(e.getPoint());
        if (!piece.getLocation().equals(location)) {
            Move move = moveController.makeMove(currentSelectedPiece, location);
            setCurrentSelectedPiece(null);
            if (applyMove(move)) updatePieces();;
        }
    }
}
