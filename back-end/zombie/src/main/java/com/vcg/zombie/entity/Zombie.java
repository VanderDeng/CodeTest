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
    Position position = new Position();

    @JsonAnySetter
    public void getXYFromJson(String key, String value) {
        if ("x".equals(key)) {
            position.setX(Integer.parseInt(value));
        } else if ("y".equals(key)) {
            position.setY(Integer.parseInt(value));
        } else {
            LogManager.getLogger(Zombie.class).info("met abnormal value: " + value);
        }
    }

}
