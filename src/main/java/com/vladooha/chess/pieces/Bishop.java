package com.vladooha.chess.pieces;

import java.util.*;
import com.vladooha.chess.desk.DeskCell;

public class Bishop extends ChessPiece {
    protected static final char pic = 'B';   
    
    public Bishop(
            boolean team, 
            DeskCell currentPosition) {
        super(pic, team, currentPosition);
    }
    
    @Override
    public Set<DeskCell> getMoves(DeskCell lowerLimit, DeskCell upperLimit, List<DeskCell> otherPieces) {
        Set<DeskCell> result = new HashSet<>();
        int diff = currentPosition.getY() - currentPosition.getX();
        int sum = currentPosition.getY() + currentPosition.getX();

        Set<DeskCell> bottomLeft = new HashSet<>();
        for (int x = lowerLimit.getX(); x < currentPosition.getX(); ++x) {
            DeskCell newCell = new DeskCell(x, x + diff);
            if (otherPieces.contains(newCell)) {
                bottomLeft.clear();
            } 
                //if (newCell.isOnDesk(lowerLimit, upperLimit))
                    bottomLeft.add(newCell);
                        
        }

        Set<DeskCell> upperLeft = new HashSet<>();
        for (int x = lowerLimit.getX(); x < currentPosition.getX(); ++x) {
            DeskCell newCell =  new DeskCell(x, sum - x);
            if (otherPieces.contains(newCell)) {
                upperLeft.clear();
            } 
                //if (newCell.isOnDesk(lowerLimit, upperLimit))
                    upperLeft.add(newCell);
            
        }

        Set<DeskCell> upperRight = new HashSet<>();
        for (int x = upperLimit.getX(); x > currentPosition.getX(); --x) {
            DeskCell newCell = new DeskCell(x, x + diff);
            if (otherPieces.contains(newCell)) {
                upperRight.clear();
            } 
                //if (newCell.isOnDesk(lowerLimit, upperLimit))
                    upperRight.add(newCell);
                     
        }

        Set<DeskCell> bottomRight = new HashSet<>();
        for (int x = upperLimit.getX(); x > currentPosition.getX(); --x) {
            DeskCell newCell =  new DeskCell(x, sum - x);
            if (otherPieces.contains(newCell)) {
                bottomRight.clear();
            } 
                //if (newCell.isOnDesk(lowerLimit, upperLimit))
                    bottomRight.add(newCell);
             
        }
        result.addAll(bottomLeft);
        result.addAll(upperLeft);
        result.addAll(bottomRight);
        result.addAll(upperRight);
        result.remove(currentPosition);

        return result;
    }
}
