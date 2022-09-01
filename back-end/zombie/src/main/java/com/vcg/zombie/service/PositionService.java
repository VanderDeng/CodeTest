package com.vcg.zombie.service;

import com.vcg.zombie.model.entity.Position;
import com.vcg.zombie.model.entity.finalPosition;

import javax.validation.constraints.NotBlank;

public interface PositionService {

    static finalPosition create(Position position) {
        finalPosition newPosition = new finalPosition(position.getZombie(),"12344444444");
        return newPosition;
    }
}
