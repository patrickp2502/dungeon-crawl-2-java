package com.codecool.dungeoncrawl.logic.input;

import com.codecool.dungeoncrawl.data.GameData;
import com.codecool.dungeoncrawl.logic.eventengine.EventEngine;
import com.codecool.dungeoncrawl.logic.eventengine.events.playerMoveEvent;
import javafx.scene.input.KeyEvent;

public class UserInput {
    private EventEngine eventEngine;
    private GameData gameData;

    public UserInput(EventEngine eventEngine, GameData gameData) {
        this.eventEngine = eventEngine;
        this.gameData = gameData;
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                eventEngine.addEvent(new playerMoveEvent());
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1,0);
                refresh();
                break;
        }
}
