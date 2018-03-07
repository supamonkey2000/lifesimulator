package com.milada.gameoflife;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Education {
    public static ArrayList<Education> EDUCATION_LIST = new ArrayList<>();

    private int type, quality, costPerYear, preReqs, postReqs;
    private String certificates;

    public Education(int type, int quality, int costPerYear, int preReqs, int postReqs, String certificates) {
        this.type = type;
        this.quality = quality;
        this.costPerYear = costPerYear;
        this.preReqs = preReqs;
        this.postReqs = postReqs;
        this.certificates = certificates;
    }

    static boolean readDefaultEducation() {
        Core.log("Reading Education configuration.", Core.LOG_TYPE_INFO, Core.getClassName());
        String readData = FileUtils.readFileAsString("data/education.conf");
        if (readData.equals(Core.NULL_STRING)) {
            Core.log("Failed to read education configuration!", Core.LOG_TYPE_ERROR, Core.getClassName());
            return false;
        }
        String[] educations = readData.split(Pattern.quote("education {"));
        for (String education : educations) {
            if (!parseEducationConfig(education)) {
                Core.log("Failed to read education configuration!", Core.LOG_TYPE_ERROR, Core.getClassName());
                return false;
            }
        }
        return true;
    }

    private static boolean parseEducationConfig(String education) {
        String certificates = null;
        int type = -1, quality = -1, costPerYear = -1, preReqs = -1, postReqs = -1;
        String[] lines = education.split("\n");
        for (String line : lines) {
            if (line.startsWith("type")) type = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("quality")) quality = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("costPerYear")) costPerYear = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("preReqs")) preReqs = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("postReqs")) postReqs = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("certificates")) certificates = (String) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
        }
        if (type == -1 || quality == -1 || costPerYear == -1 || preReqs == -1 || postReqs == -1) return true;
        EDUCATION_LIST.add(new Education(type, quality, costPerYear, preReqs, postReqs, certificates));
        return true;
    }
}

/*
type=0
quality=*int(1-100)
costPerYear=*int(100-250)
preReqs=0
postReqs=50
certificates=None
 */
