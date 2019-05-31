package com.vladooha.chess.pieces;

import com.vladooha.chess.desk.Desk;
import com.vladooha.chess.desk.DeskCell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RookTest {
    private Rook rook;

    @Before
    public void setUp() throws Exception {
        rook = new Rook(false, new DeskCell(1, 1));
    }

    @After
    public void tearDown() throws Exception {
        rook = null;
    }

    @Test
    public void getMovesTest_2x2Desk_2cells() {
        // Arrange
        DeskCell lowerLimit = new DeskCell(1, 1);
        DeskCell upperLimit = new DeskCell(2, 2);
        List<DeskCell> otherPieces = new ArrayList<>();

        // Act
        Set<DeskCell> moves = rook.getMoves(lowerLimit, upperLimit, otherPieces);

        // Assert
        assertTrue(moves.contains(new DeskCell(1, 2)));
        assertTrue(moves.contains(new DeskCell(2, 1)));
        assertTrue(moves.size() == 2);
    }

    @Test
    public void getMovesTest_DefaultDeskAndPieceDist1Cell_10cells() {
        // Arrange
        rook.setPos(new DeskCell(2, 2));
        DeskCell rookPos = rook.getPos();

        Desk desk = mock(Desk.class);
        List<DeskCell> fakeFigures = new ArrayList<>();
        DeskCell posDist1CellToRook = new DeskCell(rookPos, 2, 0);
        fakeFigures.add(posDist1CellToRook);
        when(desk.getBusyCells()).thenReturn(fakeFigures);
        when(desk.getLowerLimit()).thenReturn(new Desk().getLowerLimit());
        when(desk.getUpperLimit()).thenReturn(new Desk().getUpperLimit());

        // Act
        Set<DeskCell> moves = rook.getMoves(
                desk.getLowerLimit(),
                desk.getUpperLimit(),
                desk.getBusyCells());

        // Assert
        assertTrue(moves.contains(new DeskCell(rookPos, 1, 0)));
        assertTrue(moves.contains(new DeskCell(rookPos, 2, 0)));
        assertFalse(moves.contains(new DeskCell(rookPos, 3, 0)));

        assertTrue(moves.contains(new DeskCell(rookPos, 0, 1)));
        assertTrue(moves.contains(new DeskCell(rookPos, 0, 2)));
        assertTrue(moves.contains(new DeskCell(rookPos, 0, 3)));

        assertFalse(moves.contains(new DeskCell(rookPos, 1, 1)));
        assertFalse(moves.contains(new DeskCell(rookPos, 1, 2)));
        assertFalse(moves.contains(new DeskCell(rookPos, -2, 3)));

        assertTrue(moves.size() == 10);
    }
}