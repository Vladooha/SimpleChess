package chess.pieces;

import java.util.*;
import chess.DeskCell;

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
    
    public void setCurrentPosition(DeskCell deskCell) {
        this.currentPosition = deskCell;    
    }

    public DeskCell getCurrentPosition() {
        return currentPosition;    
    }
    
    public abstract Set<DeskCell> getAvailableMoves(DeskCell lowerLimit, DeskCell upperLimit, List<DeskCell> otherPieces);
}
