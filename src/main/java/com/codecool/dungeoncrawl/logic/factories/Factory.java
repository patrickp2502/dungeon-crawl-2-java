package com.codecool.dungeoncrawl.logic.factories;

import com.codecool.dungeoncrawl.logic.Drawable;

public interface Factory {
    public <T extends Drawable> T build();
}
