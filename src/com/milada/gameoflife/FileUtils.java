package com.milada.gameoflife;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
    A message from the developer:

    I don't know why I built this class, with a ton of methods I don't use. It seemed like they would
    be needed at some point, but... nope. Anyways, if you find a use for them go right on ahead and
    use them. I won't be there to judge your decision.
 */

public class FileUtils {

    /**
     * Create a new empty file at the specified path
     * @param path The path for the new file
     * @param name The name for the new file
     * @return Whether or not the file was created
     */
    public static boolean createNewFile(String path, String name) {
        File filePath = new File(path);
        File fileName = new File(name);
        if (!filePath.exists()) { //Filepath does NOT exist, attempt to create it
            Core.log("Directory " + path + " does not exist, creating!", Core.LOG_TYPE_WARN, Core.getClassName());
            if (!createNewDirectory(path)) { //If we are unable to create the directory, report it
                Core.log("Directory " + path + " could not be created!", Core.LOG_TYPE_ERROR, Core.getClassName());
                return false;
            }
        }
        if (fileName.exists()) {
            Core.log("File " + path + name + " already exists, ignoring!", Core.LOG_TYPE_INFO, Core.getClassName());
            return true;
        }
        Core.log("Creating new file " + path + name, Core.LOG_TYPE_INFO, Core.getClassName());
        try {
            if (!fileName.createNewFile()) {
                Core.log("Failed to create file " + path + name, Core.LOG_TYPE_ERROR, Core.getClassName());
                return false;
            } else {
                Core.log("File " + name + " has been created.", Core.LOG_TYPE_INFO, Core.getClassName());
                return true;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            Core.log("Failed to create file " + path + name, Core.LOG_TYPE_ERROR, Core.getClassName());
            return false;
        }
    }

    /**
     * Write content (a String) to a file.
     * @param pathname The name of the file to write to, including path
     * @param content The content to write to the file
     * @param append Whether or not to append to the file. If false, the file will be overwritten
     * @return Whether or not the file was written to
     */
    public static boolean writeToFile(String pathname, String content, boolean append) {
        File file = new File(pathname);
        if (!file.exists()) {
            Core.log("File " + pathname + " does not exist, cannot write to it!", Core.LOG_TYPE_ERROR, Core.getClassName());
            return false;
        }
        Core.log("Writing content to file " + pathname, Core.LOG_TYPE_INFO, Core.getClassName());
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            if (append) {
                String previous = readFileAsString(pathname);
                if (previous.equals(Core.NULL_STRING)) {
                    Core.log("Received NULL String!", Core.LOG_TYPE_ERROR, Core.getClassName());
                    return false;
                } else {
                    bufferedWriter.write(previous);
                    bufferedWriter.newLine();
                    bufferedWriter.write(content);
                    bufferedWriter.flush();
                    Core.log("Wrote content to file " + pathname, Core.LOG_TYPE_INFO, Core.getClassName());
                    return true;
                }
            } else {
                bufferedWriter.write(content);
                bufferedWriter.flush();
                Core.log("Wrote content to file " + pathname, Core.LOG_TYPE_INFO, Core.getClassName());
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Core.log("Failed to write content to file " + file, Core.LOG_TYPE_ERROR, Core.getClassName());
            return false;
        }
    }

    /**
     * Remove a file
     * @param pathname The file to remove
     * @return Whether or not the file was removed
     */
    public static boolean removeFile(String pathname) {
        File file = new File(pathname);
        if (!file.exists()) {
            Core.log("File " + pathname + " does not exist, ignoring!", Core.LOG_TYPE_INFO, Core.getClassName());
            return true;
        }
        Core.log("Deleting file " + pathname, Core.LOG_TYPE_INFO, Core.getClassName());
        try {
            if (!file.delete()) {
                Core.log("Failed to delete file " + pathname, Core.LOG_TYPE_ERROR, Core.getClassName());
                return false;
            } else {
                Core.log("File " + pathname + " deleted.", Core.LOG_TYPE_INFO, Core.getClassName());
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Core.log("Failed to delete file " + pathname, Core.LOG_TYPE_ERROR, Core.getClassName());
            return false;
        }
    }

    /**
     * Read a specified file as a String
     * @param pathname The file to read
     * @return The text from the file, or a "null string" if it could not be read.
     */
    public static String readFileAsString(String pathname) {
        File file = new File(pathname);
        if (!file.exists()) {
            Core.log("File " + pathname + " does not exist, ignoring!", Core.LOG_TYPE_ERROR, Core.getClassName());
            return Core.NULL_STRING;
        }
        Core.log("Reading from file " + pathname, Core.LOG_TYPE_INFO, Core.getClassName());
        String returnString = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                returnString = returnString.concat(line + "\n");
            }
            Core.log("Read String from file " + pathname, Core.LOG_TYPE_INFO, Core.getClassName());
            return returnString;
        } catch (Exception ex) {
            ex.printStackTrace();
            Core.log("Failed to read from file " + pathname, Core.LOG_TYPE_ERROR, Core.getClassName());
            return Core.NULL_STRING;
        }
    }

    /**
     * Create a new directory at the given path. This WILL create parent directories if they do not already exist
     * @param path The directory and path the create
     * @return Whether or not the directory was created
     */
    public static boolean createNewDirectory(String path) {
        File filePath = new File(path);
        if (filePath.exists()) {
            Core.log("Directory " + path + " already exists, ignoring", Core.LOG_TYPE_INFO, Core.getClassName());
            return true;
        }
        Core.log("Creating new directory " + path, Core.LOG_TYPE_INFO, Core.getClassName());
        try {
            if (!filePath.mkdirs()) {
                Core.log("Failed to create directory " + path, Core.LOG_TYPE_ERROR, Core.getClassName());
                return false;
            } else {
                Core.log("Directory " + path + " created.", Core.LOG_TYPE_INFO, Core.getClassName());
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Core.log("Failed to create directory " + path, Core.LOG_TYPE_ERROR, Core.getClassName());
            return false;
        }
    }

    /**
     * Remove a directory
     * @param path The directory to remove
     * @return Whether or not the directory was removed
     */
    public static boolean removeDirectory(String path) {
        File filePath = new File(path);
        if (!filePath.exists()) {
            Core.log("Directory " + path + " does not exist, ignoring!", Core.LOG_TYPE_INFO, Core.getClassName());
            return true;
        }
        Core.log("Removing directory " + path, Core.LOG_TYPE_INFO, Core.getClassName());
        try {
            if (!filePath.delete()) {
                Core.log("Failed to delete directory " + path, Core.LOG_TYPE_ERROR, Core.getClassName());
                return false;
            } else {
                Core.log("Directory " + path + " deleted.", Core.LOG_TYPE_INFO, Core.getClassName());
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Core.log("Failed to delete directory " + path, Core.LOG_TYPE_ERROR, Core.getClassName());
            return false;
        }
    }
}
