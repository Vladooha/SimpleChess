package com.vladooha;

import java.util.*;

import com.vladooha.chess.desk.Desk;
import com.vladooha.chess.desk.DeskCell;
import com.vladooha.chess.pieces.*;
import com.vladooha.chess.ui.ChessUI;

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
        ChessUI chessUI = new ChessUI(desk);
        
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
                    
                DeskCell prevPos = piece.getPos();
                List<DeskCell> moves = new ArrayList();
                moves.addAll(piece.getMoves(desk.getLowerLimit(), desk.getUpperLimit(), desk.getBusyCells()));

                while (!isMoved && moves.size() > 0) {
                    DeskCell move = moves.get(rand.nextInt(moves.size()));
                    isMoved = desk.move(piece, move);
                    if (isMoved) {
                        break;
                    } else {
                       moves.remove(move);
                    }           
                }
                
                if (moves.size() == 0) {
                    pieces.remove(piece);
                } else if (isMoved) {
                    chessUI.draw(prevPos, piece.getPos());
                    teamTurn = !teamTurn;
                }
            }
    
            try { 
                Thread.sleep(500);
            } catch (InterruptedException e) { }
        }       
    }
}
