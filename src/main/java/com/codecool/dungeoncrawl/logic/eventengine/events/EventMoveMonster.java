package com.codecool.dungeoncrawl.logic.eventengine.events;

import com.codecool.dungeoncrawl.data.GameData;

public record EventMoveMonster(GameData gameData, int directionX, int directionY) implements GameEvent {

    @Override
    public <T extends GameEvent> T getEventInformation() {
        return (T) this;
    }

    @Override
    public String toString() {
        return "playerMoveEvent{" +
                "player=" + gameData +
                ", directionX=" + directionX +
                ", directionY=" + directionY +
                '}';
    }
}
