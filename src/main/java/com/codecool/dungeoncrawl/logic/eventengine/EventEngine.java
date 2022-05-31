package com.codecool.dungeoncrawl.logic.eventengine;

import com.codecool.dungeoncrawl.data.GameData;
import com.codecool.dungeoncrawl.logic.eventengine.events.Event;

import java.util.ArrayList;
import java.util.List;

public class EventEngine {

    private final List<Event> pendingEvents;
    private final GameData gameData;

    public EventEngine(GameData gameData) {
        this.pendingEvents = new ArrayList<>();
        this.gameData = gameData;
    }

    public List<Event> getPendingEvents() {
        return pendingEvents;
    }

    public GameData getGameData() {
        return gameData;
    }

    public void addEvent(Event event) {
        pendingEvents.add(event);
    }

    public void handle() {
        System.out.println("Eventhandler handle");
    }

    @Override
    public String toString() {
        return "EventEngine{" +
                "pendingEvents=" + pendingEvents +
                ", gameData=" + gameData +
                '}';
    }
}
