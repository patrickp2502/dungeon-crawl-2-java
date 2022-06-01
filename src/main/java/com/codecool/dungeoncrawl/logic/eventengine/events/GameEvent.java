package com.codecool.dungeoncrawl.logic.eventengine.events;

public interface GameEvent {
    public <T extends GameEvent> T getEventInformation();
}
