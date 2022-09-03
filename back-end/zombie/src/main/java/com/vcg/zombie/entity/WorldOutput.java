package com.vcg.zombie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorldOutput implements Serializable {

    @NotBlank
    private List<Zombie> zombies;

    @NotBlank
    private List<Creature> creatures;

}


