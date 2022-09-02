package com.vcg.zombie.controller;

import com.vcg.zombie.entity.*;
import com.vcg.zombie.service.WorldService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class WorldController {

    private final WorldService worldService;

    @CrossOrigin(origins = {"http://localhost:3000", "null"})
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/zombie", method = RequestMethod.POST)
    public WorldOutput create(@RequestBody World world) {

        if(checkDigit(world.getGridSize())
                && checkCommand(world.getCommands())
                && checkXY(world.getZombie())
                && checkCreatures(world.getCreatures())
                && checkDuplicates(world.getZombie(), world.getCreatures()))
        {
            worldService.setWorld(world);
            worldService.getZombies().clear();
            worldService.execute();
            return new WorldOutput(worldService.getZombies(),worldService.getWorld().getCreatures());
        }
        else
        {
            return null;
        }
    }

    public boolean checkCommand(String input)
    {
        if (input == null)  // if user did not enter anything
        {
            return false;
        }

        for (int x = 0; x < input.length(); x++) // To go through the typed String
        {
            if(((input.charAt(x)) != 'U') || ((input.charAt(x)) != 'D') || ((input.charAt(x)) != 'L') || ((input.charAt(x)) != 'R'))
            //Call the character method to check each character to see if it is a letter or a space
            {
                return false;
            }
        }

        return true; // return true if all character in the string is valid
    }

    public boolean checkDigit(int gridSize)
    {
        return Character.isDigit(gridSize);
    }

    public boolean checkXY(Creature creature)
    {
        return checkDigit(creature.getX()) && checkDigit(creature.getY());
    }

    public boolean checkCreatures(List<Human> creatures)
    {
        for (Human creature : creatures) {
            if (!checkXY(creature)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkDuplicates(Zombie zombies, List<Human> creatures)
    {

        ArrayList<Creature> zombie = new ArrayList<>();
        zombie.add(zombies);
        ArrayList<Creature> human = new ArrayList<>(creatures);

        ArrayList<Creature> duplicates = new ArrayList<>(human);
        duplicates.retainAll(zombie);

        return duplicates.size() == human.size();
    }
}
