package com.codecool.dungeoncrawl.logic.eventengine.handler;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.display.Display;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventStandingOn;
import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;

public class EventHandlerStandingOn implements GameEventHandler{
    @Override
    public void handle(GameEvent event) {
        EventStandingOn actualEvent = (EventStandingOn) event;
        // display.showGameHint("You are standing on a: " + actualEvent.item());
    }
}
