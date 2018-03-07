package com.milada.gameoflife;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Job {
    public static ArrayList<Job> JOB_LIST = new ArrayList<>();

    private String company, position, requirements;
    private int rank, wage, hoursPerWeek, workXp, safety, growth, happiness, vacaTime;
    private boolean vacaPay;

    public Job(String company, String position, int rank, int wage, int hoursPerWeek, int workXp, int safety, int growth, int happiness, int vacaTime, boolean vacaPay, String requirements) {
        this.company = company;
        this.position = position;
        this.rank = rank;
        this.wage = wage;
        this.hoursPerWeek = hoursPerWeek;
        this.workXp = workXp;
        this.safety = safety;
        this.growth = growth;
        this.happiness = happiness;
        this.vacaTime = vacaTime;
        this.vacaPay = vacaPay;
        this.requirements = requirements;
    }

    void doJob() {
        if (wage == 0) wage = Core.life.country.minWage;
        Core.life.BANK_ACCOUNT += (wage * hoursPerWeek) * 4;
        if (Core.getRandomInteger(0, safety) == 0) Core.life.giveInjury("You were injured in a work accident!", Core.getRandomInteger(0,100));
        if (Core.getRandomInteger(0, 100 - growth) == 0) Core.life.job.promote();
    }

    void promote() {
        Core.ui.reportToUIWithNotification("Good work!", "You were promoted!");
    }


    static boolean readDefaultJobs() {
        Core.log("Reading Job configuration.", Core.LOG_TYPE_INFO, Core.getClassName());
        String readData = FileUtils.readFileAsString("data/jobs.conf");
        if (readData.equals(Core.NULL_STRING)) {
            Core.log("Failed to read job configuration!", Core.LOG_TYPE_ERROR, Core.getClassName());
            return false;
        }
        String[] jobs = readData.split(Pattern.quote("job {"));
        for (String job : jobs) {
            if (!parseJobConfig(job)) {
                Core.log("Failed to read job configuration!", Core.LOG_TYPE_ERROR, Core.getClassName());
                return false;
            }
        }
        //Core.log("Finished reading default Job configuration", Core.LOG_TYPE_INFO, Core.getClassName());
        return true;
    }

    private static boolean parseJobConfig(String job) {
        String company = null, position = null, requirements = null;
        int rank = -1, wage = -1, hoursPerWeek = -1, workXp = -1, safety = -1, growth = -1, happiness = 0, vacaTime = -1;
        boolean vacaPay = false;

        String[] lines = job.split("\n");
        for (String line : lines) {
            if (line.startsWith("company")) company = (String) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("position")) position = (String) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("rank")) rank = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("wage")) wage = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("hoursPerWeek")) hoursPerWeek = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("workXp")) workXp = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("safety")) safety = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("growth")) growth = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("happiness")) happiness = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("vacaTime")) vacaTime = (Integer) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
            if (line.startsWith("vacaPay")) vacaPay = Boolean.parseBoolean((String)Core.parseConfigInput(line.split(Pattern.quote("="))[1]));
            if (line.startsWith("requirements")) requirements = (String) Core.parseConfigInput(line.split(Pattern.quote("="))[1]);
        }
        if (rank == -1 || wage == -1 || hoursPerWeek == -1 || workXp == -1 || safety == -1 || growth == -1 || happiness == -1) return true;
        JOB_LIST.add(new Job(company, position, rank, wage, hoursPerWeek, workXp, safety, growth, happiness, vacaTime, vacaPay, requirements));
        return true;
    }
}
