package com.vcg.zombie.service;

import com.vcg.zombie.entity.Human;
import com.vcg.zombie.entity.World;
import com.vcg.zombie.entity.Zombie;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class WorldService {

    private World world;
    private List<Zombie> zombies = new ArrayList<>();

    private static Logger logger = LogManager.getLogger(WorldService.class);

    public List<Zombie> getZombies() {
        return zombies;
    }

    public void execute() {
        zombies.add(world.getZombie());
        printStatus("Start:");
        start();
        printStatus("End:");
    }

    private void start() {
        for (int i = 0; i < world.getCommands().length(); i++) {
            zombieMove(world.getCommands().charAt(i));
            zombieDetectHuman();
        }
    }

    private void printStatus(String s) {
        System.out.println();
        logger.info("---------" + s + "----------");
        logger.info("gridSize = " + world.gridSize);
        getZombies().forEach(z -> logger.info("zombie position: " + z.getX() + " " + z.getY()));
        world.getCreatures().forEach(h -> logger.info("human position: " + h.getX() + " " + h.getY()));
        logger.info("commands = " + world.getCommands());
    }

    void zombieMove(char moveDirection) {
        switch (moveDirection) {
            case 'R' -> zombieMoveRight();
            case 'L' -> zombieMoveLeft();
            case 'U' -> zombieMoveUp();
            case 'D' -> zombieMoveDown();
        }
    }

    private void zombieMoveDown() {
        zombieMoveCode(0, 1);
    }

    private void zombieMoveUp() {
        zombieMoveCode(0, -1);
    }

    private void zombieMoveLeft() {
        zombieMoveCode(-1, 0);
    }

    private void zombieMoveRight() {
        zombieMoveCode(1, 0);
    }

    private void zombieMoveCode(int moveX, int moveY) {
        getZombies().get(0).setX(Math.floorMod(getZombies().get(0).getX() + moveX, world.getGridSize()));
        getZombies().get(0).setY(Math.floorMod(getZombies().get(0).getY() + moveY, world.getGridSize()));
        zombieLog(getZombies().get(0).getX(), getZombies().get(0).getY(), "move");
    }

    private void zombieLog(int x, int y, String type) {
        String phrase = switch (type) {
            case "move" -> "moved to";
            case "infect" -> "infected creature at";
            default -> "halted";
        };
        logger.info("zombie 0 {} ({},{})", phrase, x, y);
    }

    void zombieDetectHuman() {
        int x = getZombies().get(0).getX();
        int y = getZombies().get(0).getY();
        for (Human human : world.getCreatures()) {
            if (human.getX() == x && human.getY() == y) {
                zombieInfect(x, y, human);
                break;
            }
        }
    }

    private void zombieInfect(int x, int y, Human human) {
        getZombies().add(new Zombie(x, y));
        world.getCreatures().remove(human);
        zombieLog(x, y, "infect");
    }
}
