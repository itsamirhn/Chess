package com.amirhn.Game;


import com.amirhn.Moves.*;
import com.amirhn.Pieces.King;
import com.amirhn.Pieces.Piece;
import com.amirhn.Pieces.PieceType;
import com.amirhn.Players.Player;

import java.util.ArrayList;
import java.util.List;

public class Chess {

    private Board board;
    public Player whitePlayer = new Player(Color.WHITE);
    public Player blackPlayer = new Player(Color.BLACK);
    public List<Move> moves = new ArrayList<>();
    public Color turn = Color.WHITE;
    public List<Scene> history = new ArrayList<>();

    public Chess() {
        this("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    }

    public Chess(String fen) {
        this.setupFEN(fen);
        history.add(new Scene(board.copy(), turn));
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
        history.add(new Scene(board.copy(), turn));
        return true;
    }

    public boolean isInCheck() {
        King king = getTurnPlayer().getKing(board);
        return getOpponentPlayer().isThreatening(board, king.getLocation());
    }

    public boolean isCheckmate() {
        if (!isInCheck()) return false;
        return getAllowedMoves().isEmpty();
    }

    public boolean isStalemate() {
        if (isInCheck()) return false;
        return getTurnPlayer().getAllowedMoves(this).isEmpty();
    }

    public boolean isThreefoldRepetition() {
        Scene currentScene = history.getLast();
        int count = 0;
        for (Scene scene : history) {
            if (scene.equals(currentScene)) count++;
        }
        return count >= 3;
    }

    public boolean is50MoveRule() {
        if (moves.size() < 50) return false;
        return moves.subList(moves.size() - 50, moves.size()).stream().noneMatch(move -> move.type == MoveType.CAPTURE || move.piece.type == PieceType.PAWN);
    }

    public boolean isDraw() {
        return is50MoveRule() || isThreefoldRepetition();
    }

    public Status getStatus() {
        if (isCheckmate()) return Status.CHECKMATE;
        if (isStalemate()) return Status.STALEMATE;
        if (isDraw()) return Status.DRAW;
        return Status.ONGOING;
    }

    @Override
    public String toString() {
        return "Turn: " + turn + "\n" + board;
    }
}
