package com.codecool.dungeoncrawl.logic.eventengine;

import com.codecool.dungeoncrawl.logic.eventengine.events.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventCollection {
    private final List<Event> eventList;
    public EventCollection() {
        eventList = new ArrayList<>();
    }
    public Optional<Event> getLatestEvent() {
        if (eventList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(eventList.get(eventList.size()-1));
    }
    public void addEvent(Event event) {
        eventList.add(event);
    }

    public void moveOnTop(Event event) {

    }
}
