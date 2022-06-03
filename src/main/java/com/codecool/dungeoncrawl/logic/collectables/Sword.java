package com.codecool.dungeoncrawl.logic.collectables;

import com.codecool.dungeoncrawl.data.Asset;

public class Sword extends Asset implements Collectable {

    public Sword(String tileName, int xCoordinate, int yCoordinate) {
        super(tileName, xCoordinate, yCoordinate);
    }
}
