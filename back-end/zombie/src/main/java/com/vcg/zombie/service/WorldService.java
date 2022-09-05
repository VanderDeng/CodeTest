package com.vcg.zombie.service;

import com.vcg.zombie.entity.Creature;
import com.vcg.zombie.entity.Position;
import com.vcg.zombie.entity.World;
import com.vcg.zombie.entity.WorldOutput;
import com.vcg.zombie.entity.Zombie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class WorldService {

    private static final Logger logger = LogManager.getLogger(WorldService.class);

    private final static Map<Character, Position> directionOffsetMap = Map.of(
        'R', new Position(1, 0),
        'L', new Position(-1, 0),
        'U', new Position(0, -1),
        'D', new Position(0, 1));

    public WorldOutput execute(World world) {
        List<Zombie> zombies = new ArrayList<>();
        zombies.add(world.getZombie());

        printStatus(world, zombies, "Start:");
        start(world, zombies);
        printStatus(world, zombies, "End:");

        return new WorldOutput(zombies, world.getCreatures());
    }

    private void start(World world, List<Zombie> zombies) {

        Zombie zombie = world.getZombie();
        for (int i = 0; i < world.getCommands().length(); i++) {
            char moveDirection = world.getCommands().charAt(i);
            zombie.move(directionOffsetMap.get(moveDirection), world.getGridSize());
            Optional<Creature> infected = zombieDetectCreature(zombie, world.getCreatures());
            infected.ifPresent(c -> {
                zombies.add(new Zombie(c.getPosition()));
                world.getCreatures().remove(c);
                zombieLog(c.getPosition(), "infect");
            });
        }
    }

    private void printStatus(World world, List<Zombie> zombies, String s) {
        System.out.println();
        logger.info("---------" + s + "----------");
        logger.info("gridSize = " + world.getGridSize());
        zombies.forEach(z -> logger.info("zombie position: {}",z.getPosition()));
        world.getCreatures().forEach(h -> logger.info("human position: {}", h.getPosition()));
        logger.info("commands = " + world.getCommands());
    }

    private void zombieLog(Position position, String type) {
        String phrase = switch (type) {
            case "move" -> "moved to";
            case "infect" -> "infected creature at";
            default -> "halted";
        };
        logger.info("zombie 0 {} {}", phrase, position);
    }

    private Optional<Creature> zombieDetectCreature(Zombie zombie, List<Creature> creatures) {
        return creatures.stream().filter(c -> c.getPosition().equals(zombie.getPosition())).findFirst();
    }
}