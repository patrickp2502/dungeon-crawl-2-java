package com.codecool.dungeoncrawl.logic.eventengine.events;

import com.codecool.dungeoncrawl.data.Asset;

public record EventStandingOn(Asset item) implements GameEvent{
}
