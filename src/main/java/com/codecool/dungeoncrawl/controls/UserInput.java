package com.codecool.dungeoncrawl.controls;

import com.codecool.dungeoncrawl.data.GameData;
import com.codecool.dungeoncrawl.logic.eventengine.EventEngine;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventPlayerInputMove;
import javafx.scene.input.KeyEvent;

public class UserInput {
    private final GameData gameData;
    private final EventEngine eventEngine;

    public UserInput(GameData gameData, EventEngine eventEngine) {
        this.gameData = gameData;
        this.eventEngine = eventEngine;
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                eventEngine.addEvent(new EventPlayerInputMove(gameData, 0, -1));
                break;
            case DOWN:
                eventEngine.addEvent(new EventPlayerInputMove(gameData, 0, 1));
                break;
            case LEFT:
                eventEngine.addEvent(new EventPlayerInputMove(gameData, -1, 0));
                break;
            case RIGHT:
                eventEngine.addEvent(new EventPlayerInputMove(gameData, 1, 0));
                break;
        }
        eventEngine.handle();
    }
}
