package com.codecool.dungeoncrawl.controls;

import com.codecool.dungeoncrawl.data.GameData;
import com.codecool.dungeoncrawl.display.SaveDialog;
import com.codecool.dungeoncrawl.logic.eventengine.EventEngine;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventPlayerInputMove;
import com.codecool.dungeoncrawl.logic.userActionEngine.SaveHandler;
import javafx.scene.control.Dialog;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.awt.*;

public class UserInput {
    private final GameData gameData;
    private final EventEngine eventEngine;
    private final SaveHandler saveHandler;

    public UserInput(GameData gameData, EventEngine eventEngine, SaveHandler saveHandler) {
        this.gameData = gameData;
        this.eventEngine = eventEngine;
        this.saveHandler = saveHandler;
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
            case S: if (keyEvent.isControlDown()) {
                    saveHandler.save();
                }
                break;


        }
    }
}
