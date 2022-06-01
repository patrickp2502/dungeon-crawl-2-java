package com.codecool.dungeoncrawl.logic.eventengine;

import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventPlayerInputMove;
import com.codecool.dungeoncrawl.logic.eventengine.handler.EventHandlerPlayerMove;

import java.util.ArrayList;
import java.util.List;

public class EventEngine {

    private final List<GameEvent> pendingEvents;

    public EventEngine() {
        this.pendingEvents = new ArrayList<>();
    }

    public List<GameEvent> getPendingEvents() {
        return pendingEvents;
    }


    public void addEvent(GameEvent event) {
        System.out.println("add event" + event);
        pendingEvents.add(event);
        handle();
    }

    public void handle() {
        for (GameEvent event: pendingEvents) {
            if (event instanceof EventPlayerInputMove) {
                new EventHandlerPlayerMove().handle(event);
            }
        }
    }

    @Override
    public String toString() {
        return "EventEngine{" +
                "pendingEvents=" + pendingEvents +
                '}';
    }
}
