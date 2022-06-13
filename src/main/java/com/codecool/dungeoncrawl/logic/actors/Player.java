package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.collections.Inventory;
import com.codecool.dungeoncrawl.logic.eventengine.Fighter;
import com.codecool.dungeoncrawl.logic.movementengine.Moveable;
import com.codecool.dungeoncrawl.logic.movementengine.behaviour.MovementBehaviour;
import com.codecool.dungeoncrawl.logic.movementengine.behaviour.RandomMovementBehaviour;
import com.codecool.dungeoncrawl.logic.physengine.assetPhysics.IsSolid;

public class Player extends Asset implements IsSolid, Moveable, Fighter {

    private MovementBehaviour movementBehaviour;

    private final Inventory inventory;


    public Player(String tileName, int xCoordinate, int yCoordinate) {
        super(tileName, xCoordinate, yCoordinate);
        movementBehaviour = new RandomMovementBehaviour();
        inventory = new Inventory();
    }


    @Override
    public MovementBehaviour getMovementBehaviour() {
        return null;
    }

    public Inventory getInventory() {
        return inventory;
    }


}
