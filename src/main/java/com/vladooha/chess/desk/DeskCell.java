package com.vladooha.chess.desk;

public class DeskCell {
    private int x;
    private int y;

    public DeskCell() {
        this.x = 0;
        this.y = 0;
    }

    public DeskCell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public DeskCell(DeskCell deskCell, int xOffset, int yOffset) {
        this.x = deskCell.getX() + xOffset;
        this.y = deskCell.getY() + yOffset;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public boolean isOnDesk(DeskCell lowerBound, DeskCell upperBound) {
        return this.x >= lowerBound.getX() &&
                this.y >= lowerBound.getY() &&
                this.x <= upperBound.getX() &&
                this.y <= upperBound.getY();                        
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof DeskCell)) {
            return false;        
        }    
        
        DeskCell deskCell = (DeskCell) obj;
        if (deskCell.getX() != x || deskCell.getY() != y) {
            return false;        
        }  

        return true;
    }

    @Override
    public int hashCode() {
        return 2 * x - y;
    }
}
