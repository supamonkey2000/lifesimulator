package com.milada.gameoflife;

public class Version {
    private static int MAJOR = 0;
    private static int MINOR = 1;
    private static int PATCH = 0;

    public static String getVersion() {
        return "v" + Integer.toString(MAJOR) + "." + Integer.toString(MINOR) + "." + Integer.toString(PATCH);
    }
}
