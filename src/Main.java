import com.amirhn.GUI.ChessFrame;
import com.amirhn.Game.Chess;
import com.amirhn.Moves.MoveType;
import com.amirhn.Pieces.PieceType;

import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
//        ChessFrame chessFrame = new ChessFrame();
        Chess chess = new Chess();
        chess.setupCustomScenario();
        System.out.println(chess);
        System.out.println(chess.getAllowedMoves());
//        while (chess.getAllowedMoves().stream().noneMatch(move -> move.type == MoveType.CASTLING)) {
//            if (chess.isCheckmate()) {
//                System.out.println("CHECKMATE");
//                break;
//            }
//            if (chess.isStalemate()) {
//                System.out.println("STALEMATE");
//                break;
//            }
//            System.out.println();
//            var moves = chess.getAllowedMoves();
//            var move = moves.get((int) (Math.random() * moves.size()));
//            System.out.println(move);
//            chess.applyMove(move);
//            System.out.println(chess);
//        }
    }
}
