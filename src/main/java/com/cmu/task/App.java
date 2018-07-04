package com.cmu.task;

import com.cmu.task.domain.RatingInputItem;
import com.cmu.task.helper.StringHelper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

        List<RatingInputItem> ratingInputItems = parseRatingInput(csvParser);
        capitalizeMovieName(ratingInputItems);

    }

    public static List<RatingInputItem> parseRatingInput(CSVParser csvParser) {
        List result = new ArrayList<RatingInputItem>();

        for (CSVRecord csvRecord : csvParser) {
            if(csvRecord.getRecordNumber() != 1) {
                RatingInputItem item = new RatingInputItem();
                item.userID = csvRecord.get("UserID");
                item.userName = csvRecord.get("UserName");
                item.userAge = csvRecord.get("UserAge");
                item.movieID = StringHelper.extractMovieId(csvRecord.get("MovieName"));
                item.movieName = StringHelper.extractMovieName(csvRecord.get("MovieName"));
                item.rating = csvRecord.get("Rating");
                result.add(item);
            }
        }

        return result;
    }


    public static void capitalizeMovieName(List<RatingInputItem> ratingInputItems) {
        for(RatingInputItem item : ratingInputItems) {
            item.movieName = StringHelper.getCapitalizedString(item.movieName);
        }
    }


}
