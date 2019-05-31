package com.vladooha.chess.pieces;

import java.util.*;
import com.vladooha.chess.desk.DeskCell;

public class Rook extends ChessPiece {
    protected static final char pic = 'R';   
    
    public Rook(
            boolean team, 
            DeskCell currentPosition) {
        super(pic, team, currentPosition);
    }
    
    @Override
    public Set<DeskCell> getMoves(DeskCell lowerLimit, DeskCell upperLimit, List<DeskCell> otherPieces) {
        Set<DeskCell> result = new HashSet<>();

        Set<DeskCell> bottom = new HashSet<>();
        for (int y = lowerLimit.getY(); y < currentPosition.getY(); ++y) {
            DeskCell newCell = new DeskCell(currentPosition.getX(), y);
            if (otherPieces.contains(newCell)) {
                bottom.clear();
            } 
            
            bottom.add(newCell);          
        }

        Set<DeskCell> upper = new HashSet<>();
        for (int y = upperLimit.getY(); y > currentPosition.getY(); --y) {
            DeskCell newCell =  new DeskCell(currentPosition.getX(), y);
            if (otherPieces.contains(newCell)) {
                upper.clear();
            }
                 
            upper.add(newCell);
        }

        Set<DeskCell> left = new HashSet<>();
        for (int x = lowerLimit.getX(); x < currentPosition.getX(); ++x) {
            DeskCell newCell =  new DeskCell(x, currentPosition.getY());
            if (otherPieces.contains(newCell)) {
                left.clear();
            }
 
            left.add(newCell);            
        }

        Set<DeskCell> right = new HashSet<>();
        for (int x = upperLimit.getX(); x > currentPosition.getX(); --x) {
            DeskCell newCell =  new DeskCell(x, currentPosition.getY());
            if (otherPieces.contains(newCell)) {
                right.clear();
            } 

            right.add(newCell);
        }

        result.addAll(bottom);
        result.addAll(upper);
        result.addAll(right);
        result.addAll(left);
        result.remove(currentPosition);

        return result;
    }
}
