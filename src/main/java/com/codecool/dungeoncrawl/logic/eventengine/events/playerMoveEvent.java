package com.codecool.dungeoncrawl.logic.eventengine.events;

import com.codecool.dungeoncrawl.logic.actors.Player;

public record playerMoveEvent(Player player, int directionX, int directionY) implements Event {

    @Override
    public <T extends Event> T getEventInformation() {
        return (T) this;
    }
}
