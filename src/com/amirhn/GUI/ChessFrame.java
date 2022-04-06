package com.amirhn.GUI;

import com.amirhn.Game.Chess;
import com.amirhn.Game.Location;
import com.amirhn.Moves.Move;
import com.amirhn.Moves.MoveController;
import com.amirhn.Pieces.Piece;

import javax.swing.*;
import java.util.List;

public class ChessFrame extends JFrame implements MoveController {

    private final Chess chess;
    private final BoardPanel boardPanel;

    public ChessFrame() {
        this(new Chess());
    }

    public ChessFrame(Chess chess) {

        this.chess = chess;

        this.setTitle("Chess");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.boardPanel = new BoardPanel(chess.getBoard(), this);
        this.add(boardPanel);

        this.setResizable(false);
        this.setVisible(true);
        this.pack();
    }

    public void update() {
        boardPanel.update();
        validate();
        repaint();
    }

    @Override
    public boolean isAllowedToMove(Piece piece) {
        return chess.getBoard().isValidPiece(piece) && piece.isAllowedToMove(chess);
    }

    @Override
    public List<Move> getAllowedMoves(Piece piece) {
        return piece.getAllowedMoves(chess);
    }

    @Override
    public Move makeMove(Piece piece, Location endpoint) {
        if (!chess.getBoard().isValidPiece(piece)) return null;
        List<Move> moves = piece.getAllowedMoves(chess).stream().filter(move -> move.getEndpointLocation().equals(endpoint)).toList();
        if (moves.isEmpty()) return null;
        return moves.get(0);
    }

    @Override
    public boolean applyMove(Move move) {
        if (chess.applyMove(move)) {
            update();
            return true;
        }
        return false;
    }
}
