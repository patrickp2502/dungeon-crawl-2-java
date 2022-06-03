package com.codecool.dungeoncrawl.logic.scenery;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.physengine.assetPhysics.IsSolid;

public class DoorClosed extends Asset implements Scenery, IsSolid {

    public DoorClosed(String tileName, int xCoordinate, int yCoordinate) {
        super(tileName, xCoordinate, yCoordinate);
    }
}
