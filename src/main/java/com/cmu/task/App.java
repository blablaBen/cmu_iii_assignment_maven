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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

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

        Map<Integer, Map<Integer, List<String>>> dataSource = createDataSource(ratingInputItems);
        List<String> recommendMovie = findRecommendMoviesByAge(35, 5, dataSource);
    }

    public static List<RatingInputItem> parseRatingInput(CSVParser csvParser) {
        List result = new ArrayList<RatingInputItem>();

        for (CSVRecord csvRecord : csvParser) {
            if(csvRecord.getRecordNumber() != 1) {
                RatingInputItem item = new RatingInputItem();
                item.userID = csvRecord.get("UserID");
                item.userName = csvRecord.get("UserName");
                item.userAge = Integer.parseInt(csvRecord.get("UserAge"));
                item.movieID = StringHelper.extractMovieId(csvRecord.get("MovieName"));
                item.movieName = StringHelper.extractMovieName(csvRecord.get("MovieName"));
                item.rating = Integer.parseInt(csvRecord.get("Rating"));
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

    public static  Map<Integer, Map<Integer, List<String>>> createDataSource(List<RatingInputItem> ratingInputItems) {
        Map<Integer, Map<Integer, List<String>>> result =  new HashMap<Integer, Map<Integer, List<String>>>();
        for(RatingInputItem item : ratingInputItems) {
            Map<Integer, List<String>> byAgeData = result.get(item.userAge);
            if(byAgeData == null) {
                byAgeData = new HashMap<Integer, List<String>>();
            }

            List<String> movieNameByRating = byAgeData.get(item.rating);
            if(movieNameByRating == null) {
                movieNameByRating = new ArrayList<String>();
            }
            movieNameByRating.add(item.movieName);

            byAgeData.put(item.rating, movieNameByRating);
            result.put(item.userAge, byAgeData);
        }
        return result;
    }

    public static List<String> findRecommendMoviesByAge(int age, int maxNumberOfMovie, Map<Integer, Map<Integer, List<String>>> dataSource) {
        List<String> result = new ArrayList<String>();

        Map<Integer, List<String>> byAgeData = dataSource.get(age);

        int[] ratings = new int[]{5,4,3,2,1};
        for(int rating: ratings) {
            if(result.size() <= maxNumberOfMovie) {
                List<String> movieNameByRating = byAgeData.get(rating);

                for (String movieName : movieNameByRating) {
                    if (result.size() <= maxNumberOfMovie) {
                        result.add(movieName);
                    } else {
                        break;
                    }
                }
            } else {
                break;
            }
        };

        return result;
    }


}
