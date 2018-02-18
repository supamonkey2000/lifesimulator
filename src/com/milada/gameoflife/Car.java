package com.milada.gameoflife;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Car {
    public static ArrayList<Car> CAR_LIST = new ArrayList<>();

    private String name;
    private int basePrice, engine, doors, model, reliability;
    private int totalPrice, insurance;

    public Car(String name, int basePrice, int engine, int doors, int model, int reliability) {
        this.name = name;
        this.basePrice = basePrice;
        this.engine = engine;
        this.doors = doors;
        this.model = model;
        this.reliability = reliability;
        this.totalPrice = calculateTotalPrice();
        this.insurance = totalPrice / Core.INSURANCE_FACTOR;
    }


    static boolean readDefaultCars() {
        Core.log("Reading Car configuration.", Core.LOG_TYPE_INFO, Core.getClassName());
        String readData = FileUtils.readFileAsString("data/cars.conf");
        if (readData.equals(Core.NULL_STRING)) {
            Core.log("Failed to read car configuration!", Core.LOG_TYPE_ERROR, Core.getClassName());
            return false;
        }
        String[] cars = readData.split(Pattern.quote("car {"));
        for (String car : cars) {
            if (!parseCarConfig(car)) {
                Core.log("Failed to read car configuration!", Core.LOG_TYPE_ERROR, Core.getClassName());
                return false;
            }
        }
        Core.log("Finished reading default Car configuration", Core.LOG_TYPE_INFO, Core.getClassName());
        return true;
    }

    private static boolean parseCarConfig(String car) {
        String name = null;
        int basePrice = -1, engine = -1, doors = -1, model = -1, reliability = -1;

        String[] lines = car.split("\n");
        for (String line : lines) {
            if (line.startsWith("name")) name = line.split(Pattern.quote("="))[1];
            if (line.startsWith("basePrice")) basePrice = Integer.parseInt(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("engine")) engine = Integer.parseInt(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("doors")) doors = Integer.parseInt(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("model")) model = Integer.parseInt(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("reliability")) reliability = Integer.parseInt(line.split(Pattern.quote("="))[1]);
        }
        if (basePrice == -1 || engine == -1 || doors == -1 || model == -1 || reliability == -1) return true;
        CAR_LIST.add(new Car(name, basePrice, engine, doors, model, reliability));
        return true;
    }

    private int calculateTotalPrice() {
        double engineFactor, doorFactor, modelFactor, reliabilityFactor;
        switch (engine) {
            case 0:
                engineFactor = 1.00;
                break;
            case 1:
                engineFactor = 1.50;
                break;
            case 2:
                engineFactor = 1.75;
                break;
            case 3:
                engineFactor = 2.00;
                break;
            default:
                engineFactor = 1.00;
        }
        switch (doors) {
            case 0:
                doorFactor = 1.2;
                break;
            case 1:
                doorFactor = 1.3;
                break;
            case 2:
                doorFactor = 1.4;
                break;
            default:
                doorFactor = 1.0;
        }
        switch (model) {
            case 0:
                modelFactor = 1.0;
                break;
            case 1:
                modelFactor = 1.5;
                break;
            case 2:
                modelFactor = 2.0;
                break;
            case 3:
                modelFactor = 2.5;
                break;
            default:
                modelFactor = 1.0;
        }
        reliabilityFactor = 1.00 + (((double)reliability) / 100);

        return (int)(((double)basePrice) * engineFactor * doorFactor * modelFactor * reliabilityFactor);
    }
}
