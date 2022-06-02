package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.data.Asset;

public class Skeleton extends Asset implements Moveable {

    public Skeleton(String tileName, int xCoordinate, int yCoordinate) {
        super(tileName, xCoordinate, yCoordinate);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
