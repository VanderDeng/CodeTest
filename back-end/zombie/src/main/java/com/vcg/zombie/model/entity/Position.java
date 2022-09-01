package com.vcg.zombie.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Position implements Serializable {

    @NotBlank
    private String gridSize;

    @NotBlank
    private Zombie zombie;

    @NotBlank
    private Zombie[] creatures;

    @NotBlank
    private String commands;



}

