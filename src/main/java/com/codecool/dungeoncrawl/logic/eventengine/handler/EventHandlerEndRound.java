package com.codecool.dungeoncrawl.logic.eventengine.handler;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.data.DataHub;
import com.codecool.dungeoncrawl.data.GameData;
import com.codecool.dungeoncrawl.logic.eventengine.EventEngine;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventRoundEnd;
import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;

public class EventHandlerEndRound implements GameEventHandler {

    @Override
    public void handle(GameEvent event) {
        Main.turn();
    }
}
