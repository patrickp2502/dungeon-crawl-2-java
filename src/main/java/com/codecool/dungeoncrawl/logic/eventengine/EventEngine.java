package com.codecool.dungeoncrawl.logic.eventengine;

import com.codecool.dungeoncrawl.logic.eventengine.events.EventRoundEnd;
import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventPlayerInputMove;
import com.codecool.dungeoncrawl.logic.eventengine.handler.EventHandlerEndRound;
import com.codecool.dungeoncrawl.logic.eventengine.handler.EventHandlerPlayerMove;

import java.util.ArrayList;
import java.util.List;

public final class EventEngine {

    private static EventEngine eventEngineInstance = null;
    private final List<GameEvent> pendingEvents;
    private final List<GameEvent> handledEvents;


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



    public List<GameEvent> getPendingEvents() {
        return pendingEvents;
    }

    public void eventIsHandled(GameEvent event) {
        handledEvents.add(event);
    }

    public void addEvent(GameEvent event) {
        pendingEvents.add(event);
    }

    public void handle() {

        for (int i = 0; i < pendingEvents.size(); i++) {
            GameEvent event = pendingEvents.get(i);
            switch (event){
                case EventPlayerInputMove e -> new EventHandlerPlayerMove().handle(e);
                case EventRoundEnd e -> new EventHandlerEndRound().handle(e);
                default -> throw new IllegalStateException("Unexpected value: " + event);
            }
            pendingEvents.remove(event);
        }

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




    }

    @Override
    public String toString() {
        return "EventEngine{" +
                "pendingEvents=" + pendingEvents +
                '}';
    }
}
