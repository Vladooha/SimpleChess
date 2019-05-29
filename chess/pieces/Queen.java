package chess.pieces;

import java.util.*;
import chess.DeskCell;

public class Queen extends ChessPiece {
    protected static final char pic = 'Q';   
    
    public Queen(
            boolean team, 
            DeskCell currentPosition) {
        super(pic, team, currentPosition);
    }
    
    @Override
    public Set<DeskCell> getAvailableMoves(DeskCell lowerLimit, DeskCell upperLimit, List<DeskCell> otherPieces) {
        Set<DeskCell> result = new HashSet<>();
        Rook rook = new Rook(team, currentPosition);
        Bishop bishop = new Bishop(team, currentPosition);
        result.addAll(rook.getAvailableMoves(lowerLimit, upperLimit, otherPieces));
        result.addAll(bishop.getAvailableMoves(lowerLimit, upperLimit, otherPieces));

        return result;
    }
}
