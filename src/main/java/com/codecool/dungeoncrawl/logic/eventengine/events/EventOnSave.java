package com.codecool.dungeoncrawl.logic.eventengine.events;

import com.codecool.dungeoncrawl.display.Display;
import com.codecool.dungeoncrawl.util.GameInformation;

public record EventOnSave(Display display, GameInformation gameInformation, String saveName) implements GameEvent{
}
