package com.codecool.dungeoncrawl.logic.eventengine.events;

public interface Event {
    public <T extends Event> T getEventInformation();
}
