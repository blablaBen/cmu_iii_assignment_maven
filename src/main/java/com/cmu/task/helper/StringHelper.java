package com.cmu.task.helper;

import java.util.List;

public class StringHelper {
    public static String extractMovieId(String movieIdAndName) {
        return movieIdAndName.substring(0, movieIdAndName.indexOf(','));
    }

    public static String extractMovieName(String movieIdAndName) {
        return movieIdAndName.substring(movieIdAndName.indexOf(',')+1, movieIdAndName.length());
    }

    public  static String getCapitalizedString(String msg) {
        String newString = "";

        int index = 0;
        while(msg.length() != 0) {
            if(index != 0) {
                newString += " ";
            }
            int indexOfSpace = msg.indexOf(" ");
            if(indexOfSpace != -1) {
                newString += Character.toUpperCase(msg.charAt(0)) + msg.substring(1, indexOfSpace);
                msg = msg.substring(indexOfSpace + 1, msg.length());
            } else {
                newString += Character.toUpperCase(msg.charAt(0)) + msg.substring(1, msg.length());
                break;
            }
            index++;
        }

        return newString;
    }

    public static String getStringFromList(List<String> input) {
        String result = "";
        for(int i = 0 ;i <input.size() ; i++) {
            result += input.get(i);
            if(i+1 < input.size()) {
                result+=",";
            }
        }
        return  result;
    }
}
