package com.codecool.dungeoncrawl.logic.eventengine;

import com.codecool.dungeoncrawl.data.GameData;
import com.codecool.dungeoncrawl.logic.eventengine.events.Event;

import java.util.ArrayList;
import java.util.List;

public class EventEngine {

    private final List<Event> pendingEvents;

    public EventEngine() {
        this.pendingEvents = new ArrayList<>();
    }

    public List<Event> getPendingEvents() {
        return pendingEvents;
    }


    public void addEvent(Event event) {
        System.out.println("add event" + event);
        pendingEvents.add(event);
    }

    public void handle() {
        for (Event event: pendingEvents) {
            System.out.println("event = " + event);

        }
    }

    @Override
    public String toString() {
        return "EventEngine{" +
                "pendingEvents=" + pendingEvents +
                '}';
    }
}
