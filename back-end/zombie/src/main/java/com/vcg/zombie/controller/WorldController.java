package com.vcg.zombie.controller;

import com.vcg.zombie.entity.World;
import com.vcg.zombie.entity.WorldOutput;
import com.vcg.zombie.service.WorldService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class WorldController {

    private final WorldService worldService;

    @CrossOrigin(origins = {"http://localhost:3000", "null"})
    @GetMapping("/test")
    public String hello() {
        return "test value is 123 ";
    }

//    @CrossOrigin(origins = {"http://localhost:3000", "null"})
//    @PostMapping("/createPosition")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Position create(@Valid @RequestBody Position position) {
//        return new Position(position.getGridSize(),position.getClass(), position.getCreatures(), position.getCommands());
//    }


    @CrossOrigin(origins = {"http://localhost:3000", "null"})
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/zombie", method = RequestMethod.POST)
    public WorldOutput create(@RequestBody World world) {
        worldService.setWorld(world);
        worldService.getZombies().clear();
        worldService.execute();
        return new WorldOutput(worldService.getZombies(),worldService.getWorld().getCreatures());
    }

}
