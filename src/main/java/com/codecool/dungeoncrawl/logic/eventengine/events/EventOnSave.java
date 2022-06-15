package com.codecool.dungeoncrawl.logic.eventengine.events;

import com.codecool.dungeoncrawl.data.GameData;
import com.codecool.dungeoncrawl.display.Display;

public record EventOnSave(Display display, GameData gameData, String saveName) implements GameEvent{
}
