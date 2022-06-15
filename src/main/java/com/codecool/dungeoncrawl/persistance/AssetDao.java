package com.codecool.dungeoncrawl.persistance;

import com.codecool.dungeoncrawl.data.Asset;

import java.util.List;
import java.util.Optional;

public interface AssetDao {
    Optional<Asset> get(long id);
    List<Asset> getAll();
    void safe(Asset asset, int gameStateId);
    void update(Asset asset, String[] params);
    void delete(Asset asset);

  }
