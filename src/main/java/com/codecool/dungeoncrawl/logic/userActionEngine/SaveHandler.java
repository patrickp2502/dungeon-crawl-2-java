package com.codecool.dungeoncrawl.logic.userActionEngine;

import com.codecool.dungeoncrawl.display.Display;
import com.codecool.dungeoncrawl.display.SaveDialog;
import com.codecool.dungeoncrawl.persistance.CrawlerDataBaseManager;
import com.codecool.dungeoncrawl.util.GameInformation;

public class SaveHandler {

    private final GameInformation gameInformation;
    private final Display display;
    private final CrawlerDataBaseManager dataManager;

    public SaveHandler(GameInformation gameInformation, Display display, CrawlerDataBaseManager dataManager) {
        this.gameInformation = gameInformation;
        this.display = display;
        this.dataManager = dataManager;


    }

    public void save() {
        SaveDialog saveDialog = new SaveDialog();
        String input = saveDialog.getInputName();
        dataManager.saveGameData(input);

    }
}
