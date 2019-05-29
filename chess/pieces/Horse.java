package chess.pieces;

import java.util.*;
import chess.DeskCell;

public class Horse extends ChessPiece {
    protected static final char pic = 'H';   
    
    public Horse(
            boolean team, 
            DeskCell currentPosition) {
        super(pic, team, currentPosition);
    }
    
    @Override
    public Set<DeskCell> getAvailableMoves(DeskCell lowerLimit, DeskCell upperLimit, List<DeskCell> otherPieces) {
        Set<DeskCell> result = new HashSet<>();
        result.add(new DeskCell(currentPosition, 1, 2));
        result.add(new DeskCell(currentPosition, 1, -2));
        result.add(new DeskCell(currentPosition, -1, 2));
        result.add(new DeskCell(currentPosition, -1, -2));
        result.add(new DeskCell(currentPosition, 2, 1));
        result.add(new DeskCell(currentPosition, 2, -1));
        result.add(new DeskCell(currentPosition, -2, 1));
        result.add(new DeskCell(currentPosition, -2, -1));        

        return result;
    }
}
