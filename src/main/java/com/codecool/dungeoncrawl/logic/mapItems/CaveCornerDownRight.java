package com.codecool.dungeoncrawl.logic.mapItems;

import com.codecool.dungeoncrawl.logic.Cell;

public class CaveCornerDownRight extends MapItem{

    public CaveCornerDownRight(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "caveCornerDownRight";
    }
}
