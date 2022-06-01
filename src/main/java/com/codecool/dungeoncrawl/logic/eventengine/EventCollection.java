package com.codecool.dungeoncrawl.logic.eventengine;

import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventCollection {
    private final List<GameEvent> eventList;
    public EventCollection() {
        eventList = new ArrayList<>();
    }
    public Optional<GameEvent> getLatestEvent() {
        if (eventList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(eventList.get(eventList.size()-1));
    }
    public void addEvent(GameEvent event) {
        eventList.add(event);
    }

    public void moveOnTop(GameEvent event) {

    }
}
