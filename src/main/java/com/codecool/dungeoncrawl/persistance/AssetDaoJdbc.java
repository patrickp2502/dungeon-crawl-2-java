package com.codecool.dungeoncrawl.persistance;

import com.codecool.dungeoncrawl.data.Asset;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AssetDaoJdbc implements AssetDao {

    private final DataSource dataSource;


    public AssetDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Optional<Asset> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Asset> getAll() {
        return null;
    }

    @Override
    public void safe(Asset asset, int gameStateId) {
        try (Connection con = dataSource.getConnection()) {

            String sql = "INSERT INTO asset (name, x, y, game_state_id)" +
                    "VALUES (?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, asset.getTileName());
            statement.setInt(2, asset.getXCoordinate());
            statement.setInt(3, asset.getYCoordinate());
            statement.setInt(4, gameStateId);

            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(Asset asset, String[] params) {

    }

    @Override
    public void delete(Asset asset) {

    }

}
