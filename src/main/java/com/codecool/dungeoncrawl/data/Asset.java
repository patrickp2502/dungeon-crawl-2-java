package com.codecool.dungeoncrawl.data;

public abstract class Asset {

    private final String tileName;

    private int xCoordinate;

    private int yCoordinate;

    public Asset(String tileName, int xCoordinate, int yCoordinate) {
        this.tileName = tileName;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public String getTileName() {
        return tileName;
    }

    @Override
    public String toString() {
        return tileName;
    }
}
