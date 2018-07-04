package com.cmu.task.helper;

public class StringHelper {
    public static String extractMovieId(String movieIdAndName) {
        return movieIdAndName.substring(0, movieIdAndName.indexOf(','));
    }

    public static String extractMovieName(String movieIdAndName) {
        return movieIdAndName.substring(movieIdAndName.indexOf(',')+1, movieIdAndName.length());
    }
}
