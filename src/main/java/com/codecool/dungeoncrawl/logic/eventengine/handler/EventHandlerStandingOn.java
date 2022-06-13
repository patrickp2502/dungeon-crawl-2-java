package com.codecool.dungeoncrawl.logic.eventengine.handler;

import com.codecool.dungeoncrawl.logic.collectables.Collectable;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventStandingOn;
import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;

import java.util.Set;

public class EventHandlerStandingOn implements GameEventHandler {
    private Set<Class <? extends GameEvent>> gameEventClasses;

    public EventHandlerStandingOn(Set<Class<? extends GameEvent>> gameEventClasses) {
        this.gameEventClasses = gameEventClasses;
    }

    @Override
    public void setGameEvents(Set<Class<? extends GameEvent>> gameEventClasses) {
        this.gameEventClasses = gameEventClasses;
    }

    @Override
    public Set<Class<? extends GameEvent>> getGameEvents() {
        return gameEventClasses;
    }


    @Override
    public void handle(GameEvent event) {
        EventStandingOn actualEvent = (EventStandingOn) event;
        Collectable item = (Collectable) actualEvent.item();
        item.setPickUpPossible(true);
        if (item.isPickUpPossible()) {
            System.out.println("OOOKK LETTTSSS GOO");
        }
        // show pick up button
        // let user pick up item
        // put Item in the Inventory
        // delete Item from game field
    }
}
