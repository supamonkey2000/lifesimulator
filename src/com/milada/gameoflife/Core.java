package com.milada.gameoflife;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Core {

    // Public variables
    public static final int LOG_TYPE_INFO = 0; // Used for logging Information to the console, NOT the UI
    public static final int LOG_TYPE_WARN = 1; // Warnings. Something with a Warning won't break the game, but something has gone wrong
    public static final int LOG_TYPE_ERROR = 2; // If this shows then something broke the game
    public static final int INSURANCE_FACTOR = 24; // Total price of insured it items divided by [this]. This is what you pay per month for those items

    public static final String NULL_STRING = "{NULL}"; // Since an EMPTY String isn't a NULL string, I made this for if a config file is invalid
    public static final String GAME_TITLE = "Simulife"; // The games name, as shown to the console and the UI (not the same as the package name)


    // Private variables
    private static final String LOG_TIME_FORMAT = "dd/MM/yyyy hh:mm:ss"; //Date and Time format to use when logging


    // Package variables
    static UI ui; // This is the UI. Say hello, it's friendly.

    /**
     * Main class
     * @param args Program arguments array
     */
    public static void main(String[] args) {
        Core.log("GameOfLife " + Version.getVersion() + " - Created by Joshua Moore (Milada Systems)", Core.LOG_TYPE_INFO, Core.getClassName());
        readBaseConfigurations(); // Read the configuration files.
        ui = new UI(); // Initiate the UI
        ui.build(); // Build the UI (Yeah this could be shoved into the previous line but I chose this way)
    }

    /**
     * Read the configuration files and build game objects based off of them
     */
    private static void readBaseConfigurations() {
        Core.log("Reading base configuration files.", Core.LOG_TYPE_INFO, Core.getClassName());
        if (!UI.setLanguage()) exit(1); // Set the UI language
        if (!Car.readDefaultCars()) exit(1); // Build the car objects
        if (!Job.readDefaultJobs()) exit(1); // Build the job objects
    }

    /**
     * Avoid using generic sysout and syserr, plus it is shorter to write. It also allows for timestamps in logging. This method is simple enough.
     * @param message The String that will be printed to the screen
     * @param type The type of message to be sent. Can be LOG_TYPE_INFO = 0, LOG_TYPE_WARN = 1, or LOG_TYPE_ERROR = 2
     */
    static void log(String message, int type, String className) {
        String timeString = "[" + getSystemTime(LOG_TIME_FORMAT) + "]";
        switch (type) {
            case LOG_TYPE_INFO:
                System.out.println(timeString + " [INFO] [" + className + "] " + message);
                break;
            case LOG_TYPE_WARN:
                System.out.println(timeString + " [WARN] [" + className + "] " + message);
                break;
            case LOG_TYPE_ERROR:
                System.err.println(timeString + " [ERROR] [" + className + "] " + message);
                break;
            default: // If something weird happened, like some idiot modified the code to have their own log type without adding a new case, then print using default parameters
                Core.log(message, LOG_TYPE_INFO, getClassName());
        }
    }

    /**
     * Get a Class name for logging to the console (Thanks StackOverflow!)
     * @return The class name that wants to log to the console
     */
    static String getClassName() {
        try {
            throw new Exception();
        } catch (Exception stack) {
            return stack.getStackTrace()[1].getClassName();
        }
    }

    /**
     * Return the system time using the given format
     * @param format The format to use for SimpleDateFormat
     * @return The current system time in the given format
     */
    private static String getSystemTime(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    /**
     * Exit the game...
     * @param statusCode The code to report to the system
     */
    static void exit(int statusCode) {
        Core.log("EXITING PROGRAM", Core.LOG_TYPE_INFO, Core.getClassName());
        System.exit(statusCode);
    }
}
