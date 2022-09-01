package com.vcg.zombie.model.entity;

public class Zombie extends Creatures {

    private String x;
    private String y;

    public Zombie(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.y = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public void infectCreatures(){

    }


}
