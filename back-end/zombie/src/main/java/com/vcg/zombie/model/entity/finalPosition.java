package com.vcg.zombie.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class finalPosition implements Serializable {


    @NotBlank
    private String zombies;

    @NotBlank
    private String creatures;
}

