package com.milada.gameoflife;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Country {
    public static ArrayList<Country> COUNTRY_LIST = new ArrayList<>();

    String name;
    long population;
    int health, minWage, environment, foreignRelations, minWorkingAge, minMilitaryAge, militaryStrength, purchaseTax, incomeTax;
    boolean free, freeHealthcare, freeEducation, conscription, pension, workVisa;

    public Country(String name, long population, int health, int minWage, int environment, int
            foreignRelations, int minWorkingAge, int minMilitaryAge, int militaryStrength, int
            purchaseTax, int incomeTax, boolean free, boolean freeHealthcare, boolean freeEducation,
                   boolean conscription, boolean pension, boolean workVisa) {
        this.name = name;
        this.population = population;
        this.health = health;
        this.minWage = minWage;
        this.environment = environment;
        this.foreignRelations = foreignRelations;
        this.minWorkingAge = minWorkingAge;
        this.minMilitaryAge = minMilitaryAge;
        this.militaryStrength = militaryStrength;
        this.purchaseTax = purchaseTax;
        this.incomeTax = incomeTax;
        this.free = free;
        this.freeHealthcare = freeHealthcare;
        this.freeEducation = freeEducation;
        this.conscription = conscription;
        this.pension = pension;
        this.workVisa = workVisa;
    }

    static boolean readDefaultCountries() {
        Core.log("Reading Country configuration.", Core.LOG_TYPE_INFO, Core.getClassName());
        String readData = FileUtils.readFileAsString("data/countries.conf");
        if (readData.equals(Core.NULL_STRING)) {
            Core.log("Failed to read country configuration!", Core.LOG_TYPE_ERROR, Core.getClassName());
            return false;
        }
        String[] countries = readData.split(Pattern.quote("country {"));
        for (String country : countries) {
            if (!parseCountryConfig(country)) {
                Core.log("Failed to read country configuration!", Core.LOG_TYPE_ERROR, Core.getClassName());
                return false;
            }
        }
        return true;
    }

    private static boolean parseCountryConfig(String country) {
        String name = Core.NULL_STRING;
        long population = -1;
        int health = -1, minWage = -1, environment = -1, foreignRelations = -1, minWorkingAge = -1, minMilitaryAge = -1, militaryStrength = -1, purchaseTax = -1, incomeTax = -1;
        boolean free = false, freeHealthcare = false, freeEducation = false, conscription = false, pension = false, workVisa = false;

        String[] lines = country.split("\n");
        for (String line : lines) {
            if (line.startsWith("name")) name = line.split(Pattern.quote("="))[1];
            if (line.startsWith("population")) population = Long.parseLong(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("health")) health = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("minWage")) minWage = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("environment")) environment = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("foreignRelations")) foreignRelations = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("minWorkingAge")) minWorkingAge = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("minMilitaryAge")) minMilitaryAge = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("militaryStrength")) militaryStrength = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("purchaseTax")) purchaseTax = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("incomeTax")) incomeTax = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("free")) free = Boolean.parseBoolean((String)Core.parseConfigInput(line.split(Pattern.quote("="))[1]));
            if (line.startsWith("freeHealthcare")) freeHealthcare = Boolean.parseBoolean((String)Core.parseConfigInput(line.split(Pattern.quote("="))[1]));
            if (line.startsWith("freeEducation")) freeEducation = Boolean.parseBoolean((String)Core.parseConfigInput(line.split(Pattern.quote("="))[1]));
            if (line.startsWith("conscription")) conscription = Boolean.parseBoolean((String)Core.parseConfigInput(line.split(Pattern.quote("="))[1]));
            if (line.startsWith("pension")) pension = Boolean.parseBoolean((String)Core.parseConfigInput(line.split(Pattern.quote("="))[1]));
            if (line.startsWith("workVisa")) workVisa = Boolean.parseBoolean((String)Core.parseConfigInput(line.split(Pattern.quote("="))[1]));
        }
        //TODO Check the variables to ensure none are -1
        COUNTRY_LIST.add(new Country(name, population, health, minWage, environment, foreignRelations, minWorkingAge, minMilitaryAge, militaryStrength, purchaseTax, incomeTax, free, freeHealthcare, freeEducation, conscription, pension, workVisa));
        return true;
    }

}
