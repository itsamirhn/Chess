package com.amirhn.Game;


import com.amirhn.Moves.Capture;
import com.amirhn.Moves.Castling;
import com.amirhn.Moves.Move;
import com.amirhn.Moves.MoveType;
import com.amirhn.Pieces.*;
import com.amirhn.Players.BlackPlayer;
import com.amirhn.Players.Player;
import com.amirhn.Players.WhitePlayer;

import java.util.ArrayList;
import java.util.List;

public class Chess {

    private Board board;
    public WhitePlayer whitePlayer = new WhitePlayer();
    public BlackPlayer blackPlayer = new BlackPlayer();
    public List<Move> moves = new ArrayList<>();
    public Color turn = Color.WHITE;

    public Chess() {
        this.setupStandardChessBoard();
    }

    public void setupFEN(String fen) {
        String[] parts = fen.split(" ");
        this.board = Board.fromFEN(parts[0]);
        this.turn = Color.fromString(parts[1]);
        // TODO: implement castling
        // TODO: en passant
        // TODO: move count
    }

    public void setupStandardChessBoard() {
        setupFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    }

    public void setupCastlingScenario() {
        this.board = new Board(8, 8);

        setPiece(new Rook(Color.WHITE, Location.valueOf(0, 0)));
//        setPiece(new Queen(Color.WHITE, Location.valueOf(0, 3)));
        setPiece(new King(Color.WHITE, Location.valueOf(0, 4)));
        setPiece(new Rook(Color.WHITE, Location.valueOf(0, 7)));
        for (int i = 0; i < 8; i++) setPiece(new Pawn(Color.WHITE, Location.valueOf(1, i)));

        setPiece(new Rook(Color.BLACK, Location.valueOf(7, 0)));
//        setPiece(new Queen(Color.BLACK, Location.valueOf(7, 3)));
        setPiece(new King(Color.BLACK, Location.valueOf(7, 4)));
        setPiece(new Rook(Color.BLACK, Location.valueOf(7, 7)));
        for (int i = 0; i < 8; i++) setPiece(new Pawn(Color.BLACK, Location.valueOf(6, i)));

    }

    public void setupPawnPromotionScenario() {
        this.board = new Board(8, 8);

        setPiece(new King(Color.WHITE, Location.valueOf(3, 2)));
        setPiece(new King(Color.BLACK, Location.valueOf(4, 5)));
        for (int i = 0; i < 8; i++) setPiece(new Pawn(Color.BLACK, Location.valueOf(1, i)));
        for (int i = 0; i < 8; i++) setPiece(new Pawn(Color.WHITE, Location.valueOf(6, i)));

    }

    public void setPiece(Piece piece) {
        this.board.setPiece(piece);
    }

    public Board getBoard() {
        return board;
    }

    public Player getTurnPlayer() {
        return getPlayer(turn);
    }

    public Player getOpponentPlayer() {
        return getPlayer(turn.opposite());
    }

    public Player getPlayer(Color color) {
        if (color == Color.WHITE) return whitePlayer;
        return blackPlayer;
    }

    public List<Move> getNaturalMoves() {
        return getTurnPlayer().getNaturalMoves(board);
    }
    public List<Move> getAllowedMoves() {
        return getTurnPlayer().getAllowedMoves(this);
    }

    public Move applyRandomMove() {
        List<Move> moves = getAllowedMoves();
        if (moves.isEmpty()) return null;
        Move move = moves.get((int) (Math.random() * moves.size()));
        applyMove(move);
        return move;
    }

    public boolean isAllowed(Move move) {
        return move.isAllowed(this);
    }

    public boolean applyMove(Move move) {
        if (move == null) return false;
        if (!isAllowed(move)) return false;
        if (!move.applyOnBoard(board)) return false;
        if (move.type == MoveType.CAPTURE) {
            Piece capturedPiece = ((Capture) move).capturePiece;
            getTurnPlayer().capturedPieces.add(capturedPiece);
        }
//        if (move.type == MoveType.CASTLING) getTurnPlayer().castling = (Castling) move;
        turn = turn.opposite();
        moves.add(move);
        return true;
    }

    public boolean isInCheck() {
        King king = getTurnPlayer().getKing(board);
        return getOpponentPlayer().isThreatening(board, king.getLocation());
    }

    public boolean isCheckmate() {
        if (!isInCheck()) return false;
        return getTurnPlayer().getAllowedMoves(this).isEmpty();
    }

    public boolean isStalemate() {
        if (isInCheck()) return false;
        return getTurnPlayer().getAllowedMoves(this).isEmpty();
    }

    public boolean isThreefoldRepetition() {
        // TODO
        return false;
    }

    public boolean is50MoveRule() {
        if (moves.size() < 50) return false;
        return moves.subList(moves.size() - 50, moves.size()).stream().noneMatch(move -> move.type == MoveType.CAPTURE || move.piece.type == PieceType.PAWN);
    }

    public boolean isDraw() {
        return isStalemate() || is50MoveRule() || isThreefoldRepetition();
    }

    public boolean isFinished() {
        return isCheckmate() || isDraw();
    }

    @Override
    public String toString() {
        return "Turn: " + turn + "\n" + board;
    }
}
