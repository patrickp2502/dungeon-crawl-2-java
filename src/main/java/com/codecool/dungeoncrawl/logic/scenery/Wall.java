package com.codecool.dungeoncrawl.logic.scenery;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.physengine.assetPhysics.IsSolid;

public class Wall extends Asset implements IsSolid, Scenery {
    public Wall(String tileName, int xCoordinate, int yCoordinate) {
        super(tileName, xCoordinate, yCoordinate);
    }
}
