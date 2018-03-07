package com.milada.gameoflife;

import java.util.regex.Pattern;

public class Language {
    private String languageCode;

    // GENERAL


    // MENUS
    String AgeMenu, EducationMenu, JobMenu, FamilyMenu, PropertyMenu, PersonalMenu, ActionsMenu, StatsMenu;

    // BUTTONS
    String Edu_StartStudyButton, Edu_StopStudyButton, Edu_DropoutButton, Edu_UpgradeButton, Edu_ApplyButton;

    // LABELS
    String DummyPanelLabel;
    String Edu_CurrentlyEnrolled, Edu_CurrentGrade, Edu_RequiredGrade, Edu_CertificatesEarned;
    String Job_JobSearchButton, Job_RaiseButton, Job_PromoButton, Job_QuitButton, Job_RetireButton, Job_DetailsButton, Job_CurrentJob, Job_Wage, Job_HoursPerWeek, Job_MonthlyPay, Job_YearlyPay;
    //String Fam_
    String Pro_NetWorth, Pro_Insurance, Pro_CarsButton, Pro_HousesButton, Pro_ItemsButton;
    //Pers
    //Acti
    String Sta_BankAccount;

    Language(String languageCode) {
        this.languageCode = languageCode;
        String[] langLines = FileUtils.readFileAsString("data/languagepacks/" + languageCode + ".conf").split("\n");
        for (String line : langLines) {
            //if (line.startsWith("#")) /* Do nothing */;
            if (line.startsWith("AgeMenu")) AgeMenu = line.split(Pattern.quote("="))[1];
            if (line.startsWith("EducationMenu")) EducationMenu = line.split(Pattern.quote("="))[1];
            if (line.startsWith("JobMenu")) JobMenu = line.split(Pattern.quote("="))[1];
            if (line.startsWith("FamilyMenu")) FamilyMenu = line.split(Pattern.quote("="))[1];
            if (line.startsWith("PropertyMenu")) PropertyMenu = line.split(Pattern.quote("="))[1];
            if (line.startsWith("PersonalMenu")) PersonalMenu = line.split(Pattern.quote("="))[1];
            if (line.startsWith("ActionsMenu")) ActionsMenu = line.split(Pattern.quote("="))[1];
            if (line.startsWith("StatsMenu")) StatsMenu = line.split(Pattern.quote("="))[1];
            if (line.startsWith("DummyPanelLabel")) DummyPanelLabel = line.split(Pattern.quote("="))[1];

            // EDUCATION
            if (line.startsWith("Edu_StartStudyButton")) Edu_StartStudyButton = line.split(Pattern.quote("="))[1];
            if (line.startsWith("Edu_StopStudyButton")) Edu_StopStudyButton = line.split(Pattern.quote("="))[1];
            if (line.startsWith("Edu_DropoutButton")) Edu_DropoutButton = line.split(Pattern.quote("="))[1];
            if (line.startsWith("Edu_UpgradeButton")) Edu_UpgradeButton = line.split(Pattern.quote("="))[1];
            if (line.startsWith("Edu_ApplyButton")) Edu_ApplyButton = line.split(Pattern.quote("="))[1];
            if (line.startsWith("Edu_CurrentlyEnrolled")) Edu_CurrentlyEnrolled = line.split(Pattern.quote("="))[1];
            if (line.startsWith("Edu_CurrentGrade")) Edu_CurrentGrade = line.split(Pattern.quote("="))[1];
            if (line.startsWith("Edu_RequiredGrade")) Edu_RequiredGrade = line.split(Pattern.quote("="))[1];
            if (line.startsWith("Edu_CertificatesEarned")) Edu_CertificatesEarned = line.split(Pattern.quote("="))[1];

            // JOB
            if (line.startsWith("Job_RaiseButton")) Job_RaiseButton = line.split(Pattern.quote("="))[1];
            if (line.startsWith("Job_PromoButton")) Job_PromoButton = line.split(Pattern.quote("="))[1];
            if (line.startsWith("Job_QuitButton")) Job_QuitButton = line.split(Pattern.quote("="))[1];
            if (line.startsWith("Job_RetireButton")) Job_RetireButton = line.split(Pattern.quote("="))[1];
            if (line.startsWith("Job_DetailsButton")) Job_DetailsButton = line.split(Pattern.quote("="))[1];
            if (line.startsWith("Job_CurrentJob")) Job_CurrentJob = line.split(Pattern.quote("="))[1];
            if (line.startsWith("Job_Wage")) Job_Wage = line.split(Pattern.quote("="))[1];
            if (line.startsWith("Job_HoursPerWeek")) Job_HoursPerWeek = line.split(Pattern.quote("="))[1];
            if (line.startsWith("Job_MonthlyPay")) Job_MonthlyPay = line.split(Pattern.quote("="))[1];
            if (line.startsWith("Job_YearlyPay")) Job_YearlyPay = line.split(Pattern.quote("="))[1];
            if (line.startsWith("Job_JobSearchButton")) Job_JobSearchButton = line.split(Pattern.quote("="))[1];

            // FAMILY

            // PROPERTY
            if (line.startsWith("Pro_NetWorth")) Pro_NetWorth = line.split(Pattern.quote("="))[1];
            if (line.startsWith("Pro_Insurance")) Pro_Insurance = line.split(Pattern.quote("="))[1];
            if (line.startsWith("Pro_CarsButton")) Pro_CarsButton = line.split(Pattern.quote("="))[1];
            if (line.startsWith("Pro_HousesButton")) Pro_HousesButton = line.split(Pattern.quote("="))[1];
            if (line.startsWith("Pro_ItemsButton")) Pro_ItemsButton = line.split(Pattern.quote("="))[1];

            // PERSONAL

            // ACTIONS

            // STATS
            if (line.startsWith("Sta_BankAccount")) Sta_BankAccount = line.split(Pattern.quote("="))[1];
        }
    }
}
