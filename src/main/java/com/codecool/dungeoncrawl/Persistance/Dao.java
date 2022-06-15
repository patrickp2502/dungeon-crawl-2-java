package com.codecool.dungeoncrawl.Persistance;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> get(long id);
    List<T> getAll();

}
