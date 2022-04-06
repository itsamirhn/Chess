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

    public void setCurrentSelectedPiece(Piece currentSelectedPiece) {
        hideAllowedMoves();
        this.currentSelectedPiece = currentSelectedPiece;
        updateAllowedMoves();
    }

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

    public void updateBoard() {
        for (int i = 0; i < board.rows; i++) for (int j = 0; j < board.columns; j++) {
            if (board.isOccupied(Location.valueOf(i, j))) getLocationPanel(Location.valueOf(i, j)).setPiece(board.getPiece(Location.valueOf(i, j)));
            else getLocationPanel(Location.valueOf(i, j)).setPiece(null);
        }
    }

    public void hideAllowedMoves() {
        if (currentSelectedPiece == null) return;
        getLocationPanel(currentSelectedPiece.getLocation()).setDefaultBackground();
        for (Move move : moveController.getAllowedMoves(currentSelectedPiece)) getLocationPanel(move.getEndpointLocation()).setDefaultBackground();
    }

    public void updateAllowedMoves() {
        if (currentSelectedPiece == null) return;
        getLocationPanel(currentSelectedPiece.getLocation()).setSelectedBackground();
        for (Move move : moveController.getAllowedMoves(currentSelectedPiece)) getLocationPanel(move.getEndpointLocation()).setSelectedBackground();
    }

    public void update() {
        hideAllowedMoves();
        updateBoard();
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
            if (!moveController.getAllowedMoves(piece).isEmpty()) setCurrentSelectedPiece(piece);
        } else {
            Move move = moveController.makeMove(currentSelectedPiece, location);
            setCurrentSelectedPiece(null);
            if (move != null && moveController.applyMove(move)) updateBoard();
        }
    }
}
