package com.vcg.zombie.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @NotBlank
    private int gridSize;
    @NotBlank
    private String zombie;
    @NotBlank
    private String creatures;
    @NotBlank
    private String commands;
}

