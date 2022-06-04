package com.codecool.dungeoncrawl.logic.eventengine;

import com.codecool.dungeoncrawl.logic.eventengine.events.*;
import com.codecool.dungeoncrawl.logic.eventengine.handler.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * In the InitEventHandlers.class you can register new Events to the EvenHandlers and invoke the @gameEventHandlers
 * List, holding GameEventHandlerObjects.{@link #getGameEventHandlers()} method Access the GameEvenHandlers.
 */
public class InitEventHandlers {
    private List<GameEventHandler> gameEventHandlers;

    public InitEventHandlers() {
        //
        gameEventHandlers = new ArrayList<>();
        //Register EventPlayerInputMove.class to EventHandlerPlayerMove
        Set<Class<? extends GameEvent>> playerMoveEvents = new HashSet<>();
        playerMoveEvents.add(EventPlayerInputMove.class);
        gameEventHandlers.add(new EventHandlerPlayerMove(playerMoveEvents));

        //Register EventStandingOn.class to EventHandlerStandingOn
        Set<Class<? extends GameEvent>> standingOnEvents = new HashSet<>();
        standingOnEvents.add(EventStandingOn.class);
        gameEventHandlers.add(new EventHandlerStandingOn(standingOnEvents));

        //Register EventAssetCollision.class to EventHandlerOnCollision
        Set<Class<? extends GameEvent>> onCollisionEvents = new HashSet<>();
        onCollisionEvents.add(EventAssetCollision.class);
        gameEventHandlers.add(new EventHandlerOnCollision(onCollisionEvents));

        //Register EventRoundEnd.class to EventHandlerRoundEnd
        Set<Class<? extends GameEvent>> endRoundEvents = new HashSet<>();
        endRoundEvents.add(EventRoundEnd.class);
        gameEventHandlers.add(new EventHandlerEndRound(endRoundEvents));
    }

    public List<GameEventHandler> getGameEventHandlers() {
        return gameEventHandlers;
    }

}