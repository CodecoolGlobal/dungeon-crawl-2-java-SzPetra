package com.codecool.dungeoncrawl.logic.mapItems;

import com.codecool.dungeoncrawl.logic.Cell;

public class CaveCornerUpLeft extends MapItem{

    public CaveCornerUpLeft(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "caveCornerUpLeft";
    }
}
