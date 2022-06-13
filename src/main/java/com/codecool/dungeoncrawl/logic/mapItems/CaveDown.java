package com.codecool.dungeoncrawl.logic.mapItems;

import com.codecool.dungeoncrawl.logic.Cell;

public class CaveDown extends MapItem{

    public CaveDown(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "caveDown";
    }
}
