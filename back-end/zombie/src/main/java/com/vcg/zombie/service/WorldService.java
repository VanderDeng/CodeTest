package com.vcg.zombie.service;

import com.vcg.zombie.entity.Human;
import com.vcg.zombie.entity.World;
import com.vcg.zombie.entity.Zombie;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Data
@Service
@RequiredArgsConstructor
public class WorldService {

    private World world;
    private List<Zombie> zombies=new ArrayList<>();

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
        System.out.println("---------" + s + "----------");
        System.out.println("gridSize = " + world.gridSize);
        getZombies().forEach(z -> System.out.println("zombie: " + z.getX() + " " + z.getY()));
        world.getCreatures().forEach(h -> System.out.println("human: " + h.getX() + " " + h.getY()));
        System.out.println("commands = " + world.getCommands());
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
        zombieMoveLog(getZombies().get(0).getX(), getZombies().get(0).getY());
    }

    private void zombieMoveLog(int x, int y) {
        System.out.printf("zombie 0 moved to (%s,%s)\n", x, y);
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
        zombieInfectLog(x, y);
    }

    private void zombieInfectLog(int x, int y) {
        System.out.printf("zombie 0 infected creature at (%s,%s)\n", x, y);
    }
}
