package com.codecool.dungeoncrawl.logic.eventengine.events;

import com.codecool.dungeoncrawl.util.GameInformation;

public record EventPlayerInputMove(GameInformation gameInformation, int directionX,
                                   int directionY) implements GameEvent {


    @Override
    public String toString() {
        return "playerMoveEvent{" +
                "player=" + gameInformation.getAssetCollection().getPlayer().get() +
                ", directionX=" + directionX +
                ", directionY=" + directionY +
                '}';
    }


}
