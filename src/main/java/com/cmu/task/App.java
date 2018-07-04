package com.cmu.task;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Hello World!" );
        String inputOneFilePath = "/Users/user/Documents/BSB/assignment1-sample/RatingsInput (1).csv";

        Reader reader = Files.newBufferedReader(Paths.get(inputOneFilePath));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                .withHeader("UserID", "UserName", "UserAge", "MovieID", "MovieName", "Rating")
                .withIgnoreHeaderCase()
                .withTrim());
    }
}
