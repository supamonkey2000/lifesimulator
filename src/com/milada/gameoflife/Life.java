package com.milada.gameoflife;

import java.util.ArrayList;

public class Life {
    String name, workVisa, certificates;
    int gender, age, appearance, fitness, intelligence, social, health;
    boolean driversLicense;
    ArrayList<Car> cars = new ArrayList<>();
    ArrayList<House> houses = new ArrayList<>();
    Job job;
    Country country, nationality;

    boolean gameStarted = false;
    int month = 0;

    boolean completedPrimary = false, completedSecondary = false, enrolled = false, studying = false, displayJobMessage = true, startedSchool = false;
    int currentGrade = 0, BANK_ACCOUNT = 0;
    String enrolledIn = "Nothing";

    void age() {
        if (!gameStarted) {
            generateNewLife();
            gameStarted = true;
            Core.ui.showStatsPanel();
            return;
        }
        month++;
        if (month == 12) {
            age++;
            month = 0;
        }

        if (age == 6 && !startedSchool) startSchool();
        if (age == country.minWorkingAge && displayJobMessage) getAJob();


        // Craziness happens here!
        if (job != null) job.doJob();
        doInsurance();



        Core.ui.updateAllLabels();
    }

    private void startSchool() {
        startedSchool = true;
    }

    private void getAJob() {
        Core.ui.reportToUIWithNotification("Earn your keep", "You are old enough for a job!");
        Core.ui.job_jobSearchButton.setEnabled(true);
    }

    private void doInsurance() {
        int totalInsurance = 0;
        for (Car car : cars) {
            totalInsurance += car.insurance;
        }
        for (House house : houses) {
            totalInsurance += house.insurance;
        }
        Core.life.BANK_ACCOUNT -= totalInsurance;
    }


    void giveInjury(String message, int magnitude) {
        Core.ui.reportToUIWithNotification("Ouch!", message);
        health += magnitude;
    }


    private void generateNewLife() {
        Core.log("Generating new Life!", Core.LOG_TYPE_INFO, Core.getClassName());
        nationality = Country.COUNTRY_LIST.get(Core.getRandomInteger(1, Country.COUNTRY_LIST.size()));
        country = nationality;
        job = null;
        //job = Job.JOB_LIST.get(1);
        driversLicense = false;
        gender = Core.getRandomInteger(0, 2);
        age = 0;
        appearance = Core.getRandomInteger(1, 100);
        fitness = Core.getRandomInteger(1, 100);
        intelligence = Core.getRandomInteger(1, 100);
        social = Core.getRandomInteger(1, 50);
        workVisa = Core.NULL_STRING;
        certificates = "None";
        name = Name.getNewName(nationality.name, gender);
        health = 90;

        System.out.println(name + " is a " + gender + " from " + nationality.name + ".");
        System.out.println(appearance + ", " + Integer.toString(fitness) + ", " + Integer.toString(intelligence) + ", " + Integer.toString(social));
    }
}
