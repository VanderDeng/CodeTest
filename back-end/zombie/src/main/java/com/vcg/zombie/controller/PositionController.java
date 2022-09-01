package com.vcg.zombie.controller;

import com.vcg.zombie.model.entity.Position;
import com.vcg.zombie.model.entity.Zombie;
import com.vcg.zombie.model.entity.finalPosition;
import com.vcg.zombie.service.PositionService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
public class PositionController {

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
    @RequestMapping(value = "/createPosition", method = RequestMethod.POST)
    public finalPosition create(@RequestBody Position position) {
        finalPosition newPosition = PositionService.create(position);
        return newPosition;
    }

}
