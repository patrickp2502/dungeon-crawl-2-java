package com.codecool.dungeoncrawl.logic.scenery;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.physengine.assetPhysics.isSolid;

public class Wall extends Asset implements isSolid {
    public Wall(String tileName, int xCoordinate, int yCoordinate) {
        super(tileName, xCoordinate, yCoordinate);
    }
}
