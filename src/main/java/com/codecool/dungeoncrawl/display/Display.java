package com.codecool.dungeoncrawl.display;

public interface Display {
    public <T extends Displayable> void display(T displayable);
}
