package com.vcg.zombie.entity;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Creature {
    private Position position;
}
