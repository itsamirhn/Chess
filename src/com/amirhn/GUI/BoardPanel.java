package com.amirhn.GUI;

import com.amirhn.Game.Board;
import com.amirhn.Game.Location;
import com.amirhn.Moves.Move;
import com.amirhn.Moves.MoveController;
import com.amirhn.Pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel implements LocationListener {

    private final MoveController moveController;
    private Move lastMove;

    private final Board board;
    private final LocationPanel[][] locationPanels;

    private Piece currentSelectedPiece;

    public BoardPanel(Board board, MoveController moveController) {
        super(new GridLayout(board.rows, board.columns));

        this.moveController = moveController;
        this.board = board;
        this.locationPanels = new LocationPanel[board.rows][board.columns];

        for (int i = board.rows - 1; i >= 0; i--) for (int j = 0; j < board.columns; j++) {
            Location location = Location.valueOf(i, j);
            this.locationPanels[i][j] = new LocationPanel(location, this);
            this.add(locationPanels[i][j]);
        }
        this.update();
        this.validate();
    }

    private void setLastMove(Move lastMove) {
        resetStates(LocationPanel.State.LASTMOVE);
        this.lastMove = lastMove;
        updateLastMove(lastMove);
    }

    private void setCurrentSelectedPiece(Piece currentSelectedPiece) {
        resetStates(LocationPanel.State.SELECTED);
        resetStates(LocationPanel.State.SUGGESTED);
        this.currentSelectedPiece = currentSelectedPiece;
        updateLastMove(lastMove);
        updateAllowedMoves();
    }

    private void resetStates(LocationPanel.State state) {
        for (int i = 0; i < board.rows; i++) for (int j = 0; j < board.columns; j++) {
            LocationPanel locationPanel = getLocationPanel(Location.valueOf(i, j));
            if (locationPanel.getState() == state) locationPanel.setState(LocationPanel.State.NORMAL);
        }
    }

    private void resetStates() {
        for (int i = 0; i < board.rows; i++) for (int j = 0; j < board.columns; j++) {
            getLocationPanel(Location.valueOf(i, j)).setState(LocationPanel.State.NORMAL);
        }
    }

    private void updateBoard() {
        for (int i = 0; i < board.rows; i++) for (int j = 0; j < board.columns; j++) {
            if (board.isOccupied(Location.valueOf(i, j))) getLocationPanel(Location.valueOf(i, j)).setPiece(board.getPiece(Location.valueOf(i, j)));
            else getLocationPanel(Location.valueOf(i, j)).setPiece(null);
        }
    }

    private void updateAllowedMoves() {
        if (currentSelectedPiece == null) return;
        getLocationPanel(currentSelectedPiece.getLocation()).setState(LocationPanel.State.SELECTED);
        for (Move move : moveController.getAllowedMoves(currentSelectedPiece)) getLocationPanel(move.getEndpointLocation()).setState(LocationPanel.State.SUGGESTED);
    }

    private void updateLastMove(Move move) {
        if (move == null) return;
        getLocationPanel(move.getStartpointLocation()).setState(LocationPanel.State.LASTMOVE);
        getLocationPanel(move.getEndpointLocation()).setState(LocationPanel.State.LASTMOVE);
    }

    public void update() {
        resetStates();
        updateBoard();
        updateLastMove(lastMove);
        updateAllowedMoves();
        validate();
    }

    public LocationPanel getLocationPanel(Location location) {
        return this.locationPanels[location.row][location.column];
    }

    @Override
    public void locationSelected(Location location) {
        if (!board.isValidLocation(location)) return;
        if (currentSelectedPiece == null) {
            if (!board.isOccupied(location)) return;
            Piece piece = board.getPiece(location);
            if (moveController.isAllowedToMove(piece)) setCurrentSelectedPiece(piece);
        } else if (!currentSelectedPiece.getLocation().equals(location)) {
            Move move = moveController.makeMove(currentSelectedPiece, location);
            setCurrentSelectedPiece(null);
            if (move != null && moveController.applyMove(move)) {
                setLastMove(move);
                updateBoard();
            } else if (board.isOccupied(location)) locationSelected(location);
        } else setCurrentSelectedPiece(null);
    }
}
