package com.vcg.zombie.model.entity;

public class Zombie extends Creatures {


    public Zombie() {
       new Zombie("0","0");
    }


    public Zombie(String positionX, String positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public String getPositionX() {
        return positionX;
    }

    public void setPositionX(String positionX) {
        this.positionX = positionX;
    }

    public String getPositionY() {
        return positionY;
    }

    public void setPositionY(String positionY) {
        this.positionY = positionY;
    }

    public void infectCreatures(){

    }


}
