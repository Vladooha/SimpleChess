package com.vladooha.chess.pieces;

import java.util.*;
import com.vladooha.chess.desk.DeskCell;

public abstract class ChessPiece {
    protected char pic;
    protected boolean team;
    protected DeskCell currentPosition;

    public ChessPiece(
                char pic, 
                boolean team, 
                DeskCell currentPosition) {
        this.pic = pic;
        this.team = team;
        this.currentPosition = currentPosition;       
    }

    public char getPic() {
        return pic;
    }

    public boolean getTeam() {
        return team;
    }
    
    public void setPos(DeskCell deskCell) {
        this.currentPosition = deskCell;    
    }

    public DeskCell getPos() {
        return currentPosition;    
    }
    
    public abstract Set<DeskCell> getMoves(DeskCell lowerLimit, DeskCell upperLimit, List<DeskCell> otherPieces);
}
