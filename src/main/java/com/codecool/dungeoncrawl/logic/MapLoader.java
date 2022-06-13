package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Keeper;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.mapItems.*;
import com.codecool.dungeoncrawl.logic.playerItems.*;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String file) {
        InputStream is = MapLoader.class.getResourceAsStream(file);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            map.addMonster(new Skeleton(cell));
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 't':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case 'g':
                            cell.setType(CellType.WALL);
                            new Gate(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new BlueKey(cell);
                            break;
                        case 'c':
                            cell.setType(CellType.WALL);
                            new CaveUp(cell);
                            break;
                        case 'u':
                            cell.setType(CellType.WALL);
                            new CaveCornerUpRight(cell);
                            break;
                        case 'b':
                            cell.setType(CellType.WALL);
                            new CaveCornerUpLeft(cell);
                            break;
                        case 'o':
                            cell.setType(CellType.WALL);
                            new CaveWall(cell);
                            break;
                        case 'l':
                            cell.setType(CellType.WALL);
                            new CaveCornerDownLeft(cell);
                            break;
                        case 'd':
                            cell.setType(CellType.WALL);
                            new CaveDown(cell);
                            break;
                        case '1':
                            cell.setType(CellType.WALL);
                            new CaveCornerDownRight(cell);
                            break;
                        case 'w':
                            cell.setType(CellType.FLOOR);
                            map.addMonster(new Ghost(cell));
                            break;
                        case 'r':
                            cell.setType(CellType.FLOOR);
                            new RedKey(cell);
                            break;
                        case 'm':
                            cell.setType(CellType.WALL);
                            new Door(cell);
                            break;
                        case 'p':
                            cell.setType(CellType.FLOOR);
                            new Path(cell);
                            break;
                        case 'v':
                            cell.setType(CellType.WALL);
                            new Water(cell);
                            break;
                        case 'h':
                            cell.setType(CellType.FLOOR);
                            new Bridge(cell);
                            break;
                        case 'q':
                            cell.setType(CellType.FLOOR);
                            new WaterPotion(cell);
                            break;
                        case 'n':
                            cell.setType(CellType.FLOOR);
                            map.addMonster(new Keeper(cell));
                            break;
                        case '6':
                            cell.setType(CellType.WALL);
                            new Exit(cell);
                            break;
                        case '8':
                            cell.setType(CellType.FLOOR);
                            new Apple(cell);
                            break;
                        case '0':
                            cell.setType(CellType.WALL);
                            new Stick(cell);
                            break;
                        case '+':
                            cell.setType(CellType.WALL);
                            new WoodGate(cell);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
