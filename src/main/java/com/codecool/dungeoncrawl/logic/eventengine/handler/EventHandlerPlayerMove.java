package com.codecool.dungeoncrawl.logic.eventengine.handler;

import com.codecool.dungeoncrawl.data.GameData;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.eventengine.EventEngine;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventRoundEnd;
import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventPlayerInputMove;
import com.codecool.dungeoncrawl.logic.physengine.PhysEngine;
import jdk.jfr.Event;

//TODO Question - Using static OK? Performance? Advantages? or throwing away? When we don t need the object?
//but what if we need to define interface? Why would we need to define an interface??
public class EventHandlerPlayerMove implements GameEventHandler {
    private int directionX;
    private int directionY;
    private Player player;
    private GameData gameData;


    @Override
    public void handle(GameEvent event) {

        EventPlayerInputMove eventPlayerInputMove = (EventPlayerInputMove) event;
        this.directionX = eventPlayerInputMove.directionX();
        this.directionY = eventPlayerInputMove.directionY();
        this.gameData = eventPlayerInputMove.gameData();
        player = gameData.player();
        movePlayer();
        //TODO ask Mentor wtf eventing eventing eventing...
        EventEngine.getInstance().addEvent(new EventRoundEnd(gameData));
        EventEngine.getInstance().eventIsHandled(eventPlayerInputMove);
    }

    private void movePlayer() {
        int playerXCoordinate = player.getXCoordinate();
        int playerYCoordinate = player.getYCoordinate();
        int newCoordinateX = player.getXCoordinate() + directionX;
        int newCoordinateY = player.getYCoordinate() + directionY;
        PhysEngine physEngine = PhysEngine.getEngine();

        if (physEngine.tryToMove(player, newCoordinateX, newCoordinateY)) {
            player.setXCoordinate(playerXCoordinate+directionX);
            player.setYCoordinate(playerYCoordinate+directionY);
        }
    }
}
