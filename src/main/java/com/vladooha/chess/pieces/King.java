package com.vladooha.chess.pieces;

import java.util.*;
import com.vladooha.chess.desk.DeskCell;

public class King extends ChessPiece {
    protected static final char pic = 'K';   
    
    public King(
            boolean team, 
            DeskCell currentPosition) {
        super(pic, team, currentPosition);
    }
    
    @Override
    public Set<DeskCell> getMoves(DeskCell lowerLimit, DeskCell upperLimit, List<DeskCell> otherPieces) {
        Set<DeskCell> result = new HashSet<>();
        int x = currentPosition.getX();
        int y = currentPosition.getY();
        for (int xOffset = -1; xOffset <= 1; ++xOffset) {
            for (int yOffset = -1; yOffset <= 1; ++yOffset) {
                DeskCell newCell = new DeskCell(x + xOffset, y + yOffset);
                if (!otherPieces.contains(newCell) && newCell.isOnDesk(lowerLimit, upperLimit)) {
                    result.add(newCell);
                }                
            }        
        }
        result.remove(currentPosition);

        return result;
    }
}
