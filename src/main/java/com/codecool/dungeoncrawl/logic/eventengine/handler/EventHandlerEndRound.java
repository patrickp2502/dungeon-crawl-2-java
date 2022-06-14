package com.codecool.dungeoncrawl.logic.eventengine.handler;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.display.Display;
import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;
import javafx.scene.control.Label;

import java.util.List;
import java.util.Set;

public class EventHandlerEndRound implements GameEventHandler {
    private Set<Class <? extends GameEvent>> gameEventClasses;

    private final Display display;

    private final List<Label> labels;

    public EventHandlerEndRound(Set<Class <? extends GameEvent>> eventClassToRegister, Display display,
                                List<Label> labels) {
        this.gameEventClasses = eventClassToRegister;
        this.display = display;
        this.labels = labels;
    }


    @Override
    public void setGameEvents(Set<Class<? extends GameEvent>> gameEventClasses) {
        this.gameEventClasses = gameEventClasses;
    }

    @Override
    public Set<Class<? extends GameEvent>> getGameEvents() {
        return gameEventClasses;
    }

    @Override
    public void handle(GameEvent event) {
        Label hintSection = labels
                .stream()
                .filter(label -> label.getText().contains("Game hint"))
                .findFirst()
                .get();

        Label inventorySection = labels
                .stream()
                        .filter(label -> label.getText().contains("Inventory"))
                                .findFirst()
                                        .get();
        display.resetPreviousLine(hintSection);
        display.drawInventory(inventorySection);
        Main.turn();
    }
}
