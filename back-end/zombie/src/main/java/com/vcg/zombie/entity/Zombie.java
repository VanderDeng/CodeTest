package com.vcg.zombie.entity;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.vcg.zombie.service.WorldService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Zombie {
    private Position position;

    public void move(Position offset, int gridSize) {
        this.position = this.position.move(offset, gridSize);
    }
}
