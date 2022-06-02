package com.codecool.dungeoncrawl.logic.eventengine.handler;

import com.codecool.dungeoncrawl.logic.eventengine.events.EventAssetCollision;
import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;

public class EventHandlerOnCollision implements GameEventHandler{

    @Override
    public void handle(GameEvent event) {
        EventAssetCollision eventAssetCollision = (EventAssetCollision) event;

        System.out.println("event = " + event);
    }
}
