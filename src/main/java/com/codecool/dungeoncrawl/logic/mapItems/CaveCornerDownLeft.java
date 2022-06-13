package com.codecool.dungeoncrawl.logic.mapItems;

import com.codecool.dungeoncrawl.logic.Cell;

public class CaveCornerDownLeft extends MapItem{

    public CaveCornerDownLeft(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "caveCornerDownLeft";
    }
}
