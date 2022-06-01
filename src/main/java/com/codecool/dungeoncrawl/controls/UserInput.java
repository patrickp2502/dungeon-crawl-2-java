package com.codecool.dungeoncrawl.controls;

import com.codecool.dungeoncrawl.data.GameData;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventPlayerInputMove;
import javafx.scene.input.KeyEvent;

public class UserInput {
    private final GameData gameData;

    public UserInput(GameData gameData) {
        this.gameData = gameData;
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                gameData.eventEngine().addEvent(new EventPlayerInputMove(gameData, 0, -1));
                System.out.println("go up");
                break;
            case DOWN:
                gameData.eventEngine().addEvent(new EventPlayerInputMove(gameData, 0, 1));
                break;
            case LEFT:
                gameData.eventEngine().addEvent(new EventPlayerInputMove(gameData, -1, 1));
                break;
            case RIGHT:
                gameData.eventEngine().addEvent(new EventPlayerInputMove(gameData, 1, 0));
                break;
        }
        System.out.println("keyEvent = " + keyEvent);
        System.out.println("gameData = " + gameData.eventEngine().getPendingEvents());
    }
}
