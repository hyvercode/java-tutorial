package com.solusione.inheritance;

public class Vehilce extends Behavior{

    private String name;
    private String color;
    private String plate;

    public Vehilce() {
    }

    public Vehilce(String name, String color, String plate) {
        this.name = name;
        this.color = color;
        this.plate = plate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    @Override
    public void vehicleTurn(String turn) {
        System.out.println("Turn "+turn);
    }
}
