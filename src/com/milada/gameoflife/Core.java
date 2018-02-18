package com.milada.gameoflife;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Core {

    // Public variables
    public static final int LOG_TYPE_INFO = 0;
    public static final int LOG_TYPE_WARN = 1;
    public static final int LOG_TYPE_ERROR = 2;
    public static final int INSURANCE_FACTOR = 24;

    public static final String NULL_STRING = "{NULL}";
    public static final String GAME_TITLE = "Game Of Life";


    // Private variables
    private static final String LOG_TIME_FORMAT = "dd/MM/yyyy hh:mm:ss"; //Date and Time format to use when logging


    // Package variables
    static UI ui;

    /**
     * Main class
     * @param args Program arguments array
     */
    public static void main(String[] args) {
        Core.log("GameOfLife " + Version.getVersion() + " - Created by Joshua Moore (Milada Systems)", Core.LOG_TYPE_INFO, Core.getClassName());
        readBaseConfigurations();
        ui = new UI();
        ui.build();
    }

    private static void readBaseConfigurations() {
        Core.log("Reading base configuration files.", Core.LOG_TYPE_INFO, Core.getClassName());
        if (!UI.setLanguage()) exit(1);
        if (!Car.readDefaultCars()) exit(1);
        if (!Job.readDefaultJobs()) exit(1);
    }

    /**
     * Avoid using generic sysout and syserr, plus it is shorter to write. It also allows for timestamps in logging
     * @param message The String that will be printed to the screen
     * @param type The type of message to be sent. Can be LOG_TYPE_INFO = 0, LOG_TYPE_WARN = 1, or LOG_TYPE_ERROR = 2
     */
    public static void log(String message, int type, String className) {
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
            default:
                Core.log(message, LOG_TYPE_INFO, getClassName());
        }
    }

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

    static void exit(int statusCode) {
        Core.log("EXITING PROGRAM", Core.LOG_TYPE_INFO, Core.getClassName());
        System.exit(statusCode);
    }
}
