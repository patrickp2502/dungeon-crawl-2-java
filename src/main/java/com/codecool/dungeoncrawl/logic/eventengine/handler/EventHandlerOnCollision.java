package com.codecool.dungeoncrawl.logic.eventengine.handler;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.eventengine.EventEngine;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventAssetCollision;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventCombatStart;
import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;
import com.codecool.dungeoncrawl.logic.eventengine.fightable;

import java.util.Set;

public class EventHandlerOnCollision implements GameEventHandler {
    private Set<Class<? extends GameEvent>> gameEventClasses;

    public EventHandlerOnCollision(Set<Class<? extends GameEvent>> gameEventClasses) {
        this.gameEventClasses = gameEventClasses;
    }

    @Override
    public void setGameEvents(Set<Class<? extends GameEvent>> gameEventClasses) {

    }

    @Override
    public Set<Class<? extends GameEvent>> getGameEvents() {
        return gameEventClasses;
    }

    //TODO throw test a wrong event --> see whats throwing
    @Override
    public void handle(GameEvent event) {
        EventAssetCollision eventAssetCollision = (EventAssetCollision) event;
        Asset colliding = eventAssetCollision.collides();
        Asset getsCollided = eventAssetCollision.getCollided();
        delegateActions(colliding, getsCollided);
    }

    private void delegateActions(Asset colliding, Asset getsCollided) {
        if (colliding instanceof fightable && getsCollided instanceof fightable) {
            EventEngine.getInstance().addEvent(new EventCombatStart(colliding, getsCollided));
        }


    }

}
