package com.codecool.dungeoncrawl.logic.movementengine.behaviour;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.data.GameData;
import com.codecool.dungeoncrawl.logic.movementengine.Direction;
import com.codecool.dungeoncrawl.logic.movementengine.Moveable;
import com.codecool.dungeoncrawl.logic.physengine.PhysEngine;

import java.util.Random;

public class RandomMovementBehaviour implements MovementBehaviour {

    @Override
    public <T extends Asset & Moveable> void move(T moveableAsset, PhysEngine physEngine, GameData gameData) {
        if (moveableAsset.getMovementStop()) {
            return;
        }

        int currentX = moveableAsset.getXCoordinate();
        int currentY = moveableAsset.getYCoordinate();
        int newX;
        int newY;
        Random random = new Random();
        do {
            newX = currentX;
            newY = currentY;
            Direction randomDirection = Direction.values()[random.nextInt(Direction.values().length)];
            newX += randomDirection.getDirectionX();
            newY += randomDirection.getDirectionY();
        } while (!physEngine.tryToMove(moveableAsset, newX, newY));

        moveableAsset.setYCoordinate(newY);
        moveableAsset.setXCoordinate(newX);
    }
}
