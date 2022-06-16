package com.codecool.dungeoncrawl.logic.movementengine;

public enum Direction {
    UP(0, -1), DOWN(0, +1), RIGHT(1, 0), LEFT(-1, 0), ZERO(0, 0);

    private final int xDirection;
    private final int yDirection;

    Direction(int x, int y) {
        this.xDirection = x;
        this.yDirection = y;
    }

    public int getDirectionX() {
        return xDirection;
    }

    public int getDirectionY() {
        return yDirection;
    }
}
