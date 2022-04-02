import com.amirhn.GUI.ChessFrame;
import com.amirhn.Game.Chess;

public class Main {
    public static void main(String[] args) {
//        ChessFrame chessFrame = new ChessFrame();
        Chess chess = new Chess();
        while (!chess.isInCheck()) {
            System.out.println();
            var moves = chess.getAllowedMoves();
            var move = moves.get((int) (Math.random() * moves.size()));
            System.out.println(move);
            chess.applyMove(move);
            System.out.println(chess);
        }
        System.out.println(chess.getAllowedMoves());
    }
}
