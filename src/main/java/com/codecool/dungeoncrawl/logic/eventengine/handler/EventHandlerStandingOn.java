package com.codecool.dungeoncrawl.logic.eventengine.handler;

import com.codecool.dungeoncrawl.display.Display;
import com.codecool.dungeoncrawl.logic.collectables.Collectable;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventStandingOn;
import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;
import javafx.scene.control.Label;

import java.util.List;
import java.util.Set;

public class EventHandlerStandingOn implements GameEventHandler {
    private Set<Class <? extends GameEvent>> gameEventClasses;

    private final Display display;

    private final List<Label> labels;

    public EventHandlerStandingOn(Set<Class<? extends GameEvent>> gameEventClasses, Display display,
                                  List<Label> labels) {
        this.gameEventClasses = gameEventClasses;
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
        EventStandingOn actualEvent = (EventStandingOn) event;
        Collectable item = (Collectable) actualEvent.item();
        item.setPickUpPossible(true);
        Label hintSection = labels
                .stream()
                .filter(label -> label.getText().contains("Game hint"))
                .findFirst()
                .get();
        display.showNewInformationUnderLabel("\nOOK", hintSection);
        // show pick up button
        // let user pick up item
        // put Item in the Inventory
        // delete Item from game field
    }
}
