import com.amirhn.GUI.ChessFrame;
import com.amirhn.Game.Chess;
import com.amirhn.Moves.MoveType;

public class Main {
    public static void main(String[] args) {
//        ChessFrame chessFrame = new ChessFrame();
        Chess chess = new Chess();
        System.out.println(chess);
        for (int i = 0; i < 10; i++) {
            System.out.println();
            var moves = chess.getNaturalMoves();
            var move = moves.get((int) (Math.random() * moves.size()));
            if (move.type == MoveType.CAPTURE) System.err.println(move);
            System.out.println(move);
            chess.applyMove(move);
            System.out.println(chess);
        }
    }
}
