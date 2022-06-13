package com.codecool.dungeoncrawl.logic.playerItems;

import com.codecool.dungeoncrawl.logic.Cell;

public class RedKey extends PlayerItem{

    public RedKey(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "redKey";
    }
}
