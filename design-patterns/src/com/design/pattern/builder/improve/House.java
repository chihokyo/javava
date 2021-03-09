package com.design.pattern.builder.improve;

/**
 * 进化后 
 * 房子类（具体产品）
 */
public class House {

    private String basis;
    private String wall;
    private String roofed;

    public String getBasis() {
        return this.basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public String getWall() {
        return this.wall;
    }

    public void setWall(String wall) {
        this.wall = wall;
    }

    public String getRoofed() {
        return this.roofed;
    }

    public void setRoofed(String roofed) {
        this.roofed = roofed;
    }

    @Override
    public String toString() {
        return "{" + " basis='" + getBasis() + "'" + ", wall='" + getWall() + "'" + ", roofed='" + getRoofed() + "'"
                + "}";
    }

}
