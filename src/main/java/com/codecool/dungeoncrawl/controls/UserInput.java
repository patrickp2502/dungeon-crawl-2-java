package com.codecool.dungeoncrawl.controls;

import com.codecool.dungeoncrawl.logic.eventengine.EventEngine;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventPlayerInputMove;
import com.codecool.dungeoncrawl.logic.userActionEngine.SaveHandler;
import com.codecool.dungeoncrawl.util.GameInformation;
import javafx.scene.input.KeyEvent;

public class UserInput {
    private final GameInformation gameInformation;
    private final EventEngine eventEngine;
    private final SaveHandler saveHandler;

    public UserInput(GameInformation gameInformation, EventEngine eventEngine, SaveHandler saveHandler) {
        this.gameInformation = gameInformation;
        this.eventEngine = eventEngine;
        this.saveHandler = saveHandler;
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                eventEngine.addEvent(new EventPlayerInputMove(gameInformation, 0, -1));
                break;
            case DOWN:
                eventEngine.addEvent(new EventPlayerInputMove(gameInformation, 0, 1));
                break;
            case LEFT:
                eventEngine.addEvent(new EventPlayerInputMove(gameInformation, -1, 0));
                break;
            case RIGHT:
                eventEngine.addEvent(new EventPlayerInputMove(gameInformation, 1, 0));
                break;
            case S: if (keyEvent.isControlDown()) {
                    saveHandler.save();
                }
                break;


        }
    }
}
