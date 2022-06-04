package com.codecool.dungeoncrawl.logic.eventengine;

import com.codecool.dungeoncrawl.logic.eventengine.events.*;
import com.codecool.dungeoncrawl.logic.eventengine.handler.*;

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
    /*TODO Handle singleEvents as they happen! Maybe prepare a Que for pending events!
     *
     */
/*
    private void handleSingleEvent(GameEvent event) {
        switch (event) {
            case EventPlayerInputMove e -> new EventHandlerPlayerMove().handle(e);
            case EventRoundEnd e -> new EventHandlerEndRound().handle(e);
            case EventAssetCollision e -> new EventHandlerOnCollision().handle(e);
            case EventStandingOn e -> new EventHandlerStandingOn().handle(e);
            default -> throw new IllegalStateException("Unexpected value: " + event);
        }

        pendingEvents.remove(event);
    }
*/

    private void handleSingleEvent(GameEvent event) {
        GameEventHandler gameEventHandler = getEventHandler(event);
        gameEventHandler.handle(event);
    }


    private GameEventHandler getEventHandler(GameEvent gameEvent) {
        return eventHandlers.stream()
                .filter(handler -> handler.getGameEvents().stream()
                        .anyMatch(eventClass -> eventClass.isInstance(gameEvent)))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No registered event: " + gameEvent));
    }

    /**
     * this handle function should handle pending Events or a List of
     * TODO Problem adding and removing events, dont be recursive
     */
    /*
    private void handle() {
        int pendingEventCount;
        while (pendingEvents.size() > 0) ;
        {
            for (GameEvent event : pendingEvents) {
                System.out.println("pendingEvents = " + pendingEvents);
                System.out.println("handledEvents = " + handledEvents);
                if (handledEvents.contains(event)) {
                    continue;
                }

                switch (event) {
                    case EventPlayerInputMove e -> new EventHandlerPlayerMove().handle(e);
                    case EventRoundEnd e -> new EventHandlerEndRound().handle(e);
                    case EventAssetCollision e -> new EventHandlerOnCollision().handle(e);
                    default -> throw new IllegalStateException("Unexpected value: " + event);
                }
                handledEvents.add(event);
            }


        }
        for (int i = 0; i < pendingEvents.size(); i++) {
            GameEvent event = pendingEvents.get(i);
            switch (event) {
                case EventPlayerInputMove e -> new EventHandlerPlayerMove().handle(e);
                case EventRoundEnd e -> new EventHandlerEndRound().handle(e);
                default -> throw new IllegalStateException("Unexpected value: " + event);
            }
            pendingEvents.remove(event);
            System.out.println("PendingEvent " + pendingEvents);
        }
*/
/*
        for (GameEvent event: pendingEvents) {
            System.out.println("pendingEvents = " + pendingEvents);
            System.out.println("handledEvents = " + handledEvents);
            if (handledEvents.contains(event)) {
                continue;
            }

            switch (event){
                case EventPlayerInputMove e -> new EventHandlerPlayerMove().handle(e);
                case EventRoundEnd e -> new EventHandlerEndRound().handle(e);
                default -> throw new IllegalStateException("Unexpected value: " + event);
            }
            handledEvents.add(event);
        }
        pendingEvents.clear();*/
        /*
        Iterator<GameEvent> iterator = pendingEvents.listIterator();
        while (iterator.hasNext()) {
            switch (iterator.next()){
                case EventPlayerInputMove e -> new EventHandlerPlayerMove().handle(e);
                case EventRoundEnd e -> new EventHandlerEndRound().handle(e);
                default -> throw new IllegalStateException("Unexpected value: " + iterator.next());
            }
            iterator.remove();
        }*/




    @Override
    public String toString() {
        return "EventEngine{" +
                "pendingEvents=" + pendingEvents +
                '}';
    }
}
