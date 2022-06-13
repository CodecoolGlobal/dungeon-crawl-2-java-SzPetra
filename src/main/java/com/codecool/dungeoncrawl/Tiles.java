package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();

    public static class Tile {
        public final int x, y, w, h;

        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 1));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("justPlayer", new Tile(25, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("sword", new Tile(2, 30));
        tileMap.put("gate", new Tile(9, 11));
        tileMap.put("openedGate", new Tile(12, 11));
        tileMap.put("key", new Tile(17, 23));
        tileMap.put("caveUp", new Tile(19, 0));
        tileMap.put("caveCornerUpLeft", new Tile(18, 0));
        tileMap.put("caveCornerUpRight", new Tile(20, 0));
        tileMap.put("caveWall", new Tile(18, 1));
        tileMap.put("caveCornerDownLeft", new Tile(18, 2));
        tileMap.put("caveCornerDownRight", new Tile(20, 2));
        tileMap.put("caveDown", new Tile(19, 2));
        tileMap.put("redKey", new Tile(18, 23));
        tileMap.put("door", new Tile(5, 4));
        tileMap.put("path", new Tile(5, 0));
        tileMap.put("water", new Tile(8, 5));
        tileMap.put("bridge", new Tile(7, 5));
        tileMap.put("wood", new Tile(6, 2));
        tileMap.put("waterPoison", new Tile(26, 23));
        tileMap.put("keeper", new Tile(25, 8));
        tileMap.put("exit", new Tile(7, 9));
        tileMap.put("openExit", new Tile(6, 9));
        tileMap.put("apple", new Tile(15, 29));
        tileMap.put("closeStick", new Tile(3, 10));
        tileMap.put("openStick", new Tile(4, 10));
        tileMap.put("woodGate", new Tile(1, 3));
        tileMap.put("openWoodGate", new Tile(4, 3));


        tileMap.put("ghost", new Tile(27, 6));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}