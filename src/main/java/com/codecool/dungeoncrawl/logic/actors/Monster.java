package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.Random;

public abstract class Monster extends Actor{
    public Monster(Cell cell) {
        super(cell);
    }

    public int[] getDirection(Player player){
        int[][] directions = {{1,0},{0,1},{-1,0},{0,-1}};
        for(int[] direction: directions){
            if(player.getCell() == cell.getNeighbor(direction[0], direction[1])){
                return direction;
            }
        }
        Random rand = new Random();
        int[] pick = directions[rand.nextInt(directions.length)];
        return pick;
    }

    @Override
    protected void attack(Actor enemy) {
        if(enemy instanceof Player) {
            int playerHealth = enemy.getHealth();
            playerHealth -= baseAttack;
            enemy.setHealth(playerHealth);
        }

    }
}
