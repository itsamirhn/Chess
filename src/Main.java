import com.amirhn.Game.Chess;

public class Main {
    public static void main(String[] args) {
        Chess chess = new Chess();
        System.out.println(chess.getBoard());
        System.out.println(chess.blackPlayer.activePieces.get(1).naturalMoves(chess.getBoard()));
    }
}
