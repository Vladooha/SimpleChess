package chess.pieces;

import java.util.*;
import chess.DeskCell;

public class Rook extends ChessPiece {
    protected static final char pic = 'R';   
    
    public Rook(
            boolean team, 
            DeskCell currentPosition) {
        super(pic, team, currentPosition);
    }
    
    @Override
    public Set<DeskCell> getAvailableMoves(DeskCell lowerLimit, DeskCell upperLimit, List<DeskCell> otherPieces) {
        Set<DeskCell> result = new HashSet<>();

        Set<DeskCell> bottom = new HashSet<>();
        for (int y = lowerLimit.getY(); y < currentPosition.getY(); ++y) {
            DeskCell newCell = new DeskCell(currentPosition.getX(), y);
            if (otherPieces.contains(newCell)) {
                //System.err.println("Bottom clear");
                bottom.clear();
            } 
                //if (newCell.isOnDesk(lowerLimit, upperLimit))
                    bottom.add(newCell);
                        
        }

        Set<DeskCell> upper = new HashSet<>();
        for (int y = upperLimit.getY(); y > currentPosition.getY(); --y) {
            DeskCell newCell =  new DeskCell(currentPosition.getX(), y);
            if (otherPieces.contains(newCell)) {
                //System.err.println("Upper clear");
                upper.clear();
            } 
                //if (newCell.isOnDesk(lowerLimit, upperLimit))
                    upper.add(newCell);
            
        }

        Set<DeskCell> left = new HashSet<>();
        for (int x = lowerLimit.getX(); x < currentPosition.getX(); ++x) {
            DeskCell newCell =  new DeskCell(x, currentPosition.getY());
            if (otherPieces.contains(newCell)) {
                //System.err.println("Left clear");
                left.clear();
            } 
                //if (newCell.isOnDesk(lowerLimit, upperLimit))
                    left.add(newCell);
                       
        }

        Set<DeskCell> right = new HashSet<>();
        for (int x = upperLimit.getX(); x > currentPosition.getX(); --x) {
            DeskCell newCell =  new DeskCell(x, currentPosition.getY());
            if (otherPieces.contains(newCell)) {
                //System.err.println("Right clear");
                right.clear();
            } 
                //if (newCell.isOnDesk(lowerLimit, upperLimit))
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
