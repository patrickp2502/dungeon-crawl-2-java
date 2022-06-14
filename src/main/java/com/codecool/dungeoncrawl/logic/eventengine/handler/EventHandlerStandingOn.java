package com.codecool.dungeoncrawl.logic.eventengine.handler;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.display.Display;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.collectables.Collectable;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventStandingOn;
import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.List;
import java.util.Set;


public class EventHandlerStandingOn implements GameEventHandler {
    private final Display display;
    private final List<Label> labels;
    private final List<Button> buttons;
    private final Player player;
    private final List<Asset> assets;
    private Set<Class<? extends GameEvent>> gameEventClasses;

    public EventHandlerStandingOn(Set<Class<? extends GameEvent>> gameEventClasses, Display display,
                                  List<Label> labels, List<Button> buttons, Player player, List<Asset> assets) {
        this.gameEventClasses = gameEventClasses;
        this.display = display;
        this.labels = labels;
        this.buttons = buttons;
        this.player = player;
        this.assets = assets;
    }

    @Override
    public Set<Class<? extends GameEvent>> getGameEvents() {
        return gameEventClasses;
    }

    @Override
    public void setGameEvents(Set<Class<? extends GameEvent>> gameEventClasses) {
        this.gameEventClasses = gameEventClasses;
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
        Label inventorySection = labels
                        .stream()
                        .filter(label -> label.getText().contains("Inventory"))
                        .findFirst()
                        .get();
        display.showNewInformationUnderLabel("\nPick up item with button", hintSection);
        // let user pick up item
        Button pickUpButton = getPickUpButton();
        pickUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                player.getInventory().addItem(item);
                deleteItem(item);
                display.showNewInformationUnderLabel("\nCollecting " + item + " worked!", hintSection);
                pickUpButton.setDisable(true);
                display.drawInventory(inventorySection);
            }
        });
    }

    private void deleteItem(Collectable item) {
        assets.remove(item);
    }

    private Button getPickUpButton() {
        return buttons
                .stream()
                .filter(button -> button.getText().contains("Pick up"))
                .findFirst()
                .get();
    }
}
