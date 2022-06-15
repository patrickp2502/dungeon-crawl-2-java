package com.codecool.dungeoncrawl.persistance.Data;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameStateDaoJdbc implements GameStateDao {

    private final DataSource dataSource;

    public GameStateDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<GameState> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<GameState> getAll() {
        try (Connection con = dataSource.getConnection()) {
            String sql = "SELECT * FROM game_state";
            ResultSet resultSet = con.createStatement().executeQuery(sql);
            List<GameState> result = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Timestamp timeStamp = resultSet.getTimestamp("time");

                GameState gameState = new GameState(id, name, timeStamp.toLocalDateTime());
                result.add(gameState);
                return result;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;
    }

    /**
     * stores a new game_state
     *
     * @param name user input name
     * @return newId of stored game state
     */
    @Override
    public int safe(String name) {
        try (Connection con = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state (name) VALUES (?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(GameState gameState, String[] params) {

    }

    @Override
    public void delete(GameState gameState) {

    }


}
