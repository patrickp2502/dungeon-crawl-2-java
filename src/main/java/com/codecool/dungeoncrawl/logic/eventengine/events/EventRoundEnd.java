package com.codecool.dungeoncrawl.logic.eventengine.events;

import com.codecool.dungeoncrawl.data.GameData;

public record EventRoundEnd(GameData gameData) implements GameEvent{

}
