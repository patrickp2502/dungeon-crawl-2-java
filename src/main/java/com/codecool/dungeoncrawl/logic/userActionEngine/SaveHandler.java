package com.codecool.dungeoncrawl.logic.userActionEngine;

import com.codecool.dungeoncrawl.data.GameData;
import com.codecool.dungeoncrawl.display.Display;
import com.codecool.dungeoncrawl.display.SaveDialog;
import com.codecool.dungeoncrawl.persistance.CrawlerDataBaseManager;

public class SaveHandler {

    private final GameData gameData;
    private final Display display;
    private final CrawlerDataBaseManager dataManager;

    public SaveHandler(GameData gameData, Display display, CrawlerDataBaseManager dataManager) {
        this.gameData = gameData;
        this.display = display;
        this.dataManager = dataManager;


    }
    public void save() {
        SaveDialog saveDialog = new SaveDialog();
        String input = saveDialog.getInputName();
        dataManager.saveGameData(input);

    }
}
