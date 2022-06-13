package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Keeper extends Monster{

    public Keeper(Cell cell) {
        super(cell);
        health = 10;
        baseAttack = 3;
    }

    @Override
    public String getTileName() {
        return "keeper";
    }

    @Override
    public void move(int dx, int dy){
        Cell nextCell = cell.getNeighbor(dx, dy);
        if( nextCell.getActor() instanceof Player){
            attack(nextCell.getActor());
        }
    }
}
