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
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/zombie", method = RequestMethod.POST)
    public WorldOutput create(@RequestBody World world) {
        worldService.setWorld(world);
        worldService.execute();
        return new WorldOutput(worldService.getZombies(),worldService.getWorld().getCreatures());
    }

}
