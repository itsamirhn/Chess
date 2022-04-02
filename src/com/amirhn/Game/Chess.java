package com.amirhn.Game;


import com.amirhn.Moves.Capture;
import com.amirhn.Moves.Move;
import com.amirhn.Moves.MoveType;
import com.amirhn.Pieces.*;
import com.amirhn.Players.BlackPlayer;
import com.amirhn.Players.Player;
import com.amirhn.Players.WhitePlayer;

import java.util.Collections;
import java.util.List;

public class Chess {

    private Board board;
    public WhitePlayer whitePlayer;
    public BlackPlayer blackPlayer;
    public int turn = 0;

    public Chess() {
        this.whitePlayer = new WhitePlayer();
        this.blackPlayer = new BlackPlayer();
        this.setupChessBoard();
    }

    public void setupChessBoard() {
        this.board = new Board(8, 8);

        setPiece(new Rook(Color.WHITE, Location.valueOf(0, 0)));
        setPiece(new Knight(Color.WHITE, Location.valueOf(0, 1)));
        setPiece(new Bishop(Color.WHITE, Location.valueOf(0, 2)));
        setPiece(new Queen(Color.WHITE, Location.valueOf(0, 3)));
        setPiece(new King(Color.WHITE, Location.valueOf(0, 4)));
        setPiece(new Bishop(Color.WHITE, Location.valueOf(0, 5)));
        setPiece(new Knight(Color.WHITE, Location.valueOf(0, 6)));
        setPiece(new Rook(Color.WHITE, Location.valueOf(0, 7)));
        for (int i = 0; i < 8; i++) setPiece(new Pawn(Color.WHITE, Location.valueOf(1, i)));

        setPiece(new Rook(Color.BLACK, Location.valueOf(7, 0)));
        setPiece(new Knight(Color.BLACK, Location.valueOf(7, 1)));
        setPiece(new Bishop(Color.BLACK, Location.valueOf(7, 2)));
        setPiece(new Queen(Color.BLACK, Location.valueOf(7, 3)));
        setPiece(new King(Color.BLACK, Location.valueOf(7, 4)));
        setPiece(new Bishop(Color.BLACK, Location.valueOf(7, 5)));
        setPiece(new Knight(Color.BLACK, Location.valueOf(7, 6)));
        setPiece(new Rook(Color.BLACK, Location.valueOf(7, 7)));
        for (int i = 0; i < 8; i++) setPiece(new Pawn(Color.BLACK, Location.valueOf(6, i)));

    }

    public void setupCustomScenario() {
        this.board = new Board(8, 8);

        setPiece(new King(Color.WHITE, Location.valueOf("h1")));
        setPiece(new Queen(Color.WHITE, Location.valueOf("c5")));


        setPiece(new King(Color.BLACK, Location.valueOf("a8")));
        setPiece(new Pawn(Color.BLACK, Location.valueOf("f2")));
        setPiece(new Pawn(Color.BLACK, Location.valueOf("g3")));
        setPiece(new Bishop(Color.BLACK, Location.valueOf("f3")));

    }

    public void setPiece(Piece piece) {
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
        return getPlayer(getTurnColor());
    }

    public Player getOpponentPlayer() {
        return getPlayer(getTurnColor().opposite());
    }

    public Player getPlayer(Color color) {
        if (color == Color.WHITE) return whitePlayer;
        return blackPlayer;
    }

    public List<Move> getNaturalMoves() {
        return getTurnPlayer().getNaturalMoves(board);
    }
    public List<Move> getAllowedMoves() {
        if (isCheckmate()) return Collections.emptyList();
        return getTurnPlayer().getAllowedMoves(this);
    }

    public boolean isAllowed(Move move) {
        return move.isAllowed(this);
    }

    public boolean applyMove(Move move) {
        if (!isAllowed(move)) return false;
        if (!move.applyOnBoard(board)) return false;
        if (move.type == MoveType.CAPTURE) {
            Piece capturedPiece = ((Capture) move).capturePiece;
            getTurnPlayer().capturedPieces.add(capturedPiece);
        }
        turn += 1;
        return true;
    }

    public boolean isInCheck() {
        King king = getTurnPlayer().getKing(board);
        return getOpponentPlayer().isThreatening(board, king.getLocation());
    }

    public boolean isCheckmate() {
        if (!isInCheck()) return false;
        return getTurnPlayer().getKing(board).getAllowedMoves(this).isEmpty();
    }

    @Override
    public String toString() {
        return "Turn: " + getTurnColor() + "\n" + board;
    }
}
