package com.codecool.dungeoncrawl.data;

public final class DataHub {

    private static DataHub dataHub = null;
    private final GameData gameData;


    private DataHub(GameData gameData) {
        this.gameData = gameData;
    }


    //todo ask the mentor
    public static void setGameData(GameData gameData) throws Exception {
        if (dataHub != null) {
            throw new Exception("can't override gameData reference");
        }
        dataHub = new DataHub(gameData);

    }
    //todo ask the mentor wtf singleton
    public static DataHub getInstance() throws Exception {
        if (dataHub == null) {
            throw new Exception("Please initialise gameData first with DataHub.setGameData(...)");
        }
        return dataHub;
    }


    public GameData getGameData() {
        return gameData;
    }
}
