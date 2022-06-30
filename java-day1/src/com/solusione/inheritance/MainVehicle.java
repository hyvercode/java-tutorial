package com.solusione.inheritance;

public class MainVehicle {
    public static void main(String[] args){
        Car bus = new Car();
        bus.setName("BUS");
        bus.setColor("White");
        bus.setPlate("BK000PL");
        printCar(bus);

    }

    private static void printCar(Car car){
        System.out.println("Name "+car.getName());
        System.out.println("White "+car.getColor());
        System.out.println("Name "+car.getPlate());

        car.vehicleBreake();
        car.vehicleTurn("Left");
    }
}
