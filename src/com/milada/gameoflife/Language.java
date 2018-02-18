package com.milada.gameoflife;

import java.util.regex.Pattern;

public class Language {
    private String languageCode;

    // GENERAL


    // BUTTONS
    String AgeButton, EducationButton, JobButton, PropertyButton, ActionsButton;

    // LABELS


    Language(String languageCode) {
        this.languageCode = languageCode;
        String[] langLines = FileUtils.readFileAsString("data/languagepacks/" + languageCode + ".conf").split("\n");
        for (String line : langLines) {
            if (line.startsWith("#")) /* Do nothing */;
            if (line.startsWith("AgeButton")) AgeButton = line.split(Pattern.quote("="))[1];
            if (line.startsWith("EducationButton")) EducationButton = line.split(Pattern.quote("="))[1];
            if (line.startsWith("JobButton")) JobButton = line.split(Pattern.quote("="))[1];
            if (line.startsWith("PropertyButton")) PropertyButton = line.split(Pattern.quote("="))[1];
            if (line.startsWith("ActionsButton")) ActionsButton = line.split(Pattern.quote("="))[1];
        }
    }
}
