package com.milada.gameoflife;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Name {
    String country;
    int gender;
    public static ArrayList<Name> NAME_LIST = new ArrayList<>();

    private ArrayList<String> first = new ArrayList<>(), last = new ArrayList<>();

    Name(String country, int gender, ArrayList<String> first, ArrayList<String> last) {
        this.country = country;
        this.gender = gender;
        this.first = first;
        this.last = last;
    }

    static String getNewName(String mCountry, int mGender) {
        for (Name name : NAME_LIST) {
            if (name.country.equals(mCountry) && name.gender == mGender) {
                return name.first.get(Core.getRandomInteger(0, name.first.size())) + " " + name.last.get(Core.getRandomInteger(0, name.last.size()));
            }
        }
        return "Alex Smith";
    }

    static boolean readDefaultNames() {
        Core.log("Reading Name configuration.", Core.INSURANCE_FACTOR, Core.getClassName());
        String nameData = FileUtils.readFileAsString("data/names.conf");
        if (nameData.equals(Core.NULL_STRING)) {
            Core.log("Failed to read name configuration!", Core.LOG_TYPE_ERROR, Core.getClassName());
            return false;
        }
        String[] names = nameData.split(Pattern.quote("name {"));
        for (String name : names) {
            if (!parseNameConfig(name)) {
                Core.log("Failed to read name configuraiton!", Core.LOG_TYPE_ERROR, Core.getClassName());
                return false;
            }
        }
        return true;
    }

    private static boolean parseNameConfig(String name) {
        String mCountry = "";
        int mGender = 0;
        ArrayList<String> mFirst = new ArrayList<>(), mLast = new ArrayList<>();
        String[] lines = name.split("\n");
        for (String line : lines) {
            if (line.startsWith("country")) mCountry = (String) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("first")) mFirst.add((String) Core.parseConfigInput(line.split(Pattern.quote("="))[1]));
            if (line.startsWith("last")) mLast.add((String) Core.parseConfigInput(line.split(Pattern.quote("="))[1]));
            if (line.startsWith("gender")) mGender = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
        }
        NAME_LIST.add(new Name(mCountry, mGender, mFirst, mLast));
        return true;
    }
}
