package com.codecool.dungeoncrawl.persistance;

import com.codecool.dungeoncrawl.persistance.Data.GameState;
import com.codecool.dungeoncrawl.persistance.Data.GameStateDaoJdbc;
import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.data.GameData;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CrawlerDataBaseManager {
    private final GameData gameData;
    private GameStateDaoJdbc gameStateJdbc;
    private AssetDaoJdbc assetDaoJdbc;
    private Map<String, String> env;

    public CrawlerDataBaseManager(GameData gameData) {
        this.gameData = gameData;
        env = System.getenv();
        setUp();
        }

    private DataSource connect() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName(env.get("database"));
        dataSource.setUser(env.get("user"));
        dataSource.setPassword(env.get("password"));
        System.out.println("Trying to connect...");
        try {
            dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("Connection Problems!");
        }
        System.out.println("Connection OK");
        return dataSource;
    }

    private void setUp() {
        DataSource dataSource = connect();
        assetDaoJdbc = new AssetDaoJdbc(dataSource);
        gameStateJdbc = new GameStateDaoJdbc(dataSource);
    }

    public List<GameState> getGameStates() {
        return gameStateJdbc.getAll();
    }

    public boolean isNameProvided(String name) {
        return false;
    }

    public void saveGameData(String name) {
        setUp();
        try (Connection con = connect().getConnection()) {
            int gameStateId = gameStateJdbc.safe(name);
            List<Asset> assets = gameData.assetCollection().getAssets();
            assets.forEach(asset -> assetDaoJdbc.safe(asset, gameStateId));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadGameData() {



    }

}
