package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ghost extends Monster{

    public Ghost(Cell cell) {
        super(cell);
        baseAttack = 1;
        health = 10;
    }

    @Override
    public String getTileName() {
        return "ghost";
    }

    @Override
    public void move(int dx, int dy){
        Cell nextCell = getCell().getNeighbor(dx, dy);
        if (nextCell.getActor() == null){
            cell.setActor(null);
            cell = nextCell;
            cell.setActor(this);
        } else if(nextCell.getActor() instanceof Player) {
            attack(nextCell.getActor());
        }
    }

    @Override
    public int[] getDirection(Player player){
        Cell target = player.getCell();
        int distanceX = Math.abs(target.getX() - cell.getX());
        int distanceY = Math.abs(target.getY() - cell.getY());
        if(distanceX > distanceY){
            if(target.getX() > cell.getX()){
                return new int[]{1,0};
            }else {
                return new int[]{-1, 0};
            }
        }else {
            if (target.getY() > cell.getY()) {
                return new int[]{0, 1};
            } else {
                return new int[]{0, -1};
            }
        }
    }
}
