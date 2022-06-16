package com.codecool.dungeoncrawl.persistance.Data;

import java.util.List;
import java.util.Optional;

public interface GameStateDao {
    int safe(String name);

    Optional<GameState> get(long id);

    List<GameState> getAll();

    void update(GameState gameState, String[] params);

    void delete(GameState gameState);
}
