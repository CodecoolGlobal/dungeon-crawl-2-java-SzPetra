package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Monster {


    public Skeleton(Cell cell) {
        super(cell);
        baseAttack = 2;
        health = 20;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

}
