package com.vcg.zombie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

public class Zombie extends Creature {

    public Zombie(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
