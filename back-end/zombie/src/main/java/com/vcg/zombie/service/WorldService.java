package com.vcg.zombie.service;

import com.vcg.zombie.entity.Creature;
import com.vcg.zombie.entity.Position;
import com.vcg.zombie.entity.World;
import com.vcg.zombie.entity.WorldOutput;
import com.vcg.zombie.entity.Zombie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WorldService {

    private static final Logger logger = LogManager.getLogger(WorldService.class);

    public WorldOutput execute(World world, List<Zombie> zombies) {
        zombies.add(world.getZombie());
        printStatus(world, zombies, "Start:");
        start(world, zombies);
        printStatus(world, zombies, "End:");
        return new WorldOutput(zombies, world.getCreatures());
    }

    private void start(World world, List<Zombie> zombies) {
        for (int i = 0; i < world.getCommands().length(); i++) {
            zombieMove(world, world.getCommands().charAt(i));
            zombieDetectCreature(world, zombies);
        }
    }

    private void printStatus(World world, List<Zombie> zombies, String s) {
        System.out.println();
        logger.info("---------" + s + "----------");
        logger.info("gridSize = " + world.gridSize);
        zombies.forEach(z -> logger.info("zombie position: " + z.getPosition().getX() + " " + z.getPosition().getY()));
        world.getCreatures().forEach(h -> logger.info("human position: " + h.getPosition().getX() + " " + h.getPosition().getY()));
        logger.info("commands = " + world.getCommands());
    }

    private void zombieMove(World world, char moveDirection) {
        switch (moveDirection) {
            case 'R' -> zombieMoveByPosition(world, new Position(1, 0));
            case 'L' -> zombieMoveByPosition(world, new Position(-1, 0));
            case 'U' -> zombieMoveByPosition(world, new Position(0, -1));
            case 'D' -> zombieMoveByPosition(world, new Position(0, 1));
            default -> logger.info("zombie 0 halted");
        };
    }

    private void zombieMoveByPosition(World world, Position position) {
        world.getZombie().getPosition().setX(Math.floorMod(world.getZombie().getPosition().getX() + position.getX(), world.getGridSize()));
        world.getZombie().getPosition().setY(Math.floorMod(world.getZombie().getPosition().getY() + position.getY(), world.getGridSize()));
        zombieLog(world.getZombie().getPosition().getX(), world.getZombie().getPosition().getY(), "move");
    }

    private void zombieLog(int x, int y, String type) {
        String phrase = switch (type) {
            case "move" -> "moved to";
            case "infect" -> "infected creature at";
            default -> "halted";
        };
        logger.info("zombie 0 {} ({},{})", phrase, x, y);
    }

    void zombieDetectCreature(World world, List<Zombie> zombies) {
        int x = world.getZombie().getPosition().getX();
        int y = world.getZombie().getPosition().getY();
        for (Creature creature : world.getCreatures()) {
            if (creature.getPosition().getX() == x && creature.getPosition().getY() == y) {
                zombieInfect(world, zombies, x, y, creature);
                break;
            }
        }
    }

    private void zombieInfect(World world, List<Zombie> zombies, int x, int y, Creature creature) {
        zombies.add(new Zombie(new Position(x, y)));
        world.getCreatures().remove(creature);
        zombieLog(x, y, "infect");
    }
}