package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.physengine.assetPhysics.isSolid;

public class Skeleton extends Asset implements Moveable, isSolid {

    public Skeleton(String tileName, int xCoordinate, int yCoordinate) {
        super(tileName, xCoordinate, yCoordinate);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
