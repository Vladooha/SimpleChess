package com.vladooha.chess.pieces;

import java.util.*;

import com.vladooha.chess.desk.DeskCell;

public class Pawn extends ChessPiece {
    protected static final char pic = 'p';
    protected boolean isFirstMove;    
    
    public Pawn(
            boolean team, 
            DeskCell currentPosition) {
        super(pic, team, currentPosition);
        isFirstMove = true;
    }
    
    @Override
    public Set<DeskCell> getMoves(DeskCell lowerLimit, DeskCell upperLimit, List<DeskCell> otherPieces) {
        Set<DeskCell> result = new HashSet<>();
        int direction = team ? 1 : -1;        
        
        DeskCell simpleMove = new DeskCell(currentPosition, 0, direction * 1);
        if (!otherPieces.contains(simpleMove)) {
            if (simpleMove.isOnDesk(lowerLimit, upperLimit)) {
                result.add(simpleMove);
            }

            DeskCell longMove = new DeskCell(currentPosition, 0, direction * 2);
            if (isFirstMove && !otherPieces.contains(longMove) && longMove.isOnDesk(lowerLimit, upperLimit)) {
                result.add(longMove);
            }
        }
        
        DeskCell eatLeft = new DeskCell(currentPosition, -1, direction * 1);
        if (eatLeft.isOnDesk(lowerLimit, upperLimit) && otherPieces.contains(eatLeft)) {
            result.add(eatLeft);
        }
        DeskCell eatRight = new DeskCell(currentPosition, 1, direction * 1);
        if (eatRight.isOnDesk(lowerLimit, upperLimit) && otherPieces.contains(eatRight)) {
            result.add(eatRight);
        }        

        return result;
    }

    @Override
    public void setPos(DeskCell deskCell) {
        super.setPos(deskCell);
        isFirstMove = false;    
    }
}
