package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.movementengine.Moveable;
import com.codecool.dungeoncrawl.logic.movementengine.behaviour.MovementBehaviour;
public class Skeleton extends Asset implements Moveable, isSolid {

    public Skeleton(String tileName, int xCoordinate, int yCoordinate) {
        super(tileName, xCoordinate, yCoordinate);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public MovementBehaviour getMovementBehaviour() {
        return null;
    }
}
