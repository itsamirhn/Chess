package com.amirhn.Game;


import com.amirhn.Moves.*;
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
        this("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    }

    public Chess(String fen) {
        this.setupFEN(fen);
    }

    public void setupFEN(String fen) {
        String[] parts = fen.split(" ");
        board = Board.fromFEN(parts[0]);
        turn = Color.fromString(parts[1]);
        whitePlayer.hadShortCastling = true;
        whitePlayer.hadLongCastling = true;
        blackPlayer.hadShortCastling = true;
        blackPlayer.hadLongCastling = true;
        if (!parts[2].equals("-")) {
            for (char c : parts[2].toCharArray()) {
                if (Character.toLowerCase(c) == 'k') getPlayer(Color.valueOfPieceChar(c)).hadShortCastling = false;
                if (Character.toLowerCase(c) == 'q') getPlayer(Color.valueOfPieceChar(c)).hadLongCastling = false;
            }
        }

        // TODO: en passant
        // TODO: move count
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

    public Move getRandomMove() {
        List<Move> moves = getAllowedMoves();
        if (moves.isEmpty()) return null;
        return moves.get((int) (Math.random() * moves.size()));
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
        if (move.type == MoveType.CASTLING) {
            if (move instanceof ShortCastling) getTurnPlayer().hadShortCastling = true;
            if (move instanceof LongCastling) getTurnPlayer().hadLongCastling = true;
        }
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
