package com.codecool.dungeoncrawl.logic.collectables;

import com.codecool.dungeoncrawl.data.Asset;

public class Key extends Asset implements Collectable {

    public Key(String tileName, int xCoordinate, int yCoordinate) {
        super(tileName, xCoordinate, yCoordinate);
    }
}
