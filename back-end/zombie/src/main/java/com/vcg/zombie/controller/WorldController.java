package com.vcg.zombie.controller;

import com.vcg.zombie.entity.World;
import com.vcg.zombie.entity.WorldOutput;
import com.vcg.zombie.entity.Zombie;
import com.vcg.zombie.service.WorldService;

import com.vcg.zombie.util.CreateZombiesFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class WorldController {

    private final WorldService worldService;

    @CrossOrigin(origins = {"http://localhost:3000", "null"})
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/zombie", method = RequestMethod.POST)
    public WorldOutput execute(@RequestBody World world) {
        List<Zombie> zombies = CreateZombiesFactory.CreateZombies();
        return worldService.execute(world, zombies);
    }

    @GetMapping("/hello")
    public String hello(String a) {
        return "hello " + a;
    }
}
