import java.util.*;
import chess.pieces.ChessPiece;
import chess.*;
import java.lang.StringBuilder;

public class Controller {
    public static void main(String[] args) {
        Controller controller = new Controller();        
        Desk desk = new Desk();
        controller.play(desk);
    }

    public void play(Desk desk) {
        Random rand = new Random();
        boolean teamTurn = true;
        boolean gameOver = false; 
        
        while (!gameOver) {
            List<ChessPiece> pieces = new ArrayList<>();
            pieces.addAll(desk.getPieces());
            boolean isMoved = false;
            while (!isMoved) {
                ChessPiece piece = null;
                Collections.shuffle(pieces);
                for (ChessPiece teamPiece : pieces) {
                    if (teamPiece.getTeam() == teamTurn)
                        piece = teamPiece;
                }

                if (piece == null) {
                    String winner = teamTurn ? "Black" : "White";
                    System.err.println(winner + " wins!");
                    gameOver = true;

                    break;
                }                
                    
                DeskCell prevPos = piece.getCurrentPosition();
                List<DeskCell> moves = new ArrayList();
                moves.addAll(piece.getAvailableMoves(desk.getLowerLimit(), desk.getUpperLimit(), desk.getBusyCells()));

                while (!isMoved && moves.size() > 0) {
                    DeskCell move = moves.get(rand.nextInt(moves.size()));
                    isMoved = desk.move(piece, move);
                    if (isMoved) {
                        break;
                    } else {
                       moves.remove(move);
                    }           
                }
                    
                //System.err.println("Cond " + moves.size() + " " + isMoved + " " + pieces.size());
                if (moves.size() == 0) {
                    pieces.remove(piece);
                } else if (isMoved) {
                    draw(desk, prevPos, piece.getCurrentPosition());
                    teamTurn = !teamTurn;
                }
            }
    
            try { 
                Thread.sleep(1000); 
            } catch (InterruptedException e) { }
        }       
    }

    public void draw(Desk desk, DeskCell prevPos, DeskCell currPos) {
        int width = (desk.getUpperLimit().getX() - desk.getLowerLimit().getX()) * 2 + 5;
        int height = desk.getUpperLimit().getY() - desk.getLowerLimit().getY() + 3;
        char[][] playGround = new char[height][width];  
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (i == 0 || i == height - 1) {
                    playGround[i][j] = '-';
                } else if (j == 0 || j == width - 1) {
                    playGround[i][j] = '|';
                } else {
                    playGround[i][j] = ' ';
                }
            }
        }
        playGround[0][0] = '+';
        playGround[0][width - 1] = '+';
        playGround[height - 1][0] = '+';
        playGround[height - 1][width - 1] = '+';

        for (ChessPiece piece : desk.getPieces()) {
            int x = piece.getCurrentPosition().getX() - desk.getLowerLimit().getX();
            int y = piece.getCurrentPosition().getY() - desk.getLowerLimit().getY();            
            if (piece.getTeam()) {
                playGround[y + 1][x*2 + 1] = Character.toUpperCase(piece.getPic());
            } else {
                playGround[y + 1][x*2 + 1] = Character.toLowerCase(piece.getPic());
            }
        }

        StringBuilder deskStr = new StringBuilder();
        for (int i = 0; i < height; ++i) {
            deskStr.append(playGround[i]);
            deskStr.append("\n");
        }
        System.err.println(deskStr.toString());
        System.err.println("Chess pieces left: " + desk.getBusyCells().size());
        System.err.println(
                String.format(
                        "[%d|%d] -> [%d|%d]",
                        prevPos.getX(),
                        prevPos.getY(),
                        currPos.getX(),
                        currPos.getY()));
    }
}
