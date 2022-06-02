package com.codecool.dungeoncrawl.logic.scenery;

import com.codecool.dungeoncrawl.data.Asset;

public class Wall extends Asset implements Scenery {
    public Wall(String tileName, int xCoordinate, int yCoordinate) {
        super(tileName, xCoordinate, yCoordinate);
    }
}
