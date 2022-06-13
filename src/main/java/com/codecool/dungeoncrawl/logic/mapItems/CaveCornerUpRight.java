package com.codecool.dungeoncrawl.logic.mapItems;

import com.codecool.dungeoncrawl.logic.Cell;

public class CaveCornerUpRight extends MapItem{
    public CaveCornerUpRight(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "caveCornerUpRight";
    }
}
