package com.codecool.dungeoncrawl.logic.eventengine;

import com.codecool.dungeoncrawl.logic.eventengine.events.EventRoundEnd;
import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventPlayerInputMove;
import com.codecool.dungeoncrawl.logic.eventengine.handler.EventHandlerEndRound;
import com.codecool.dungeoncrawl.logic.eventengine.handler.EventHandlerPlayerMove;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public final class EventEngine {

    private static EventEngine eventEngineInstance = null;

    private final Stack<GameEvent> gameEventStack = new Stack<>();
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
        gameEventStack.add(event);
    }

    public void handle() {
        /*
        Stack<GameEvent> gameEventStack = new Stack<>();
        Iterator<GameEvent> iterator = gameEventStack.listIterator();
        while (iterator.hasNext()) {
            System.out.println(pendingEvents);
            GameEvent event = gameEventStack.pop();
            switch (event){
                case EventPlayerInputMove e -> new EventHandlerPlayerMove().handle(e);
                case EventRoundEnd e -> new EventHandlerEndRound().handle(e);
                default -> throw new IllegalStateException("Unexpected value: " + event);
            }
        }
        */


        for (int i = 0; i < pendingEvents.size(); i++) {
            GameEvent event = pendingEvents.get(i);
            switch (event){
                case EventPlayerInputMove e -> new EventHandlerPlayerMove().handle(e);
                case EventRoundEnd e -> new EventHandlerEndRound().handle(e);
                default -> throw new IllegalStateException("Unexpected value: " + event);
            }
            pendingEvents.remove(event);
            System.out.println("PendingEvent "+ pendingEvents);
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
