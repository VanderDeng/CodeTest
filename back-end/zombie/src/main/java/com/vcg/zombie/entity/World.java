package com.vcg.zombie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class World implements Serializable {

    @NotBlank
    public int gridSize;

    @NotBlank
    private Zombie zombie;

    @NotBlank
    private List<Human> creatures;

    @NotBlank
    private String commands;
}

