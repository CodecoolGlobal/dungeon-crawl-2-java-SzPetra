package com.codecool.dungeoncrawl.logic.playerItems;

import com.codecool.dungeoncrawl.logic.Cell;

public class BlueKey extends PlayerItem {

    public BlueKey(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "key";
    }
}
