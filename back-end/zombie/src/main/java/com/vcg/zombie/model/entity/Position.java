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

//    class Zombie {
//        public String x;
//        public String y;
//    }


    @NotBlank
    private String gridSize;

    @NotBlank


    @NotBlank
    private String creatures;

    @NotBlank
    private String commands;



}

