package com.cmu.task.helper;

public class StringHelper {
    public static String extractMovieId(String movieIdAndName) {
        return movieIdAndName.substring(0, movieIdAndName.indexOf(','));
    }

    public static String extractMovieName(String movieIdAndName) {
        return movieIdAndName.substring(movieIdAndName.indexOf(',')+1, movieIdAndName.length());
    }

    public  static String getCapitalizedString(String msg) {
        String newString = "";

        while(msg.length() != 0) {
            int indexOfSpace = msg.indexOf(" ");
            if(indexOfSpace != -1) {
                newString += Character.toUpperCase(msg.charAt(0)) + msg.substring(1, indexOfSpace);
                msg = msg.substring(indexOfSpace + 1, msg.length());
            } else {
                newString += Character.toUpperCase(msg.charAt(0)) + msg.substring(1, msg.length());
                break;
            }
        }

        return newString;
    }
}
