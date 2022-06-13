package com.codecool.dungeoncrawl.logic.playerItems;

import com.codecool.dungeoncrawl.logic.Cell;

public class Apple extends PlayerItem implements Consumable{

    private final int healthBooster = 5;
    public Apple(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "apple";
    }

    @Override
    public int consumeItem() {
        return healthBooster;
    }
}
