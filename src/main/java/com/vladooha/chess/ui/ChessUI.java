package com.vladooha.chess.ui;

import com.vladooha.chess.desk.Desk;
import com.vladooha.chess.desk.DeskCell;
import com.vladooha.chess.pieces.ChessPiece;

public class ChessUI {
    private Desk desk;

    public ChessUI(Desk desk) {
        this.desk = desk;
    }

    public void draw(DeskCell prevPos, DeskCell currPos) {
        int width = (desk.getUpperLimit().getX() - desk.getLowerLimit().getX()) * 2 + 3;
        int height = desk.getUpperLimit().getY() - desk.getLowerLimit().getY() + 3;
        char[][] playGround = new char[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (i == 0 || i == height - 1) {
                    playGround[i][j] = '-';
                } else if (j == 0 || j == width - 1) {
                    playGround[i][j] = '|';
                } else {
                    playGround[i][j] = ' ';
                }
            }
        }
        playGround[0][0] = '+';
        playGround[0][width - 1] = '+';
        playGround[height - 1][0] = '+';
        playGround[height - 1][width - 1] = '+';

        for (ChessPiece piece : desk.getPieces()) {
            int x = piece.getPos().getX() - desk.getLowerLimit().getX();
            int y = piece.getPos().getY() - desk.getLowerLimit().getY();
            if (piece.getTeam()) {
                playGround[y + 1][x*2 + 1] = Character.toUpperCase(piece.getPic());
            } else {
                playGround[y + 1][x*2 + 1] = Character.toLowerCase(piece.getPic());
            }
        }

        StringBuilder deskStr = new StringBuilder();
        for (int i = 0; i < height; ++i) {
            deskStr.append(playGround[i]);
            deskStr.append("\n");
        }

        System.err.println(deskStr.toString());
        System.err.println("Chess pieces left: " + desk.getBusyCells().size());
        System.err.println(
                String.format(
                        "[%c%d] -> [%c%d]",
                        (int) 'A' + prevPos.getX() - 1,
                        prevPos.getY(),
                        (int) 'A' + currPos.getX() - 1,
                        currPos.getY()));
    }
}
