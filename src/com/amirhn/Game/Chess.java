package com.amirhn.Game;


import com.amirhn.Moves.Move;
import com.amirhn.Pieces.*;
import com.amirhn.Players.BlackPlayer;
import com.amirhn.Players.Player;
import com.amirhn.Players.WhitePlayer;

import java.util.List;

public class Chess {

    private Board board;
    public WhitePlayer whitePlayer;
    public BlackPlayer blackPlayer;
    public int turn = 0;

    public Chess() {
        this.whitePlayer = new WhitePlayer();
        this.blackPlayer = new BlackPlayer();
        this.resetBoard();
    }

    public void resetBoard() {
        this.board = new Board(8, 8);

        this.setPiece(new Rook(Color.WHITE, Location.valueOf(0, 0)));
        this.setPiece(new Knight(Color.WHITE, Location.valueOf(0, 1)));
        this.setPiece(new Bishop(Color.WHITE, Location.valueOf(0, 2)));
        this.setPiece(new Queen(Color.WHITE, Location.valueOf(0, 3)));
        this.setPiece(new King(Color.WHITE, Location.valueOf(0, 4)));
        this.setPiece(new Bishop(Color.WHITE, Location.valueOf(0, 5)));
        this.setPiece(new Knight(Color.WHITE, Location.valueOf(0, 6)));
        this.setPiece(new Rook(Color.WHITE, Location.valueOf(0, 7)));
        for (int i = 0; i < 8; i++) this.setPiece(new Pawn(Color.WHITE, Location.valueOf(1, i)));

        this.setPiece(new Rook(Color.BLACK, Location.valueOf(7, 0)));
        this.setPiece(new Knight(Color.BLACK, Location.valueOf(7, 1)));
        this.setPiece(new Bishop(Color.BLACK, Location.valueOf(7, 2)));
        this.setPiece(new Queen(Color.BLACK, Location.valueOf(7, 3)));
        this.setPiece(new King(Color.BLACK, Location.valueOf(7, 4)));
        this.setPiece(new Bishop(Color.BLACK, Location.valueOf(7, 5)));
        this.setPiece(new Knight(Color.BLACK, Location.valueOf(7, 6)));
        this.setPiece(new Rook(Color.BLACK, Location.valueOf(7, 7)));
        for (int i = 0; i < 8; i++) this.setPiece(new Pawn(Color.BLACK, Location.valueOf(6, i)));

    }

    public void setPiece(Piece piece) {
        if (piece.color == Color.WHITE) { this.whitePlayer.activePieces.add(piece); }
        if (piece.color == Color.BLACK) { this.blackPlayer.activePieces.add(piece); }
        this.board.setPiece(piece);
    }

    public Board getBoard() {
        return board;
    }

    public Color getTurnColor() {
        if (this.turn % 2 == 0) return Color.WHITE;
        else return  Color.BLACK;
    }

    public Player getTurnPlayer() {
        if (this.getTurnColor() == Color.WHITE) return this.whitePlayer;
        else return this.blackPlayer;
    }

    public List<Move> getNaturalMoves() {
        return getTurnPlayer().getNaturalMoves(this.board);
    }

    public boolean applyMove(Move move) {
        if (move.applyOnBoard(this.board)) {
            turn += 1;
            return true;
        } else return false;
    }

    public boolean undoMove(Move move) {
        if (move.undoOnBoard(this.board)) {
            turn -= 1;
            return true;
        } else return false;
    }

    @Override
    public String toString() {
        return "Turn: " + getTurnColor() + "\n" + board;
    }
}
