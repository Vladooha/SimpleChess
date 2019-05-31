package com.vladooha.chess.desk;

import java.util.*;

import com.vladooha.chess.pieces.*;
//import DeskCell;

public class Desk {
    private List<ChessPiece> pieces;
    private DeskCell lowerLimit;
    private DeskCell upperLimit;

    private King blackKing;
    private King whiteKing;

    public Desk() {
        this(1, 1, 8, 8);

        for (int x = 1; x <= 8; ++x) {
            addPiece(new Pawn(true, new DeskCell(x, 2)));
            addPiece(new Pawn(false, new DeskCell(x, 7)));
        }
        
        addPiece(new Rook(true, new DeskCell(1, 1)));
        addPiece(new Rook(true, new DeskCell(8, 1)));
        addPiece(new Rook(false, new DeskCell(1, 8)));
        addPiece(new Rook(false, new DeskCell(8, 8)));
        
        addPiece(new Horse(true, new DeskCell(2, 1)));
        addPiece(new Horse(true, new DeskCell(7, 1)));
        addPiece(new Horse(false, new DeskCell(2, 8)));
        addPiece(new Horse(false, new DeskCell(7, 8)));

        addPiece(new Bishop(true, new DeskCell(3, 1)));
        addPiece(new Bishop(true, new DeskCell(6, 1)));
        addPiece(new Bishop(false, new DeskCell(3, 8)));
        addPiece(new Bishop(false, new DeskCell(6, 8)));
        
        addPiece(new Queen(true, new DeskCell(4, 1)));
        addPiece(new Queen(false, new DeskCell(4, 8)));
        
        whiteKing = new King(true, new DeskCell(5, 1));
        blackKing = new King(false, new DeskCell(5, 8));
        addPiece(whiteKing);
        addPiece(blackKing);
    }

    public Desk(int lowerX, int lowerY, int upperX, int upperY) {
        lowerLimit = new DeskCell(lowerX, lowerY);
        upperLimit = new DeskCell(upperX, upperY);

        pieces = new ArrayList<>();
    }

    public boolean addPiece(ChessPiece piece) {
/*
        for (ChessPiece anotherPiece : pieces) {
            if (piece.getPos().equals(piece.getPos())) {
                return false;
            }
        }
*/
        pieces.add(piece);

        return true;
    }

    public boolean removePiece(ChessPiece piece) {
        return pieces.remove(piece);    
    }

    public List<ChessPiece> getPieces() {
        return pieces;
    }

    /*
    public Set<DeskCell> removeNotAllowed(Set<DeskCell> cells, boolean team) {
        Iterator<DeskCell> iter = cells.iterator();
 
        while(iter.hasNext()) {
            DeskCell cell = iter.next();
            for (ChessPiece piece : pieces) {
                if (piece.getPos().equals(cell)) {
                    if (piece.getTeam() == team) {
                        iter.remove();
                    }
                    
                    break;
                }
            }
        }
    }
*/

    public boolean move(ChessPiece piece, DeskCell dest) {
        if (!dest.isOnDesk(lowerLimit, upperLimit)) {
            return false;        
        }

        Iterator<ChessPiece> iter = pieces.iterator();
        ChessPiece eaten = null;
 
        while(iter.hasNext()) {
            ChessPiece anotherPiece = iter.next();
            if (anotherPiece.getPos().equals(dest)) {
                if (piece.getTeam() == anotherPiece.getTeam()) {
                    return false;
                } else {
                    iter.remove();
                    eaten = anotherPiece;
                }
                
                break;
            }
        }
        
        DeskCell oldPos = piece.getPos();
        piece.setPos(dest);
        

        if (checkShah(piece.getTeam())) {
            if (eaten != null) {            
                pieces.add(eaten);
            }

            piece.setPos(oldPos);

            return false;
        }      
        
        return true;
    }

    public DeskCell getLowerLimit() {
        return new DeskCell(lowerLimit, 0, 0);
    }

    public DeskCell getUpperLimit() {
        return new DeskCell(upperLimit, 0, 0);
    }

    public List<DeskCell> getBusyCells() {
        List<DeskCell> result = new ArrayList();
        for (ChessPiece piece : pieces) {
            result.add(piece.getPos());
        }
        return result;
    }

    public boolean checkShah(boolean kingTeam) {
        List<DeskCell> busyCells = getBusyCells();
        King king = kingTeam ? whiteKing : blackKing;

        for (ChessPiece piece : pieces) {
            if (piece.getTeam() != kingTeam && 
                    piece.getMoves(lowerLimit, upperLimit, busyCells).contains(king.getPos())) {

                return true;
            }
        }

        return false;
    }
}
