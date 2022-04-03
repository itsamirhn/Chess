import com.amirhn.GUI.ChessFrame;
import com.amirhn.Game.Chess;

public class Main {
    public static void main(String[] args) {
//        ChessFrame chessFrame = new ChessFrame();
        Chess chess = new Chess();
        while (!chess.isFinished()) {
            System.out.println();
            var move = chess.applyRandomMove();
            System.out.println(move);
            chess.applyMove(move);
            System.out.println(chess);
        }
        if (chess.isCheckmate()) System.err.println("CHECKMATE");
        else if (chess.isStalemate()) System.err.println("STALEMATE");
        else if (chess.isDraw()) System.err.println("DRAW");
    }
}
