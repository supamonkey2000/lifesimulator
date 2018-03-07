package com.milada.gameoflife;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class House {
    public static ArrayList<House> HOUSE_LIST = new ArrayList<>();

    int type, floors, bedrooms, bathrooms, maintenance, neighbourhood, basePrice, price, insurance;

    public House(int type, int floors, int bedrooms, int bathrooms, int maintenance, int neighbourhood, int basePrice) {
        this.type = type;
        this.floors = floors;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.maintenance = maintenance;
        this.neighbourhood = neighbourhood;
        this.basePrice = basePrice;
        this.price = calculateTotalPrice();
        this.insurance = this.price / Core.INSURANCE_FACTOR;
    }


    static boolean readDefaultHouses() {
        Core.log("Reading House configuration.", Core.LOG_TYPE_INFO, Core.getClassName());
        String readData = FileUtils.readFileAsString("data/houses.conf");
        if (readData.equals(Core.NULL_STRING)) {
            Core.log("Failed to read house configuration!", Core.LOG_TYPE_ERROR, Core.getClassName());
            return false;
        }
        String[] houses = readData.split(Pattern.quote("house {"));
        for (String house : houses) {
            if (!parseHouseConfig(house)) {
                Core.log("Failed to read house configuration!", Core.LOG_TYPE_ERROR, Core.getClassName());
                return false;
            }
        }
        //Core.log("Finished reading default House configuration", Core.LOG_TYPE_INFO, Core.getClassName());
        return true;
    }

    private static boolean parseHouseConfig(String car) {
        int type = -1, floors = -1, bedrooms = -1, bathrooms = -1, maintenance = -1, neighbourhood = -1, basePrice = -1;

        String[] lines = car.split("\n");
        for (String line : lines) {
            if (line.startsWith("type")) type = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("floors")) floors = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("bedrooms")) bedrooms = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("bathrooms")) bathrooms = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("maintenance")) maintenance = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("neighbourhood")) neighbourhood = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("basePrice")) basePrice = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
        }
        if (basePrice == -1 || type == -1 || floors == -1 || bedrooms == -1 || bathrooms == -1 || maintenance == -1 || neighbourhood == -1) return true;
        HOUSE_LIST.add(new House(type, floors, bedrooms, bathrooms, maintenance, neighbourhood, basePrice));
        return true;
    }

    private int calculateTotalPrice() {
        double typeFactor, floorsFactor, bedroomsFactor, bathroomsFactor, maintenanceFactor, neighbourhoodFactor;
        typeFactor =  1.0 + (double) type;
        floorsFactor = 1.0 + (((double) floors) / 10.0);
        bedroomsFactor = 1.0 + (((double) bedrooms) / 10.0);
        bathroomsFactor = 1.0 + (((double) bathrooms) / 10.0);
        maintenanceFactor = 1.0 + ((100.0 - ((double) maintenance)) / 100.0);
        neighbourhoodFactor = 1.0 + (((double) neighbourhood) / 100.0);
        return (int)(basePrice * (typeFactor * floorsFactor * bedroomsFactor * bathroomsFactor * maintenanceFactor * neighbourhoodFactor));
    }
}
