package com.codecool.dungeoncrawl.logic.scenery;

import com.codecool.dungeoncrawl.data.Asset;

public class Floor extends Asset implements Scenery{
    public Floor(String tileName, int xCoordinate, int yCoordinate) {
        super(tileName, xCoordinate, yCoordinate);
    }
}
