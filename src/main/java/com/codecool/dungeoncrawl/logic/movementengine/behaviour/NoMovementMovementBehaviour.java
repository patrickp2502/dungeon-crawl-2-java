package com.codecool.dungeoncrawl.logic.movementengine.behaviour;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.movementengine.Moveable;
import com.codecool.dungeoncrawl.logic.physengine.PhysEngine;
import com.codecool.dungeoncrawl.util.GameInformation;

public class NoMovementMovementBehaviour implements MovementBehaviour {
    @Override
    public <T extends Asset & Moveable> void move(T moveableAsset, PhysEngine physEngine, GameInformation gameInformation) {

    }
}
