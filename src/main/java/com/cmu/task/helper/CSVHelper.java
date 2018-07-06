package com.cmu.task.helper;

import com.cmu.task.domain.NewUserInputItem;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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

    public static void generateOutputFile(String path, List<NewUserInputItem> outputItems) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(path));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                .withHeader("UserName", "UserAge", "NoOfMoviesToRecommend", "Movies"));

        for(NewUserInputItem item : outputItems) {
            csvPrinter.printRecord(item.userName, item.userAge, item.noOfMoviesToRecommend, item.movies);
        }

        csvPrinter.flush();
    }
}


