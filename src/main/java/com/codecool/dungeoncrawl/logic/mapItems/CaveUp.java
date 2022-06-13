package com.codecool.dungeoncrawl.logic.mapItems;

import com.codecool.dungeoncrawl.logic.Cell;

public class CaveUp extends MapItem{
    public CaveUp(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "caveUp";
    }
}
