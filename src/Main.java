import com.amirhn.Game.Chess;
import com.amirhn.Moves.MoveType;

public class Main {
    public static void main(String[] args) {
        Chess chess = new Chess();
        System.out.println(chess);
        for (int i = 0; i < 10; i++) {
            System.out.println();
            var moves = chess.getNaturalMoves();
            var move = moves.get((int) (Math.random() * moves.size()));
            if (move.type == MoveType.CAPTURE) System.err.println(move);
            else System.out.println(move);
            chess.applyMove(move);
            System.out.println(chess);
            chess.undoMove(move);
            System.out.println(chess);
            chess.applyMove(move);
        }
    }
}
