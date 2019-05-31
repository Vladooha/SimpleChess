package com.vladooha.chess.pieces;

import java.util.*;
import com.vladooha.chess.desk.DeskCell;

public class Queen extends ChessPiece {
    protected static final char pic = 'Q';   
    
    public Queen(
            boolean team, 
            DeskCell currentPosition) {
        super(pic, team, currentPosition);
    }
    
    @Override
    public Set<DeskCell> getMoves(DeskCell lowerLimit, DeskCell upperLimit, List<DeskCell> otherPieces) {
        Set<DeskCell> result = new HashSet<>();
        Rook rook = new Rook(team, currentPosition);
        Bishop bishop = new Bishop(team, currentPosition);
        result.addAll(rook.getMoves(lowerLimit, upperLimit, otherPieces));
        result.addAll(bishop.getMoves(lowerLimit, upperLimit, otherPieces));

        return result;
    }
}
