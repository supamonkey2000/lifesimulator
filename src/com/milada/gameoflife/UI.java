package com.milada.gameoflife;

import java.awt.FlowLayout;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class UI extends JFrame{

    private static String[] languageCodes; // The list of Language Codes
    private static String USER_LANGUAGE; // The code the user picked. It's named in caps because it's important.

    private static Language language; // The language pack the UI will use

    //UI Variables
    private JButton ageB, educationB, jobB, propertyB, actionsB;

    /**
     * Default and only constructor
     */
    UI() {
        super(Core.GAME_TITLE + " " + Version.getVersion());
        setLayout(new FlowLayout());
        createButtons();
        addComponentsToUI();
    }

    /**
     * Build the UI and make it visible to the user
     */
    void build() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(true); // Flash the Window when something updates
        setLocationRelativeTo(null); // Put the Window in the middle of the screen
        setResizable(false); // Make sure the user can't resize the Window
        setSize(640,480);
        setVisible(true); // Display the Window (and hope nothing fails)
    }


    /**
     * Create the JButtons for the UI. Having this method reduces the complexity of the program
     */
    private void createButtons() {
        ageB = new JButton(language.AgeButton);
        educationB = new JButton(language.EducationButton);
        jobB = new JButton(language.JobButton);
        propertyB = new JButton(language.PropertyButton);
        actionsB = new JButton(language.ActionsButton);
    }

    /**
     * Add the components to the UI. THIS WILL BE CHANGED! Panels will be used instead
     */
    private void addComponentsToUI() {
        add(ageB);
        add(educationB);
        add(jobB);
        add(propertyB);
        add(actionsB);
    }


    /**
     * Set the language of the UI
     * @return Whether or not the Language was set
     */
    static boolean setLanguage() {
        Core.log("Setting the UI Language", Core.LOG_TYPE_INFO, Core.getClassName());
        String languageCodeData = FileUtils.readFileAsString("data/languages.conf");
        if (languageCodeData.equals(Core.NULL_STRING)) {
            Core.log("Invalid Language file, exiting!", Core.LOG_TYPE_ERROR, Core.getClassName());
            Core.exit(1);
        }
        languageCodes = languageCodeData.split(Pattern.quote("\n"));
        for (String code : languageCodes) if (code.startsWith("USERLANGUAGE")) USER_LANGUAGE = code.split(Pattern.quote("="))[1];
        language = new Language(USER_LANGUAGE);
        Core.log("Language has been set!", Core.LOG_TYPE_INFO, Core.getClassName());
        return true;
    }
}
