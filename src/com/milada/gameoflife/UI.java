package com.milada.gameoflife;

import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class UI extends JFrame{

    private static String[] languageCodes;


    //UI Variables
    private JButton ageB;

    UI() {
        super(Core.GAME_TITLE + " " + Version.getVersion());
        ageB = new JButton("Age");
        add(ageB);
    }

    void build() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }




    static boolean setLanguage() {
        String languageCodeData = FileUtils.readFileAsString("data/languages.conf");
        if (!languageCodeData.equals(Core.NULL_STRING)) {
            Core.log("Invalid Language, exiting!", Core.LOG_TYPE_ERROR, Core.getClassName());
            Core.exit(1);
        }
        languageCodes = languageCodeData.split(Pattern.quote("\n"));
        
    }
}
