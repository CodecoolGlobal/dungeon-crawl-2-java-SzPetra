package com.codecool.dungeoncrawl.logic.mapItems;

import com.codecool.dungeoncrawl.logic.Cell;

public class Path extends MapItem{

    public Path(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "path";
    }
}
