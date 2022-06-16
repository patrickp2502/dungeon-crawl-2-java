package com.codecool.dungeoncrawl.logic.eventengine.events;

import com.codecool.dungeoncrawl.util.GameInformation;

public record EventRoundEnd(GameInformation gameInformation) implements GameEvent{

}
