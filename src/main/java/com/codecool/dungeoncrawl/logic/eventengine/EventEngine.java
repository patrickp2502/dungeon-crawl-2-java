package com.codecool.dungeoncrawl.logic.eventengine;

import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;
import com.codecool.dungeoncrawl.logic.eventengine.handler.GameEventHandler;

import java.util.ArrayList;
import java.util.List;

public final class EventEngine {

    private static EventEngine eventEngineInstance = null;

    private final List<GameEvent> pendingEvents;
    private final List<GameEvent> handledEvents;
    private List<GameEventHandler> eventHandlers;


    private EventEngine() {
        this.pendingEvents = new ArrayList<>();
        this.handledEvents = new ArrayList<>();
    }

    public static EventEngine getInstance() {
        if (eventEngineInstance == null) {
            eventEngineInstance = new EventEngine();
        }
        return eventEngineInstance;
    }

    public void setHandlers(List<GameEventHandler> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }


    public List<GameEvent> getPendingEvents() {
        return pendingEvents;
    }

    public void eventIsHandled(GameEvent event) {
        handledEvents.add(event);
    }

    public void addEvent(GameEvent event) {
        pendingEvents.add(event);
        handleSingleEvent(event);
    }

    private void handleSingleEvent(GameEvent event) {
        System.out.println("event = " + event);
        System.out.println("event.getClass() = " + event.getClass());
        GameEventHandler gameEventHandler = getEventHandler(event);
        gameEventHandler.handle(event);
    }


    private GameEventHandler getEventHandler(GameEvent gameEvent) {
        System.out.println("eventHandlers = " + eventHandlers);
        return eventHandlers.stream()
                .filter(handler -> handler.getGameEvents().stream()
                        .anyMatch(eventClass -> eventClass.isInstance(gameEvent)))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No registered event: " + gameEvent));
    }


    @Override
    public String toString() {
        return "EventEngine{" +
                "pendingEvents=" + pendingEvents +
                '}';
    }
}
