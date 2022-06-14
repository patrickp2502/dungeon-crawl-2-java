package com.codecool.dungeoncrawl.logic.eventengine.handler;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.display.Display;
import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.List;
import java.util.Set;

public class EventHandlerEndRound implements GameEventHandler {
    private Set<Class <? extends GameEvent>> gameEventClasses;

    private final Display display;

    private final List<Label> labels;

    private final List<Button> buttons;

    public EventHandlerEndRound(Set<Class <? extends GameEvent>> eventClassToRegister, Display display,
                                List<Label> labels, List<Button> buttons) {
        this.gameEventClasses = eventClassToRegister;
        this.display = display;
        this.labels = labels;
        this.buttons = buttons;
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
        handleEndRoundTasks();
        Main.turn();
    }

    private void handleEndRoundTasks() {
        Label hintSection = handleHintSection();
        Label inventorySection = handleInventorySection();
        Label buttonLabel = labels
                .stream()
                .filter(label -> label.getText().contains("Buttons"))
                .findFirst()
                .get();
        Button pickUpButton = getPickUpButton();
        if (hintSection.getText().contains("Pick up item")) {
            pickUpButton.setDisable(false);

        } else {
            pickUpButton.setDisable(true);
        }
    }

    private Button getPickUpButton() {
        return buttons
                .stream()
                .filter(button -> button.getText().contains("Pick up"))
                .findFirst()
                .get();
    }

    private Label handleInventorySection() {
        Label inventorySection = getLabelThatHas("Inventory");
        display.drawInventory(inventorySection);
        return inventorySection;
    }

    private Label handleHintSection() {
        Label hintSection = getLabelThatHas("Game hint");


        hintSection.setText(hintSection.getText() + "\n \n");
        display.resetPreviousLine(hintSection);
        return hintSection;
    }

    private Label getLabelThatHas(String text) {
        return labels
                .stream()
                .filter(label -> label.getText().contains(text))
                .findFirst()
                .get();
    }
}
