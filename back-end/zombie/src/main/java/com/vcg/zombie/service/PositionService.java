package com.vcg.zombie.service;

import com.vcg.zombie.model.entity.Position;
import com.vcg.zombie.model.entity.Zombie;
import com.vcg.zombie.model.entity.finalPosition;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.stream.Stream;

public interface PositionService {

    static Position create(Position position) {
//        finalPosition newPosition = new finalPosition(Zombie("1","2"),"12344444444");
//        return newPosition;
        System.out.println(position);
        System.out.println(position.getZombie().getX());
        System.out.println(position.getZombie().getY());
        @NotBlank Zombie[] creatures = position.getCreatures();
        Stream.of(creatures).forEach(creature-> System.out.println(creature.getX()+" "+creature.getY()));

        return position;
    }
}
