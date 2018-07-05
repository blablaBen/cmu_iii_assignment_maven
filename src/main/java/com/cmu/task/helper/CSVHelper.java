package com.cmu.task.helper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVHelper {

    public static CSVParser readRatingInputFile(String path) throws IOException {

        Reader reader = Files.newBufferedReader(Paths.get(path));
        return new CSVParser(reader, CSVFormat.DEFAULT
                .withHeader("UserID", "UserName", "UserAge", "MovieID", "MovieName", "Rating")
                .withIgnoreHeaderCase()
                .withTrim());

    }

    public static CSVParser readNewUserInputFile(String path) throws IOException {

        Reader reader = Files.newBufferedReader(Paths.get(path));
        return new CSVParser(reader, CSVFormat.DEFAULT
                .withHeader("UserName", "UserAge", "NoOfMoviesToRecommend", "Movies")
                .withIgnoreHeaderCase()
                .withTrim());

    }
}
